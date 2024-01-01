package useless.localdif.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.enums.Difficulty;
import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.client.player.controller.PlayerControllerSP;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import useless.localdif.LocalDifficulty;

@Mixin(value = PlayerControllerSP.class, remap = false)
public class PlayerControllerSPMixin extends PlayerController {
	public PlayerControllerSPMixin(Minecraft minecraft) {
		super(minecraft);
	}

	@Override
	public void initLevel(World world) {
		if (world != null){
			final Difficulty[] difficulties = new Difficulty[]{Difficulty.PEACEFUL, Difficulty.EASY, Difficulty.NORMAL, Difficulty.HARD};
			mc.gameSettings.difficulty.set(difficulties[Math.min(Math.max(world.getGameRule(LocalDifficulty.difficulty), 0), 3)]);
			((ToggleableOptionComponentAccessor)LocalDifficulty.getDifficultyComp()).getSlider().sliderValue = world.getGameRule(LocalDifficulty.difficulty);
			((ToggleableOptionComponentAccessor)LocalDifficulty.getDifficultyComp()).getSlider().displayString = mc.gameSettings.difficulty.getDisplayString();
			mc.gameSettings.difficulty.onUpdate();
		}
	}
}
