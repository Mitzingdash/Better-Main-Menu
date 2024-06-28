package mitzingdash.better_main_menu.client.gui.screen;

import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.panel.TPanelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.util.interfaces.IParentScreenProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class BmmConfigScreen extends TScreenPlus implements IParentScreenProvider {

	public Screen parent = null;

	public BmmConfigScreen(Screen parent) {
		super(Text.translatable("narrator.screen.title"));
		this.parent = parent;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
	    // TODO Auto-generated method stub
	    var contentPane = new TFillColorElement(10, 10, getWidth()-20, getHeight()-20);
	    contentPane.setColor(0x44808080);
	    addChild(contentPane);
	    
	    var panel = new TPanelElement(1, 1, contentPane.getWidth()-2, contentPane.getHeight() - 2);
	    panel.setOutlineColor(0xff1c1c1c);
	    contentPane.addChild(panel);
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
