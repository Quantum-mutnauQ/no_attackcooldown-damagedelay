package quatum.no_attackcooldowndamagedelay.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quatum.no_attackcooldowndamagedelay.Config;

import java.util.Map;
import java.util.UUID;

@Mixin(Items.class)
public class ItemsMixin {

    // Wert von Item.BASE_ATTACK_DAMAGE_UUID (protected, daher hier als Konstante)
    private static final UUID BASE_ATTACK_DAMAGE_UUID =
            UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    @Inject(method = "registerItem(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item;",
            at = @At("RETURN"))
    private static void applyNewAttackdamage(ResourceLocation id, Item item, CallbackInfoReturnable<Item> cir) {
        Config.loadDamageValues();

        Float damage = Config.CustomDamageValuesValue.get(id.toString());
        if (damage == null)
            return;

        Item registered = cir.getReturnValue();

        Multimap<Attribute, AttributeModifier> existing = registered.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        for (Map.Entry<Attribute, AttributeModifier> entry : existing.entries()) {
            if (entry.getKey() == Attributes.ATTACK_DAMAGE && BASE_ATTACK_DAMAGE_UUID.equals(entry.getValue().getId())) {
                continue;
            }
            builder.put(entry.getKey(), entry.getValue());
        }

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage, AttributeModifier.Operation.ADDITION));

        Multimap<Attribute, AttributeModifier> result = builder.build();

        if (registered instanceof SwordItem)
            ((SwordItemAccessor) registered).setDefaultModifiers(result);
        else if (registered instanceof DiggerItem)
            ((DiggerItemAccessor) registered).setDefaultModifiers(result);

    }
}