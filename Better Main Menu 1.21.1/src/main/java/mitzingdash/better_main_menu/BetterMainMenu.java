package mitzingdash.better_main_menu;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class BetterMainMenu implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("better_main_menu");

    public static BetterMainMenuConfig CONFIG = new BetterMainMenuConfig();
    
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

    	CONFIG.loadFromFileOrCrash(true);

        new File(System.getProperty("user.dir") + "/config/BMMImages").mkdirs();
        new File(System.getProperty("user.dir") + "/config/BMMImages/Icon").mkdirs();
        new File(System.getProperty("user.dir") + "/config/BMMImages/Background").mkdirs();

        if(CONFIG.debug)
            LOGGER.info(System.getProperty("user.dir"));

    }

}