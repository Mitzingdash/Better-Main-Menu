package mitzingdash.better_main_menu.client.gui.screen;

import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TLabelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.panel.TPanelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.util.interfaces.IParentScreenProvider;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

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
	    // TODO Auto-generated method stub
	    var contentPane = new TFillColorElement(10, 10, getWidth()-20, getHeight()-20);
	    contentPane.setColor(0x00000000);
	    addChild(contentPane);
	    
	    // main box
	    var generalContentPane = new TFillColorElement(0, 0, (int) (contentPane.getWidth()*0.7), contentPane.getHeight());
	    generalContentPane.setColor(0x44808080);
	    contentPane.addChild(generalContentPane);
	    
	    var generalPanel = new TPanelElement(1, 1, generalContentPane.getWidth()-2, generalContentPane.getHeight() - 2);
	    generalPanel.setOutlineColor(0xff1c1c1c);
	    generalPanel.setBackgroundColor(0x00000000);
	    generalContentPane.addChild(generalPanel);
	    
	    // options screen select
	    var optionsNavPanelPane = new TFillColorElement(
	        generalContentPane.getEndX() + 5, generalContentPane.getY(),
	        (contentPane.getWidth() - generalContentPane.getWidth()) - 5, contentPane.getHeight() / 2);
	    optionsNavPanelPane.setColor(0x44808080);
	    contentPane.addChild(optionsNavPanelPane, false);
	    
	    var optionsTitleHousing = new TPanelElement(1, 1, optionsNavPanelPane.getWidth()-2, optionsNavPanelPane.getHeight() - 2);
	    optionsTitleHousing.setOutlineColor(0xff1c1c1c);
	    optionsTitleHousing.setBackgroundColor(0x00000000);
	    optionsNavPanelPane.addChild(optionsTitleHousing);
	    
	    var mainTittle = new TLabelElement(optionsTitleHousing.getWidth()/2-50, 7, 100, 10);
	    mainTittle.setText(getTitle());
	    mainTittle.setTextColor(0xffffffff);
	    mainTittle.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
	    optionsTitleHousing.addChild(mainTittle);
	    
	    var optionsNavPanel = new TPanelElement(0, 20, optionsNavPanelPane.getWidth(), optionsNavPanelPane.getHeight()-20);
	    optionsNavPanel.setOutlineColor(0xff1c1c1c);
	    optionsNavPanel.setBackgroundColor(0x00000000);
	    optionsTitleHousing.addChild(optionsNavPanel);
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
