package com.example.examplemod.block.entity.renderer;

import com.example.examplemod.block.entity.MysteryStandBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.List;

public class MysteryStandBlockEntityRenderer implements BlockEntityRenderer<MysteryStandBlockEntity> {
    public MysteryStandBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(MysteryStandBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        List<ItemStack> stacks = pBlockEntity.getRenderStack();

        ItemStack output = pBlockEntity.getItemHandler().getStackInSlot(MysteryStandBlockEntity.OUTPUT_SLOT);
        if (!output.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.5, 0.7, 0.5);
            pPoseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(
                    output,
                    ItemDisplayContext.GROUND,
                    pPackedLight,
                    pPackedOverlay,
                    pPoseStack,
                    pBuffer,
                    pBlockEntity.getLevel(),
                    0
            );
            pPoseStack.popPose();
        }

        // --- Render inputs in the 4 corners ---
        for (int slot = 0; slot <= 3; slot++) { // input slots
            ItemStack stack = pBlockEntity.getItemHandler().getStackInSlot(slot);
            if (stack.isEmpty()) continue; // skip empty slots

            pPoseStack.pushPose();

            // Corner positions (relative to block)
            switch (slot) {
                case 0 -> pPoseStack.translate(0.3125, 0.64, 0.3125); // front-right
                case 1 -> pPoseStack.translate(0.6875, 0.64, 0.3125); // front-left
                case 2 -> pPoseStack.translate(0.3125, 0.64, 0.6875); // back-right
                case 3 -> pPoseStack.translate(0.6875, 0.64, 0.6875); // back-left
            }

            pPoseStack.scale(0.3f, 0.3f, 0.3f);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(270f));

            float angle = switch (slot) {
                case 0 -> 225f;
                case 1 -> 135f;
                case 2 -> 315f;
                case 3 -> 45f;
                default -> 0f;
            };

            pPoseStack.mulPose(Axis.ZP.rotationDegrees(angle));

            itemRenderer.renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    pPoseStack,
                    pBuffer,
                    pBlockEntity.getLevel(),
                    0
            );

            pPoseStack.popPose();
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
