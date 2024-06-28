package mitzingdash.better_main_menu.client;

import io.github.thecsdev.tcdcommons.api.events.client.gui.screen.ScreenEvent;
import mitzingdash.better_main_menu.client.gui.screen.BmmScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

public class BetterMainMenuClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

    	ScreenEvent.INIT_POST.register(screen -> {
    		if (screen instanceof TitleScreen) {
    			MinecraftClient.getInstance().setScreen(new BmmScreen().getAsScreen());
    		};
    	});

    }

}
