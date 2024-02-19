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
import mitzingdash.better_main_menu.client.gui.widget.CreditButtonWidget;
import mitzingdash.better_main_menu.client.gui.widget.MButtonWidget;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.resource.language.LanguageManager;
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
		
		var btn_quit = new MButtonWidget(panel.getWidth()-50, panel.getHeight()-25, 45, 20);
		btn_quit.setText(Text.literal("Quit"));
		btn_quit.setTooltip(Tooltip.of(Text.translatable("menu.quit")));
		btn_quit.setOnClick(__ -> {
			getClient().scheduleStop();
		});
		panel.addChild(btn_quit);
		
		var accesibility = new MButtonWidget(panel.getWidth()-73, panel.getHeight()-25, 20, 20);
		accesibility.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/accessibility.png")));
		accesibility.setTooltip(Tooltip.of(Text.translatable("narrator.button.accessibility")));
		accesibility.setOnClick(__ -> {
			getClient().setScreen(new AccessibilityOptionsScreen(getAsScreen(), getClient().options));
		});
		panel.addChild(accesibility);
		
		var language = new MButtonWidget(5, panel.getHeight()-25, 20, 20);
		language.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/language.png")));
		language.setTooltip(Tooltip.of(Text.translatable("narrator.button.language")));
		language.setOnClick(__ -> {
			getClient().setScreen(new LanguageOptionsScreen(getAsScreen(), getClient().options, getClient().getLanguageManager()));
		});
		panel.addChild(language);
		
		var realms = new MButtonWidget(panel.getWidth()-50, panel.getHeight()-48, 45, 20);
		realms.setText(Text.literal("Realms"));
		realms.setTooltip(Tooltip.of(Text.translatable("menu.online")));
		realms.setOnClick(__ -> {
			getClient().setScreen(new RealmsMainScreen(getAsScreen()));
		});
		panel.addChild(realms);
		
		var credits = new CreditButtonWidget(5, getHeight()-12, 152, 10);
		credits.setText(Text.literal("Main Menu made by Mitzingdash").formatted(Formatting.GREEN));
		credits.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://github.com/Mitzingdash", true);
		});
		addChild(credits);
		
		var txt_social = new TLabelElement(5, getHeight()-20, 50, 5, Text.literal("Not affiliated with MOJANG").formatted(Formatting.GOLD));
		addChild(txt_social);
		
		var settings = new MButtonWidget(5, getHeight()-45, 20, 20);
		settings.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/settings.png")));
		settings.setTooltip(Tooltip.of(Text.literal("Bmm Settings")));
		settings.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://github.com/Mitzingdash", true);
		});
		//addChild(settings);
		
		
	}

	@Override
	public void renderBackground(TDrawContext pencil) {
		// TODO Auto-generated method stub
		TEX_BACKGROUND.drawTexture(pencil, 0, 0, getWidth(), getHeight());
	}
	
	
}
