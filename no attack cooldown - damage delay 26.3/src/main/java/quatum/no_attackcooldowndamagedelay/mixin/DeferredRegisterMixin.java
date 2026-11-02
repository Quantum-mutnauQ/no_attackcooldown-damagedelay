package quatum.no_attackcooldowndamagedelay.mixin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quatum.no_attackcooldowndamagedelay.Config;

import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(DeferredRegister.Items.class)
public class DeferredRegisterMixin {

    @Inject(method = "registerItem(Ljava/lang/String;Ljava/util/function/Function;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/registries/DeferredItem;", at = @At("HEAD"))
    private static <I extends Item> void applyNewAttackdamage(String name, Function<Item.Properties, ? extends I> func, Supplier<Item.Properties> properties, CallbackInfoReturnable<DeferredItem<I>> cir){
        Config.loadDamageValues();

        if (!Config.CustomDamageValuesValue.containsKey(name))
            return;

        Float damage = Config.CustomDamageValuesValue.get(name);

        if (damage == null)
            return;

        ItemAttributeModifiers modifiers = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.parse("custom_attack_damage"), 10000, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();

        properties.get().attributes(modifiers);
    }
}
