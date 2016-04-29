package net.darkhax.worldprotect.libs;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    
    public static final String MOD_ID = "WorldProtect";
    public static final String MOD_NAME = "World Protect";
    public static final String VERSION_NUMBER = "1.0.0.0";
    public static final String MCVERSION = "[1.9,1.9.2]";
    public static final String CLIENT_PROXY_CLASS = "net.darkhax.worldprotect.client.ProxyClient";
    public static final String SERVER_PROXY_CLASS = "net.darkhax.worldprotect.common.ProxyCommon";
    public static final String DEPENDENCIES = "after:JEI@[2.28.5.172,];";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
}
