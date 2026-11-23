package quatum.no_attackcooldowndamagedelay.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.DiggerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DiggerItem.class)
public interface DiggerItemAccessor {
    @Mutable
    @Accessor("defaultModifiers")
    void setDefaultModifiers(Multimap<Attribute, AttributeModifier> modifiers);
}