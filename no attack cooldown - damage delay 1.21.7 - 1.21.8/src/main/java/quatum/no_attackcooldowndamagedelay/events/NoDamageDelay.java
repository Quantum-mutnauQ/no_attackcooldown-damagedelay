package quatum.no_attackcooldowndamagedelay.events;

import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import quatum.no_attackcooldowndamagedelay.Config;
import quatum.no_attackcooldowndamagedelay.NoAttackCooldown_DamageDelay;

import java.util.Objects;

@EventBusSubscriber
public class NoDamageDelay {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        if (event == null || event.getEntity() == null) {
            return;
        }

        String damageType = Config.damageType_to_CorospoigString(Objects.requireNonNull(event.getSource().typeHolder().unwrapKey().get()));
        var sourceEntity = event.getSource().getEntity();

        if (Config.LogDamageValue) {
            NoAttackCooldown_DamageDelay.LOGGER.info(damageType);
        }

        if (Config.NoDamageDelayValue && !event.getEntity().level().isClientSide()) {
            if (!Config.damageTypesListValue.contains(damageType)&&(sourceEntity == null || !Config.blacklistedEntitysValue.contains(Config.entity_to_CotospoigStrig(sourceEntity)))) {
                event.getEntity().invulnerableTime = 0;
            }
        }
    }
}
