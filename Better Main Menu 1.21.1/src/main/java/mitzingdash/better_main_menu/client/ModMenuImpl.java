package mitzingdash.better_main_menu.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import mitzingdash.better_main_menu.client.gui.screen.BmmConfigScreen;

public class ModMenuImpl implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		// TODO Auto-generated method stub
		return parent -> new BmmConfigScreen(parent).getAsScreen();
	}
	
}
