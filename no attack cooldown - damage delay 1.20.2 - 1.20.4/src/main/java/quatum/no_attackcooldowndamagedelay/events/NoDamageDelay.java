package quatum.no_attackcooldowndamagedelay.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import quatum.no_attackcooldowndamagedelay.Config;
import quatum.no_attackcooldowndamagedelay.NoAttackCooldown_DamageDelay;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class NoDamageDelay
{
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event == null || event.getEntity() == null) {
            return;
        }

        String damageTypeKey = Objects.requireNonNull(event.getSource().typeHolder().unwrapKey().get()).toString();
        String damageType = damageTypeKey.split("/ ")[1].split("]")[0];

        if (Config.LogDamageValue) {
            NoAttackCooldown_DamageDelay.LOGGER.info(damageTypeKey);
        }

        if (Config.NoDamageDelayValue && !event.getEntity().level().isClientSide()) {
            if (!Config.damageTypesListValue.contains(damageType)) {
                event.getEntity().invulnerableTime = 0;
            }
        }
    }
}
