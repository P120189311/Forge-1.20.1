package com.p120189311.theendoflimbo.screen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MysteryStandScreen extends AbstractContainerScreen<MysteryStandMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TheEndOfLimboMod.MAIN, "textures/gui/mystery_stand_incantation_gui.png");
    public MysteryStandScreen(MysteryStandMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (this.menu.isCrafting()) {
            int progress = this.menu.getScaledProgress();
            int p = progress + 1;

            guiGraphics.blit(TEXTURE, x + 53, y + 38, 178, 11, p, 8);

            int srcStartU = 178 + (16 - p);
            int destX = x + 107 + (16 - p);
            guiGraphics.blit(TEXTURE, destX, y + 38, srcStartU, 2, p, 8);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}

