package com.example.examplemod.entity.custom;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public ModChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.MOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getModVariant()) {
            case ABYSS -> {
                return ModItems.ABYSS_CHEST_BOAT.get();
            }
        }
        return super.getDropItem();
    }

    public void setVariant(ModBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, ModBoatEntity.Type.ABYSS.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(ModBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    @Override
    public boolean fireImmune() {
        return this.getModVariant() == ModBoatEntity.Type.ABYSS;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (this.getModVariant() == ModBoatEntity.Type.ABYSS && source.is(DamageTypeTags.IS_FIRE)) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getModVariant() == ModBoatEntity.Type.ABYSS && isInLava()) {
            handleLavaBuoyancy();
        }
    }

    public boolean isInLava() {
        return this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA);
    }

    private void handleLavaBuoyancy() {
        var pos = this.blockPosition();
        var fluidState = this.level().getFluidState(pos);

        if (!fluidState.is(FluidTags.LAVA)) return;

        // Lava surface height
        double surfaceY = pos.getY() + fluidState.getHeight(this.level(), pos);
        double currentY = this.getY();

        // Raise target height so lava doesn’t seep through
        double targetY = surfaceY + 0.17D; // 👈 slightly higher above the lava
        double deltaY = targetY - currentY;

        // Smooth buoyancy — stable and less bopping
        double buoyancy = deltaY * 0.5D;

        Vec3 motion = this.getDeltaMovement();
        double verticalMotion = motion.y * 0.4D; // damping to stop bouncing

        // Apply forces: smooth, slightly stronger lift
        this.setDeltaMovement(
                motion.x * 0.9D,
                verticalMotion + buoyancy * 0.5D,
                motion.z * 0.9D
        );

        // Prevent falling physics & false ground checks
        this.fallDistance = 0.0F;
        this.setOnGround(false);
    }

    public ModBoatEntity.Type getModVariant() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
