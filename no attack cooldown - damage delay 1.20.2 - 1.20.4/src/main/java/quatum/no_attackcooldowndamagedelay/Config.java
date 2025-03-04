package quatum.no_attackcooldowndamagedelay;

import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = NoAttackCooldown_DamageDelay.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> DamageDelay = BUILDER
            .comment("Damage types with delay between hits.")
            .defineListAllowEmpty("DamageDelay", () -> List.of(
                    DamageTypes.IN_FIRE.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.CACTUS.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.HOT_FLOOR.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.FREEZE.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.LAVA.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.FELL_OUT_OF_WORLD.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.SWEET_BERRY_BUSH.toString().split("/ ")[1].split("]")[0],
                    DamageTypes.IN_WALL.toString().split("/ ")[1].split("]")[0]
            ),value -> true);
    private static final ModConfigSpec.BooleanValue NoAttackCooldown = BUILDER
            .comment("Removes the players attack cooldown.").define("NoAttackCooldown", true);
    private static final ModConfigSpec.BooleanValue NoDamageDelay = BUILDER
            .comment("Removes the delay between hits.").define("NoDamageDelay",true);
    private static final ModConfigSpec.BooleanValue RemoveCooldownIndicator = BUILDER
            .comment("Removes the cooldown indikator wehen you punch").define("RemoveCooldownIndicator", true);
    private static final ModConfigSpec.BooleanValue LogDamage = BUILDER
            .comment("Logs the damage Types in the logs").define("LogDamage", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean NoAttackCooldownValue = true;
    public static boolean NoDamageDelayValue = true;
    public static boolean RemoveCooldownIndicatorValue = true;
    public static boolean LogDamageValue=false;
    public static List<String> damageTypesListValue = new ArrayList<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        NoAttackCooldownValue=NoAttackCooldown.get();
        NoDamageDelayValue=NoDamageDelay.get();
        RemoveCooldownIndicatorValue=RemoveCooldownIndicator.get();
        damageTypesListValue= (List<String>) DamageDelay.get();
        LogDamageValue=LogDamage.get();

    }
}
