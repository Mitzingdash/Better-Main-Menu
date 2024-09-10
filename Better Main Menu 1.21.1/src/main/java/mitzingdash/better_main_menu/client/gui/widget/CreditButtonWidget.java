package mitzingdash.better_main_menu.client.gui.widget;

import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TButtonWidget;
import io.github.thecsdev.tcdcommons.api.util.annotations.Virtual;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;

public class CreditButtonWidget extends TButtonWidget {
	

	public CreditButtonWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	
	
	//TButtonWidget.java
	public @Virtual @Override void render(TDrawContext pencil)
	{
		pencil.enableScissor(getX(), getY(), getEndX(), getEndY());
		pencil.drawTElementTextTH(this.text, HorizontalAlignment.CENTER);
		pencil.disableScissor();
		if (isFocusedOrHovered()) pencil.drawHorizontalLine(getX(), getEndX(), getEndY()-2, 0xff00ff00);
	}
	

}