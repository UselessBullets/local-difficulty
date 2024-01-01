package useless.localdif.mixins;

import net.minecraft.client.gui.options.components.ButtonComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ButtonComponent.class, remap = false)
public interface ButtonComponentAccessor {
	@Accessor
	String getTranslationKey();
}
