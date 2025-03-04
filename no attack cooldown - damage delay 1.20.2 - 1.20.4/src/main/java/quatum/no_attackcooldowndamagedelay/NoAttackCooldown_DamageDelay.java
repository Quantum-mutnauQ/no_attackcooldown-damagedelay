package quatum.no_attackcooldowndamagedelay;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NoAttackCooldown_DamageDelay.MODID)
public class NoAttackCooldown_DamageDelay {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "no_attack_cooldown_damage_delay";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoAttackCooldown_DamageDelay(IEventBus modEventBus) {
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,Config.SPEC);
    }
}
