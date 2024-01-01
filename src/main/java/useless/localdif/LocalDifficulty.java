package useless.localdif;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.components.OptionsComponent;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.enums.Difficulty;
import net.minecraft.core.data.gamerule.GameRule;
import net.minecraft.core.data.gamerule.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import useless.localdif.mixins.ButtonComponentAccessor;
import useless.localdif.mixins.OptionsCategoryAccessor;


public class LocalDifficulty implements ModInitializer {
    public static final String MOD_ID = "localdif";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static GameRuleInteger difficulty = GameRules.register(new GameRuleInteger("difficulty", 2));
	public static ToggleableOptionComponent<Difficulty> suffering;
    @Override
    public void onInitialize() {
		LOGGER.info("LocalDifficulty initialized.");
    }
	public static ToggleableOptionComponent<Difficulty> getDifficultyComp(){
		if (suffering != null){
			return suffering;
		}
		for (OptionsComponent component : OptionsPages.GENERAL.getComponents()){
			if (component instanceof OptionsCategory){
				OptionsCategory category = (OptionsCategory)component;
				for (OptionsComponent component2 : ((OptionsCategoryAccessor)category).getComponents()){
					if (component2 instanceof  ToggleableOptionComponent){
						ToggleableOptionComponent<?> tChamp = (ToggleableOptionComponent<?>) component2;
						System.out.println(((ButtonComponentAccessor)tChamp).getTranslationKey());
						if (((ButtonComponentAccessor)tChamp).getTranslationKey().equals("options.difficulty")){
							suffering = (ToggleableOptionComponent<Difficulty>) tChamp;
							return suffering;
						}
					}
				}
			}
		}
		throw new RuntimeException("suffering cannot be found!!!");
	}
}
