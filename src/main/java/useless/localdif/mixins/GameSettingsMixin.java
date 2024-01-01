package useless.localdif.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.EnumOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.Option;
import net.minecraft.client.option.enums.Difficulty;
import net.minecraft.core.data.gamerule.GameRuleCollection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.localdif.LocalDifficulty;

@Mixin(value = GameSettings.class, remap = false)
public class GameSettingsMixin {
	@Shadow
	public EnumOption<Difficulty> difficulty;

	@Shadow
	@Final
	public Minecraft mc;

	@Inject(method = "optionChanged(Lnet/minecraft/client/option/Option;)V", at = @At("TAIL"))
	private void difficultyOptionManipulation(Option<?> option, CallbackInfo ci){
		if (option == difficulty && mc.theWorld != null){
			GameRuleCollection gameRules = mc.theWorld.getLevelData().getGameRules();
			gameRules.setValue(LocalDifficulty.difficulty, difficulty.value.id());
		}
	}
}
