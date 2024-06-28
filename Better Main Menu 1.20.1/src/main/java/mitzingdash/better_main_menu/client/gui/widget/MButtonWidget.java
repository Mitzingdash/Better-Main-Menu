package mitzingdash.better_main_menu.client.gui.widget;

import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.client.gui.widget.TButtonWidget;
import io.github.thecsdev.tcdcommons.api.util.annotations.Virtual;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import net.minecraft.util.Identifier;

public class MButtonWidget extends TButtonWidget {
	

	public MButtonWidget(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	
	public static final Identifier BTN_WIDGET = new Identifier("better_main_menu", "textures/gui/widgets.png");
	
	//TButtonWidget.java
	public @Virtual @Override void render(TDrawContext pencil)
	{
		if (isFocusedOrHovered()) pencil.drawTNineSlicedTexture(BTN_WIDGET, 0, 86, 200, 20, 5);
		else pencil.drawTNineSlicedTexture(BTN_WIDGET, 0, 66, 200, 20, 5);
		renderBackground(pencil);
		pencil.enableScissor(getX(), getY(), getEndX(), getEndY());
		pencil.drawTElementTextTH(this.text, HorizontalAlignment.CENTER);
		pencil.disableScissor();
	}
	

}
