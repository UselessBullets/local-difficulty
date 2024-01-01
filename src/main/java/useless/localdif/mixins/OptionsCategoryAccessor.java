package useless.localdif.mixins;

import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.components.OptionsComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = OptionsCategory.class, remap = false)
public interface OptionsCategoryAccessor {
	@Accessor
	List<OptionsComponent> getComponents();
}
