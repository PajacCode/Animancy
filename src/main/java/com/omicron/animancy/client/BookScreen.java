package com.omicron.animancy.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.omicron.animancy.Animancy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

public class BookScreen extends Screen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Animancy.MODID, "textures/gui/gui.png");
    private static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");

    public BookScreen() {
        super(new TranslationTextComponent("screen.omicronexample.book"));
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks)
    {
        this.renderBackground(pMatrixStack);
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        drawString(pMatrixStack, Minecraft.getInstance().font, "test", 10, 10, 0xffffff);
        Style style = new StringTextComponent("yes").getStyle();
        Style style1 = style.withFont(new ResourceLocation(Animancy.MODID, "test"));
        pMatrixStack.pushPose();
        pMatrixStack.scale(0.3F, 0.3F, 0.3F);
        Minecraft.getInstance().font.drawShadow(pMatrixStack, new StringTextComponent("yes").setStyle(style1).append(" tak"), 6, 6, 0xffffff);
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(pMatrixStack, 200, 200, 0, 0, 0, 0, 955, 638, 955, 638);
        this.minecraft.getTextureManager().bind(TEXTURE);
        this.blit(pMatrixStack, 100 + 0, 100, 0, 0, 955, 638, 955, 638);
        pMatrixStack.popPose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        super.mouseClicked(pMouseX, pMouseY, pButton);
        System.out.println(width + " W");
        System.out.println(height + " H");
        System.out.println(Minecraft.getInstance().getWindow().getGuiScale() + " S");
        return true;
    }
}
