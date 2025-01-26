package quatum.no_attackcooldowndamagedelay.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import quatum.no_attackcooldowndamagedelay.Config;

import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class NoDamageDelay
{
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event != null && event.getEntity() != null && Config.NoDamageDelayValue) {
            AtomicBoolean is = new AtomicBoolean(false);
            Config.damageTypesList.forEach(damageTypeResourceKey -> {
                if (event.getSource().is(damageTypeResourceKey))
                    is.set(true);
            });
            if (!is.get() && !event.getEntity().level().isClientSide())
                event.getEntity().invulnerableTime = 0;



        }
    }
}
