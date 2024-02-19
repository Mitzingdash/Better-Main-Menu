package mitzingdash.better_main_menu.client.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mitzingdash.better_main_menu.client.gui.screen.BmmScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
	
	@Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
	private void onSetScreen(@Nullable Screen screen, CallbackInfo ci) {
		
		if(screen instanceof TitleScreen){
			System.out.println("womb womb");
			MinecraftClient.getInstance().setScreen(new BmmScreen().getAsScreen());
			ci.cancel();
			return;
		}
		
	}

}
