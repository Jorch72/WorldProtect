package net.darkhax.worldprotect.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockHandler {
    
    public static final List<BlockPos> PROTECTED = new ArrayList<BlockPos>();
    
    @SubscribeEvent
    public void onBlockBreak (BreakEvent event) {
        
        if (PROTECTED.contains(event.getPos()))
            event.setCanceled(true);
            
        protectBlock(event.getPos());
    }
    
    public static void protectBlock (BlockPos pos) {
        
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run () {
                
                if (!PROTECTED.contains(pos))
                    PROTECTED.add(pos);
            }
        });
    }
    
    public static void unprotectBlock (BlockPos pos) {
        
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run () {
                
                PROTECTED.remove(pos);
            }
        });
    }
}
