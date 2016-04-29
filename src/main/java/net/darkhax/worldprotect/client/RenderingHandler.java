package net.darkhax.worldprotect.client;

import net.darkhax.worldprotect.handler.BlockHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderingHandler {
    
    public static TextureAtlasSprite icon;
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onWorldRender (RenderWorldLastEvent event) {
        
        if (icon == null)
            icon = RenderUtils.getParticleTexture(new ItemStack(Blocks.STAINED_GLASS));
            
        final Minecraft mc = Minecraft.getMinecraft();
        final Frustum camera = RenderUtils.getCamera(mc.getRenderViewEntity(), event.getPartialTicks());
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        
        for (final BlockPos pos : BlockHandler.PROTECTED)
            if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(pos)))
                this.renderPingOverlay(pos);
    }
    
    private void renderPingOverlay (BlockPos pos) {
        
        final float box = 1.01f;
        
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.disableDepth();
        
        RenderUtils.translateAgainstPlayer(pos);
        drawBlockOverlay(box, box, box, icon, 255, 0, 0, 100);
        GlStateManager.translate(0, 0, 0);
        
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOverlay (float width, float height, float length, TextureAtlasSprite icon, int red, int green, int blue, int alpha) {
        
        final Tessellator tessellator = Tessellator.getInstance();
        final VertexBuffer vertexbuffer = tessellator.getBuffer();
        
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        // TOP
        vertexbuffer.pos(-(width / 2), height / 2, -(length / 2)).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, -(length / 2)).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, length / 2).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), height / 2, length / 2).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        
        // BOTTOM
        vertexbuffer.pos(-(width / 2), -(height / 2), length / 2).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, -(height / 2), length / 2).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, -(height / 2), -(length / 2)).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), -(height / 2), -(length / 2)).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        
        // NORTH
        vertexbuffer.pos(-(width / 2), height / 2, length / 2).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, length / 2).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, -(height / 2), length / 2).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), -(height / 2), length / 2).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        
        // SOUTH
        vertexbuffer.pos(-(width / 2), -(height / 2), -(length / 2)).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, -(height / 2), -(length / 2)).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, -(length / 2)).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), height / 2, -(length / 2)).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        
        // EAST
        vertexbuffer.pos(-(width / 2), height / 2, -(length / 2)).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), height / 2, length / 2).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), -(height / 2), length / 2).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(-(width / 2), -(height / 2), -(length / 2)).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        
        // WEST
        vertexbuffer.pos(width / 2, -(height / 2), -(length / 2)).tex(icon.getMinU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, -(height / 2), length / 2).tex(icon.getMaxU(), icon.getMinV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, length / 2).tex(icon.getMaxU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(width / 2, height / 2, -(length / 2)).tex(icon.getMinU(), icon.getMaxV()).color(red, green, blue, alpha).endVertex();
        
        tessellator.draw();
    }
}
