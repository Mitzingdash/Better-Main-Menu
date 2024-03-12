package mitzingdash.better_main_menu.client.gui.screen;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import net.minecraft.text.Text;

public class BmmConfigScreen extends TScreenPlus implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		// TODO Auto-generated method stub
		return parent -> new BmmConfigScreen().getAsScreen();
	}

	public BmmConfigScreen() {
		super(Text.translatable("narrator.screen.title"));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}
