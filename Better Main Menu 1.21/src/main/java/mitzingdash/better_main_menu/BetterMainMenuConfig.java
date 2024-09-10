package mitzingdash.better_main_menu;

import java.nio.file.InvalidPathException;

import io.github.thecsdev.tcdcommons.api.config.AutoConfig;
import io.github.thecsdev.tcdcommons.api.config.annotation.SerializedAs;

public class BetterMainMenuConfig extends AutoConfig {

	public BetterMainMenuConfig() throws InvalidPathException {
		super("BMMSettings");
	}

	public @SerializedAs("background-texture-id") String btid = "better_main_menu:textures/gui/backgrounds/valley.png";
	public @SerializedAs("main-icon") String logo = "better_main_menu:textures/gui/icons/logo.png";
	public @SerializedAs("debug-mode") boolean debug = false;
}
