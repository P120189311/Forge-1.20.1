package com.p120189311.theendoflimbo.block.entity;

import com.p120189311.theendoflimbo.recipe.MysteryStandRecipe;
import com.p120189311.theendoflimbo.screen.MysteryStandMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysteryStandBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ItemStackHandler getItemHandler() {
        return this.itemHandler;
    }

    public static final int INPUT_SLOT0 = 0;
    public static final int INPUT_SLOT1 = 1;
    public static final int INPUT_SLOT2 = 2;
    public static final int INPUT_SLOT3 = 3;
    public static final int OUTPUT_SLOT = 4;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public MysteryStandBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(TheEndOfLimboBlockEntities.MYSTERY_STAND_BE.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> MysteryStandBlockEntity.this.progress;
                    case 1 -> MysteryStandBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MysteryStandBlockEntity.this.progress = pValue;
                    case 1 -> MysteryStandBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public List<ItemStack> getRenderStack() {
        List<ItemStack> stacks = new ArrayList<>();

        for (int slot : new int[]{INPUT_SLOT0, INPUT_SLOT1, INPUT_SLOT2, INPUT_SLOT3}) {
            ItemStack stack = itemHandler.getStackInSlot(slot);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }

        return stacks;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.theendoflimbo.mystery_stand");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new MysteryStandMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("mystery_stand.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("mystery_stand.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);
            
            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        }   else {
            resetProgress();
        }
    }
    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<MysteryStandRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        if (result.isEmpty()) return;

        for (int i = 0; i < 4; i++) {
            ItemStack stack = this.itemHandler.getStackInSlot(i);

            if (stack.getItem() == Items.POTION && stack.hasTag() && "minecraft:water".equals(stack.getTag().getString("Potion"))) {
                // Replace Water Bottle with Glass Bottle
                this.itemHandler.extractItem(i, 1, false);
                ItemStack remainder = new ItemStack(Items.GLASS_BOTTLE);
                this.itemHandler.insertItem(i, remainder, false);
            } else {
                this.itemHandler.extractItem(i, 1, false);
            }
        }

        ItemStack outputStack = this.itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (outputStack.isEmpty()) {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
        } else {
            outputStack.grow(result.getCount());
        }
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<MysteryStandRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertItemIntoOutputSlot(result.getItem()) && canInsertAmountIntoOutputSlot(result.getCount());
    }

    private Optional<MysteryStandRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(MysteryStandRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        ItemStack outputStack = this.itemHandler.getStackInSlot(OUTPUT_SLOT);

        if (outputStack.isEmpty()) {
            return true;
        }

        if (outputStack.is(item) && outputStack.getCount() < outputStack.getMaxStackSize()) {
            return true;
        }

        return false;
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        ItemStack outputStack = this.itemHandler.getStackInSlot(OUTPUT_SLOT);

        return outputStack.isEmpty() ||
                (outputStack.getCount() + amount <= outputStack.getMaxStackSize());
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
