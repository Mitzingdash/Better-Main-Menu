package mitzingdash.better_main_menu.client.gui.screen;

import io.github.thecsdev.tcdcommons.api.client.gui.other.TBlankElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TLabelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TTextureElement;
import io.github.thecsdev.tcdcommons.api.client.gui.panel.TPanelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
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
	protected void init()
	{
		final var config = BetterMainMenu.CONFIG;
	    var contentPane = new TFillColorElement(10, 10, getWidth()-20, getHeight()-20);
	    contentPane.setColor(0x00000000);
	    addChild(contentPane);
	    
	    // main box
	    var generalContentPane = new TFillColorElement(0, 0, (int) (contentPane.getWidth()*0.7), contentPane.getHeight());
	    generalContentPane.setColor(0x77000000);
	    contentPane.addChild(generalContentPane);

	    var generalPanel = new TPanelElement(1, 1, generalContentPane.getWidth()-2, generalContentPane.getHeight() - 2);
	    generalPanel.setOutlineColor(0xff808080);
	    generalPanel.setBackgroundColor(0x00000000);
	    generalContentPane.addChild(generalPanel);
	    
	    // options screen select
	    var optionsNavPanelPane = new TFillColorElement(
	        generalContentPane.getEndX() + 5, generalContentPane.getY(),
	        (contentPane.getWidth() - generalContentPane.getWidth()) - 5, contentPane.getHeight() / 2);
	    optionsNavPanelPane.setColor(0x77000000);
	    contentPane.addChild(optionsNavPanelPane, false);
	    
	    var optionsTitleHousing = new TPanelElement(1, 1, optionsNavPanelPane.getWidth() -2, 20);
	    optionsTitleHousing.setOutlineColor(0xff808080);
	    optionsTitleHousing.setBackgroundColor(0x00000000);
	    optionsNavPanelPane.addChild(optionsTitleHousing);

	    var optionsNavPanel = new TPanelElement(1, 20, optionsNavPanelPane.getWidth() - 2, optionsNavPanelPane.getHeight()-21);
	    optionsNavPanel.setOutlineColor(0xff808080);
	    optionsNavPanel.setBackgroundColor(0x00000000);
		optionsNavPanelPane.addChild(optionsNavPanel);

		//credits panel
		var creditsPanelPane = new TFillColorElement(
			optionsNavPanelPane.getX(), optionsNavPanelPane.getEndY() + 5,
			optionsNavPanelPane.getWidth(), generalContentPane.getHeight() - optionsNavPanelPane.getHeight() - 5);
		creditsPanelPane.setColor(0x77000000);
		contentPane.addChild(creditsPanelPane, false);

		var creditsTitlePanel = new TPanelElement(1, 1, creditsPanelPane.getWidth() -2, 20);
		creditsTitlePanel.setOutlineColor(0xff808080);
		creditsTitlePanel.setBackgroundColor(0x00000000);
		creditsPanelPane.addChild(creditsTitlePanel);

		var creditsHousePanel = new TPanelElement(1, 20, creditsPanelPane.getWidth()-2, creditsPanelPane.getHeight() - 21);
		creditsHousePanel.setOutlineColor(0xff808080);
		creditsHousePanel.setBackgroundColor(0x00000000);
		creditsHousePanel.setScrollFlags(TPanelElement.SCROLL_VERTICAL);
		creditsPanelPane.addChild(creditsHousePanel);

		var creditsPanelScrollBar = new TScrollBarWidget(creditsHousePanel.getEndX() - 5, 20, 10, creditsHousePanel.getHeight(), creditsHousePanel);
		creditsPanelPane.addChild(creditsPanelScrollBar);


		//Option Nav Children
	    var mainTittle = new TLabelElement(optionsTitleHousing.getWidth()/2-50, 7, 100, 10);
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
		cancelButton.setOnClick(__ ->{
			close();
		});
		optionsNavPanel.addChild(cancelButton, false);

		//Credits Children
		var creditsTitle = new TLabelElement(creditsHousePanel.getWidth()/2-50, 7, 100, 10);
		creditsTitle.setText(Text.literal("Credits"));
		creditsTitle.setTextColor(0xffffffff);
		creditsTitle.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
		creditsTitlePanel.addChild(creditsTitle);

		//Mitzing
		var mitzing = new MBlankElement(3, 5, creditsHousePanel.getWidth() - 6, 80);
		mitzing.setBorderColor(0xff808080);
		creditsHousePanel.addChild(mitzing);

		var mitIco = new TTextureElement(3, 3, 30, 30);
		mitIco.setTexture(new UITexture(Identifier.of("better_main_menu", "textures/gui/icons/mit.png")));
		mitzing.addChild(mitIco);

		//Dave
		var csDave = new MBlankElement(3, 90, creditsHousePanel.getWidth() - 6, 80);
		csDave.setBorderColor(0xff808080);
		creditsHousePanel.addChild(csDave);

		var daveIco = new TTextureElement(3, 3, 30, 30);
		daveIco.setTexture(new UITexture(Identifier.of("better_main_menu", "textures/gui/icons/thecsdev.png")));
		csDave.addChild(daveIco);
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
