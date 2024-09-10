package mitzingdash.better_main_menu.client.gui.screen;

import io.github.thecsdev.tcdcommons.api.client.gui.other.TBlankElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TLabelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TTextureElement;
import io.github.thecsdev.tcdcommons.api.client.gui.panel.TPanelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.gui.util.GuiUtils;
import io.github.thecsdev.tcdcommons.api.client.gui.util.UITexture;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TCheckboxWidget;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TScrollBarWidget;
import io.github.thecsdev.tcdcommons.api.client.util.interfaces.IParentScreenProvider;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import mitzingdash.better_main_menu.BetterMainMenu;
import mitzingdash.better_main_menu.client.gui.widget.MBlankElement;
import mitzingdash.better_main_menu.client.gui.widget.MButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.swing.*;

public class BmmConfigScreen extends TScreenPlus implements IParentScreenProvider {

	public Screen parent = null;

	public BmmConfigScreen(Screen parent) {
		super(Text.translatable("options.title"));
		this.parent = parent;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		final var config = BetterMainMenu.CONFIG;
		var contentPane = new TFillColorElement(10, 10, getWidth() - 20, getHeight() - 20);
		contentPane.setColor(0x00000000);
		addChild(contentPane);

		// main box
		var generalContentPane = new TFillColorElement(0, 0, (int) (contentPane.getWidth() * 0.7), contentPane.getHeight());
		generalContentPane.setColor(0x77000000);
		contentPane.addChild(generalContentPane);

		var generalPanel = new TPanelElement(1, 1, generalContentPane.getWidth() - 2, generalContentPane.getHeight() - 2);
		generalPanel.setOutlineColor(0xff808080);
		generalPanel.setBackgroundColor(0x00000000);
		generalContentPane.addChild(generalPanel);

		// options screen select
		var optionsNavPanelPane = new TFillColorElement(
				generalContentPane.getEndX() + 5, generalContentPane.getY(),
				(contentPane.getWidth() - generalContentPane.getWidth()) - 5, contentPane.getHeight() / 2);
		optionsNavPanelPane.setColor(0x77000000);
		contentPane.addChild(optionsNavPanelPane, false);

		var optionsTitleHousing = new TPanelElement(1, 1, optionsNavPanelPane.getWidth() - 2, 20);
		optionsTitleHousing.setOutlineColor(0xff808080);
		optionsTitleHousing.setBackgroundColor(0x00000000);
		optionsNavPanelPane.addChild(optionsTitleHousing);

		var optionsNavPanel = new TPanelElement(1, 20, optionsNavPanelPane.getWidth() - 2, optionsNavPanelPane.getHeight() - 21);
		optionsNavPanel.setOutlineColor(0xff808080);
		optionsNavPanel.setBackgroundColor(0x00000000);
		optionsNavPanelPane.addChild(optionsNavPanel);

		//credits panel
		var creditsPanelPane = new TFillColorElement(
				optionsNavPanelPane.getX(), optionsNavPanelPane.getEndY() + 5,
				optionsNavPanelPane.getWidth(), generalContentPane.getHeight() - optionsNavPanelPane.getHeight() - 5);
		creditsPanelPane.setColor(0x77000000);
		contentPane.addChild(creditsPanelPane, false);

		var creditsTitlePanel = new TPanelElement(1, 1, creditsPanelPane.getWidth() - 2, 20);
		creditsTitlePanel.setOutlineColor(0xff808080);
		creditsTitlePanel.setBackgroundColor(0x00000000);
		creditsPanelPane.addChild(creditsTitlePanel);

		var creditsHousePanel = new TPanelElement(1, 20, creditsPanelPane.getWidth() - 2, creditsPanelPane.getHeight() - 21);
		creditsHousePanel.setOutlineColor(0xff808080);
		creditsHousePanel.setBackgroundColor(0x00000000);
		creditsHousePanel.setScrollFlags(TPanelElement.SCROLL_VERTICAL);
		creditsPanelPane.addChild(creditsHousePanel);

		var creditsPanelScrollBar = new TScrollBarWidget(creditsHousePanel.getEndX() - 5, 20, 10, creditsHousePanel.getHeight(), creditsHousePanel);
		creditsPanelPane.addChild(creditsPanelScrollBar);


		//Option Nav Children
		var mainTittle = new TLabelElement(optionsTitleHousing.getWidth() / 2 - 50, 7, 100, 10);
		mainTittle.setText(getTitle());
		mainTittle.setTextColor(0xffffffff);
		mainTittle.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		optionsTitleHousing.addChild(mainTittle);

		final var debugMode = new TCheckboxWidget(10, 5, optionsNavPanel.getWidth() - 15, 20);
		debugMode.setText(Text.literal("Debug Mode"));
		debugMode.setHorizontalAlignment(HorizontalAlignment.LEFT, HorizontalAlignment.RIGHT);
		debugMode.setChecked(config.debug);
		debugMode.setEnabled(true);
		optionsNavPanel.addChild(debugMode);

		var saveButton = new MButtonWidget(5, optionsNavPanel.getHeight() - 35, (int) ((double) optionsNavPanel.getWidth() / 2 - 7.5), 30);
		saveButton.setText(Text.literal("Done"));
		saveButton.setOnClick(__ -> {
			config.debug = debugMode.getChecked();
			config.saveToFileOrCrash(true);
			close();
		});
		optionsNavPanel.addChild(saveButton);

		var cancelButton = new MButtonWidget((int) (saveButton.getEndX() + 7.5), saveButton.getY(), optionsNavPanel.getWidth() / 2 - 10, 30);
		cancelButton.setText(Text.literal("Cancel"));
		cancelButton.setOnClick(__ -> {
			close();
		});
		optionsNavPanel.addChild(cancelButton, false);

		//Credits Children
		var creditsTitle = new TLabelElement(creditsHousePanel.getWidth() / 2 - 50, 7, 100, 10);
		creditsTitle.setText(Text.literal("Credits"));
		creditsTitle.setTextColor(0xffffffff);
		creditsTitle.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		creditsTitlePanel.addChild(creditsTitle);

		//Mitzing
		var mitzing = new MBlankElement(3, 5, creditsHousePanel.getWidth() - 6, 100);
		mitzing.setBorderColor(0xff808080);
		creditsHousePanel.addChild(mitzing);

		var mitNamePane = new MBlankElement(0, 0, mitzing.getWidth(), mitzing.getHeight() / 2 + 1);
		mitNamePane.setBorderColor(0xff808080);
		mitzing.addChild(mitNamePane);

		var mitIco = new TTextureElement(3, 3, 45, 45);
		mitIco.setTexture(new UITexture(Identifier.of("better_main_menu", "textures/gui/icons/mit.png")));
		mitNamePane.addChild(mitIco);

		var mitName = new TLabelElement(mitIco.getEndX(), mitIco.getY(), mitNamePane.getWidth() - 50, 20);
		mitName.setText(Text.literal("Mitzingdash"));
		mitName.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		mitNamePane.addChild(mitName, false);

		var mitDes = new TLabelElement(mitIco.getEndX(), mitIco.getY() + 15, mitNamePane.getWidth() - 50, 20);
		mitDes.setText(Text.literal("Lead Dev"));
		mitDes.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		mitDes.setTextColor(0xff535353);
		mitNamePane.addChild(mitDes, false);

		var mitLinks = new MBlankElement(0, 50, mitzing.getWidth(), mitzing.getHeight() / 2);
		mitLinks.setBorderColor(0xff808080);
		mitzing.addChild(mitLinks);

		//buttons
		var mitGitButton = new MButtonWidget(3, 3, 20, 20);
		mitGitButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/github.png")));
		mitGitButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://github.com/Mitzingdash", true);
		});
		mitLinks.addChild(mitGitButton);

		var mitModrinthButton = new MButtonWidget(26, 3, 20, 20);
		mitModrinthButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/wrench.png")));
		mitModrinthButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://modrinth.com/user/Mitzingdash", true);
		});
		mitLinks.addChild(mitModrinthButton);

		var mitYoutubeButton = new MButtonWidget(3, 26, 20, 20);
		mitYoutubeButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/youtube.png")));
		mitYoutubeButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://www.youtube.com/@mitzingdash", true);
		});
		mitLinks.addChild(mitYoutubeButton);

		var mitCurseButton = new MButtonWidget(26, 26, 20, 20);
		mitCurseButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/anvil.png")));
		mitCurseButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://www.curseforge.com/members/mitzingdash/projects", true);
		});
		mitLinks.addChild(mitCurseButton);

		//description
		var mitDescription1 = new TLabelElement(30, 3, mitLinks.getWidth() - 36, 15);
		mitDescription1.setText(Text.literal("Creator Of BMM"));
		mitDescription1.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		mitLinks.addChild(mitDescription1);

		var mitDescription2 = new TLabelElement(30, 16, mitLinks.getWidth() - 36, 15);
		mitDescription2.setText(Text.literal("Just Develops"));
		mitDescription2.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		mitLinks.addChild(mitDescription2);

		var mitDescription3 = new TLabelElement(30, 29, mitLinks.getWidth() - 36, 15);
		mitDescription3.setText(Text.literal("idk"));
		mitDescription3.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		mitLinks.addChild(mitDescription3);


		//Dave
		var dldave = new MBlankElement(3, 110, creditsHousePanel.getWidth() - 6, 100);
		dldave.setBorderColor(0xff808080);
		creditsHousePanel.addChild(dldave);

		var daveNamePane = new MBlankElement(0, 0, dldave.getWidth(), dldave.getHeight() / 2 + 1);
		daveNamePane.setBorderColor(0xff808080);
		dldave.addChild(daveNamePane);

		var daveIco = new TTextureElement(3, 3, 45, 45);
		daveIco.setTexture(new UITexture(Identifier.of("better_main_menu", "textures/gui/icons/thecsdev.png")));
		daveNamePane.addChild(daveIco);

		var daveName = new TLabelElement(daveIco.getEndX(), daveIco.getY(), daveNamePane.getWidth() - 50, 20);
		daveName.setText(Text.literal("TheCSDev"));
		daveName.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		daveNamePane.addChild(daveName, false);

		var daveDes = new TLabelElement(daveIco.getEndX(), daveIco.getY() + 15, daveNamePane.getWidth() - 50, 20);
		daveDes.setText(Text.literal("Main Help"));
		daveDes.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		daveDes.setTextColor(0xff535353);
		daveNamePane.addChild(daveDes, false);

		var daveLinks = new MBlankElement(0, 50, dldave.getWidth(), dldave.getHeight() / 2);
		daveLinks.setBorderColor(0xff808080);
		dldave.addChild(daveLinks);

		//buttons
		var daveGitButton = new MButtonWidget(3, 3, 20, 20);
		daveGitButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/github.png")));
		daveGitButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://github.com/TheCSMods", true);
		});
		daveLinks.addChild(daveGitButton);

		var daveModrinthButton = new MButtonWidget(26, 3, 20, 20);
		daveModrinthButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/wrench.png")));
		daveModrinthButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://modrinth.com/user/TheCSDev", true);
		});
		daveLinks.addChild(daveModrinthButton);

		var daveWebsiteButton = new MButtonWidget(3, 26, 20, 20);
		daveWebsiteButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/globe.png")));
		daveWebsiteButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://thecsdev.com/", true);
		});
		daveLinks.addChild(daveWebsiteButton);

		var daveCurseButton = new MButtonWidget(26, 26, 20, 20);
		daveCurseButton.setIcon(new UITexture(Identifier.of("better_main_menu", "textures/gui/anvil.png")));
		daveCurseButton.setOnClick(__ -> {
			GuiUtils.showUrlPrompt("https://www.curseforge.com/members/thecsdev/projects", true);
		});
		daveLinks.addChild(daveCurseButton);

		//description
		var daveDescription1 = new TLabelElement(30, 3, daveLinks.getWidth() - 36, 15);
		daveDescription1.setText(Text.literal("Creator Of BSS"));
		daveDescription1.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		daveLinks.addChild(daveDescription1);

		var daveDescription2 = new TLabelElement(30, 16, daveLinks.getWidth() - 36, 15);
		daveDescription2.setText(Text.literal("Helped alot"));
		daveDescription2.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		daveLinks.addChild(daveDescription2);

		var daveDescription3 = new TLabelElement(30, 29, daveLinks.getWidth() - 36, 15);
		daveDescription3.setText(Text.literal("also made TCDcommons"));
		daveDescription3.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		daveLinks.addChild(daveDescription3);

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
