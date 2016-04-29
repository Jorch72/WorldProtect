package net.darkhax.worldprotect;

import net.darkhax.worldprotect.common.ProxyCommon;
import net.darkhax.worldprotect.handler.BlockHandler;
import net.darkhax.worldprotect.libs.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, dependencies = Constants.DEPENDENCIES, acceptedMinecraftVersions = Constants.MCVERSION)
public class WorldProtect {
    
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static ProxyCommon proxy;
    
    @Mod.Instance(Constants.MOD_ID)
    public static WorldProtect instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        proxy.onPreInit();
        MinecraftForge.EVENT_BUS.register(new BlockHandler());
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
    
    }
    
    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
    
    }
}