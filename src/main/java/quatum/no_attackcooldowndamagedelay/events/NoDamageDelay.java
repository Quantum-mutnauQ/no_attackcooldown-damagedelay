package quatum.no_attackcooldowndamagedelay.events;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
                    event.getEntity().invulnerableTime =0;


        }
    }
}
