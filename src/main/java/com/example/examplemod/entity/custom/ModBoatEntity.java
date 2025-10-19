package com.example.examplemod.entity.custom;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.MOD_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case ABYSS -> ModItems.ABYSS_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.ABYSS.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    @Override
    public boolean fireImmune() {
        return this.getModVariant() == Type.ABYSS;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (this.getModVariant() == Type.ABYSS && source.is(DamageTypeTags.IS_FIRE)) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getModVariant() == Type.ABYSS && isInLava()) {
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

    public static enum Type implements StringRepresentable {
        ABYSS(ModBlocks.ABYSS_PLANKS.get(), "abyss");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ModBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ModBoatEntity.Type::values);
        private static final IntFunction<ModBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static ModBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static ModBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, ABYSS);
        }
    }
}
