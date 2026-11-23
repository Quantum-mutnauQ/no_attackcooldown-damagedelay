package quatum.no_attackcooldowndamagedelay.mixin;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quatum.no_attackcooldowndamagedelay.Config;

@Mixin(Items.class)
public class ItemsMixin {

    @Inject(method = "registerItem(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item;",at = @At("RETURN"))
    private static void applyNewAttackdamage(ResourceKey<Item> id, Item item, CallbackInfoReturnable<Item> cir) {
        Config.loadDamageValues();

        Float damage = Config.CustomDamageValuesValue.get(id.location().toString());

        if (damage == null)
            return;

        Item registered = cir.getReturnValue();

        ItemAttributeModifiers existing = registered.components().getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
            if (entry.attribute().equals(Attributes.ATTACK_DAMAGE) && entry.modifier().id().equals(Item.BASE_ATTACK_DAMAGE_ID)) {
                continue;
            }
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        }

        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, damage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);

        ((ItemAccessor) registered).setComponents(DataComponentMap.composite(registered.components(), DataComponentMap.builder().set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build()).build()));
    }
}