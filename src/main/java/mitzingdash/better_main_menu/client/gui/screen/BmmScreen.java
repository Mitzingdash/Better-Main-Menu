package mitzingdash.better_main_menu.client.gui.screen;

import com.terraformersmc.modmenu.api.ModMenuApi;

import io.github.thecsdev.tcdcommons.api.client.gui.layout.UIListLayout;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TLabelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TTextureElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.gui.util.GuiUtils;
import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.client.gui.util.UITexture;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TButtonWidget;
import io.github.thecsdev.tcdcommons.api.util.enumerations.Axis2D;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import io.github.thecsdev.tcdcommons.api.util.enumerations.VerticalAlignment;
import mitzingdash.better_main_menu.client.gui.widget.MButtonWidget;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class BmmScreen extends TScreenPlus {

	public static final UITexture TEX_BACKGROUND = new UITexture(new Identifier("better_main_menu", "textures/gui/background.png"));
	
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
		
		var logo = new TTextureElement(10, 5, 80, 80);
		logo.setTexture(new UITexture(new Identifier("better_main_menu", "textures/gui/logo.png")));
		panel.addChild(logo);
		
		var button_panel = new TFillColorElement(0, panel.getHeight()/2-40, panel.getWidth(), 100);
		button_panel.setColor(0x00000000);
		panel.addChild(button_panel);
		
		var singleplayer = new MButtonWidget(5, 5, 90, 20);
		singleplayer.setText(Text.translatable("menu.singleplayer"));
		singleplayer.setOnClick(__ -> {
			getClient().setScreen(new SelectWorldScreen(getAsScreen()));
		});
		button_panel.addChild(singleplayer);
		
		var multiplayer = new MButtonWidget(5, 27, 90, 20);
		multiplayer.setText(Text.translatable("menu.multiplayer"));
		multiplayer.setOnClick(__ -> {
			final boolean smw = getClient().options.skipMultiplayerWarning;
			final var screen = smw ? new MultiplayerScreen(getAsScreen()) : new MultiplayerWarningScreen(getAsScreen());
			getClient().setScreen(screen);
		});
		button_panel.addChild(multiplayer);
		
		var options = new MButtonWidget(5, 49, 90, 20);
		options.setText(Text.translatable("menu.options"));
		options.setOnClick(__ -> {
			getClient().setScreen(new OptionsScreen(getAsScreen(), getClient().options));
		});
		button_panel.addChild(options);
		
		if(FabricLoader.getInstance().isModLoaded("modmenu")) {
			var mod_btn = new MButtonWidget(0, 0, 90, 20);
			mod_btn.setText(Text.translatable("modmenu.title"));
			mod_btn.setOnClick(__ -> {
				getClient().setScreen(ModMenuApi.createModsScreen(getAsScreen()));
			});
			button_panel.addChild(mod_btn);
		}
		
		new UIListLayout(Axis2D.Y, VerticalAlignment.CENTER, HorizontalAlignment.CENTER, 2).apply(button_panel);
		
		var btn_quit = new MButtonWidget(panel.getWidth()-25, panel.getHeight()-25, 20, 20);
		btn_quit.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/quit.png")));
		btn_quit.setTooltip(Tooltip.of(Text.translatable("menu.quit")));
		btn_quit.setOnClick(__ -> {
			getClient().scheduleStop();
		});
		panel.addChild(btn_quit);
		
		var realms = new MButtonWidget(panel.getWidth()-50, panel.getHeight()-25, 20, 20);
		realms.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/realms.png")));
		realms.setTooltip(Tooltip.of(Text.translatable("menu.online")));
		realms.setOnClick(__ -> {
			getClient().setScreen(new RealmsMainScreen(getAsScreen()));
		});
		panel.addChild(realms);
		
		var credits = new TLabelElement(5, getHeight()-10, 50, 5, Text.literal("Main Menu made by Mitzingdash").formatted(Formatting.GREEN));
		addChild(credits);
		
		var txt_social = new TLabelElement(5, getHeight()-20, 50, 5, Text.literal("Follow for updates").formatted(Formatting.GOLD));
		addChild(txt_social);
		
		var github = new MButtonWidget(5, getHeight()-45, 20, 20);
		github.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/github.png")));
		github.setTooltip(Tooltip.of(Text.literal("My Github")));
		github.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://github.com/Mitzingdash", true);
		});
		addChild(github);
		
		var ko_fi = new MButtonWidget(27, getHeight()-45, 20, 20);
		ko_fi.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/ko_fi.png")));
		ko_fi.setTooltip(Tooltip.of(Text.literal("Donate to support me!")));
		ko_fi.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://ko-fi.com/mitzingdash", true);
		});
		addChild(ko_fi);
		
	}

	@Override
	public void renderBackground(TDrawContext pencil) {
		// TODO Auto-generated method stub
		TEX_BACKGROUND.drawTexture(pencil, 0, 0, getWidth(), getHeight());
	}
	
	
}
