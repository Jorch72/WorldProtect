package net.darkhax.worldprotect.client;

import net.darkhax.worldprotect.common.ProxyCommon;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient extends ProxyCommon {
    
    @Override
    public void onPreInit () {
        
        MinecraftForge.EVENT_BUS.register(new RenderingHandler());
    }
    
    @Override
    public void onInit () {
    
    }
    
    @Override
    public void onPostInit () {
    
    }
}