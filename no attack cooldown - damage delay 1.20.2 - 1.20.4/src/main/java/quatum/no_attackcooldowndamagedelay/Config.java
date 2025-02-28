package quatum.no_attackcooldowndamagedelay;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
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
    /*
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> DamageDelay = BUILDER
            .comment("Damage types with delay between hits.")
            .defineListAllowEmpty("DamageDelay",List.of(""));*/
    private static final ModConfigSpec.BooleanValue NoAttackCooldown = BUILDER
            .comment("Removes the players attack cooldown.").define("NoAttackCooldown", true);
    private static final ModConfigSpec.BooleanValue NoDamageDelay = BUILDER
            .comment("Removes the delay between hits.").define("NoDamageDelay",true);
    private static final ModConfigSpec.BooleanValue RemoveCooldownIndicator = BUILDER
            .comment("Removes the cooldown indikator wehen you punch").define("RemoveCooldownIndicator", true);
    static final ModConfigSpec SPEC = BUILDER.build();
    public static boolean NoAttackCooldownValue = true;
    public static boolean NoDamageDelayValue = true;
    public static boolean RemoveCooldownIndicatorValue = true;
    public static List<ResourceKey<DamageType>> damageTypesList = new ArrayList<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        NoAttackCooldownValue=NoAttackCooldown.get();
        NoDamageDelayValue=NoDamageDelay.get();
        RemoveCooldownIndicatorValue=RemoveCooldownIndicator.get();
        damageTypesList.add(DamageTypes.IN_FIRE);
        damageTypesList.add(DamageTypes.CACTUS);
        damageTypesList.add(DamageTypes.HOT_FLOOR);
        damageTypesList.add(DamageTypes.FREEZE);
        damageTypesList.add(DamageTypes.LAVA);
        damageTypesList.add(DamageTypes.FELL_OUT_OF_WORLD);
        damageTypesList.add(DamageTypes.SWEET_BERRY_BUSH);
        damageTypesList.add(DamageTypes.IN_WALL);
    }
}
