package mitzingdash.better_main_menu.client.gui.screen;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.util.interfaces.IParentScreenProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class BmmConfigScreen extends TScreenPlus implements ModMenuApi, IParentScreenProvider {

	public Screen parent = null;
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		// TODO Auto-generated method stub
		return parent -> new BmmConfigScreen(parent).getAsScreen();
	}

	public BmmConfigScreen(Screen parent) {
		super(Text.translatable("narrator.screen.title"));
		this.parent = parent;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getParentScreen() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	public void close() {
		getClient().setScreen(this.parent);
	}

}
