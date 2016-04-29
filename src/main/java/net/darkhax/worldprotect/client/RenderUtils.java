package net.darkhax.worldprotect.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUtils {
    
    @SideOnly(Side.CLIENT)
    public static Frustum getCamera (Entity renderEntity, float partialTicks) {
        
        final double cameraX = renderEntity.prevPosX + (renderEntity.posX - renderEntity.prevPosX) * partialTicks;
        final double cameraY = renderEntity.prevPosY + (renderEntity.posY - renderEntity.prevPosY) * partialTicks;
        final double cameraZ = renderEntity.prevPosZ + (renderEntity.posZ - renderEntity.prevPosZ) * partialTicks;
        
        final Frustum camera = new Frustum();
        camera.setPosition(cameraX, cameraY, cameraZ);
        return camera;
    }
    
    @SideOnly(Side.CLIENT)
    public static void translateAgainstPlayer (BlockPos pos) {
        
        final float x = (float) (pos.getX() - TileEntityRendererDispatcher.staticPlayerX);
        final float y = (float) (pos.getY() - TileEntityRendererDispatcher.staticPlayerY);
        final float z = (float) (pos.getZ() - TileEntityRendererDispatcher.staticPlayerZ);
        GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
    }
    
    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getParticleTexture (ItemStack stack) {
        
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack).getParticleTexture();
    }
}