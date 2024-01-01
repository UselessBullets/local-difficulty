package useless.localdif.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.enums.Difficulty;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.localdif.LocalDifficulty;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
	@Shadow
	public World theWorld;

	@Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/enums/Difficulty;id()I"))
	private int getDifficultyFromGamerule(Difficulty instance){
		return theWorld.getGameRule(LocalDifficulty.difficulty);
	}
	@Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setAllowedMobSpawns(ZZ)V"))
	private void setSpawnValueProperly(World instance, boolean flag, boolean flag1){
		instance.setAllowedMobSpawns(theWorld.getGameRule(LocalDifficulty.difficulty) != 0, flag1);
	}
}
