package mitzingdash.better_main_menu.client.gui.screen;

import com.terraformersmc.modmenu.api.ModMenuApi;

import io.github.thecsdev.tcdcommons.api.client.gui.layout.UIListLayout;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.client.gui.util.UITexture;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TButtonWidget;
import io.github.thecsdev.tcdcommons.api.util.enumerations.Axis2D;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import io.github.thecsdev.tcdcommons.api.util.enumerations.VerticalAlignment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BmmScreen extends TScreenPlus {

	public BmmScreen() {
		super(Text.translatable("narrator.screen.title"));
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	@Override
	protected void init() {
		
		var panel = new TFillColorElement(getWidth()-100, 0, 100, getHeight());
		panel.setColor(0x77000000);
		addChild(panel);
		
		var button_panel = new TFillColorElement(0, panel.getHeight()/2-50, panel.getWidth(), 100);
		button_panel.setColor(0x00000000);
		panel.addChild(button_panel);
		
		var singleplayer = new TButtonWidget(5, 5, 90, 20);
		singleplayer.setText(Text.translatable("menu.singleplayer"));
		singleplayer.setOnClick(__ -> {
			getClient().setScreen(new SelectWorldScreen(getAsScreen()));
		});
		button_panel.addChild(singleplayer);
		
		var multiplayer = new TButtonWidget(5, 27, 90, 20);
		multiplayer.setText(Text.translatable("menu.multiplayer"));
		multiplayer.setOnClick(__ -> {
			final boolean smw = getClient().options.skipMultiplayerWarning;
			final var screen = smw ? new MultiplayerScreen(getAsScreen()) : new MultiplayerWarningScreen(getAsScreen());
			getClient().setScreen(screen);
		});
		button_panel.addChild(multiplayer);
		
		var options = new TButtonWidget(5, 49, 90, 20);
		options.setText(Text.translatable("menu.options"));
		options.setOnClick(__ -> {
			getClient().setScreen(new OptionsScreen(getAsScreen(), getClient().options));
		});
		button_panel.addChild(options);
		
		if(FabricLoader.getInstance().isModLoaded("modmenu")) {
			var mod_btn = new TButtonWidget(0, 0, 90, 20);
			mod_btn.setText(Text.translatable("modmenu.title"));
			mod_btn.setOnClick(__ -> {
				getClient().setScreen(ModMenuApi.createModsScreen(getAsScreen()));
			});
			button_panel.addChild(mod_btn);
		}
		
		new UIListLayout(Axis2D.Y, VerticalAlignment.CENTER, HorizontalAlignment.CENTER, 2).apply(button_panel);
		
		var btn_quit = new TButtonWidget(panel.getWidth()-25, panel.getHeight()-25, 20, 20);
		btn_quit.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/x_white.png")));
		btn_quit.setTooltip(Tooltip.of(Text.translatable("menu.quit")));
		btn_quit.setOnClick(__ -> {
			getClient().scheduleStop();
		});
		panel.addChild(btn_quit);
		
		var realms = new TButtonWidget(panel.getWidth()-50, panel.getHeight()-25, 20, 20);
		realms.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/realms_white.png")));
		realms.setTooltip(Tooltip.of(Text.translatable("menu.online")));
		realms.setOnClick(__ -> {
			getClient().setScreen(new RealmsMainScreen(getAsScreen()));
		});
		panel.addChild(realms);
		
	}

	@Override
	public void renderBackground(TDrawContext pencil) {
		// TODO Auto-generated method stub
		pencil.drawTFill(0xff0b6b00);
	}
	
	

}
