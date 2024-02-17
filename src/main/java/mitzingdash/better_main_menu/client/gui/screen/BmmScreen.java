package mitzingdash.better_main_menu.client.gui.screen;

import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TButtonWidget;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.text.Text;

public class BmmScreen extends TScreenPlus {

	public BmmScreen() {
		super(Text.translatable("narrator.screen.title"));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		
		var btn_quit = new TButtonWidget(this.getWidth()-25, this.getHeight()-25, 20, 20);
		btn_quit.setText(Text.literal("X"));
		btn_quit.setTooltip(Tooltip.of(Text.translatable("menu.quit")));
		btn_quit.setOnClick(__ -> {
			getClient().scheduleStop();
		});
		addChild(btn_quit);
		
	}
	
	

}
