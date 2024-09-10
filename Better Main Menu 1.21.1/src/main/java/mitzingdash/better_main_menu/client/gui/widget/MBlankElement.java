package mitzingdash.better_main_menu.client.gui.widget;

import io.github.thecsdev.tcdcommons.api.client.gui.other.TBlankElement;
import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.util.annotations.Virtual;

public class MBlankElement extends TBlankElement {

    public MBlankElement(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int borderColor = 0xff000000;
    public @Virtual @Override void render(TDrawContext pencil){
        pencil.drawTBorder(borderColor);
    }
}
