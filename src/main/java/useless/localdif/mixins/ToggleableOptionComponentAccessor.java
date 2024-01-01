package useless.localdif.mixins;

import net.minecraft.client.gui.GuiSliderInteger;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ToggleableOptionComponent.class, remap = false)
public interface ToggleableOptionComponentAccessor {
	@Accessor
	GuiSliderInteger getSlider();
}
