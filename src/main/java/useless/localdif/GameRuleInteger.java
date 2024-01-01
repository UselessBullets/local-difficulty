package useless.localdif;

import com.mojang.nbt.ByteTag;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.Tag;
import net.minecraft.core.data.gamerule.GameRule;

public class GameRuleInteger extends GameRule<Integer> {
	public GameRuleInteger(String key, Integer defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public void writeToNBT(CompoundTag tag, Integer value) {
		ByteTag ruleTag = new ByteTag();
		ruleTag.setValue((byte)((int)value));
		tag.put(this.getKey(), ruleTag);
	}

	@Override
	public Integer readFromNBT(CompoundTag tag) {
		Tag<?> ruleTag = tag.getTag(this.getKey());
		if (ruleTag instanceof ByteTag) {
			ByteTag ruleTagByte = (ByteTag)ruleTag;
			return Integer.valueOf(ruleTagByte.getValue());
		}
		return this.getDefaultValue();
	}

	@Override
	public Integer parseFromString(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
