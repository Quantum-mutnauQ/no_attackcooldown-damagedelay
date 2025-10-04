package quatum.no_attackcooldowndamagedelay;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = NoAttackCooldown_DamageDelay.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();



    // a list of strings that are treated as resource locations for items

    private static final ModConfigSpec.ConfigValue<List<? extends String>> DamageDelay = BUILDER
            .comment("Damage types with delay between hits.")
            .defineListAllowEmpty("DamageDelay", () -> List.of(
                    damageType_to_CorospoigString(DamageTypes.IN_FIRE),
                    damageType_to_CorospoigString(DamageTypes.CACTUS),
                    damageType_to_CorospoigString(DamageTypes.HOT_FLOOR),
                    damageType_to_CorospoigString(DamageTypes.FREEZE),
                    damageType_to_CorospoigString(DamageTypes.LAVA),
                    damageType_to_CorospoigString(DamageTypes.FELL_OUT_OF_WORLD),
                    damageType_to_CorospoigString(DamageTypes.SWEET_BERRY_BUSH),
                    damageType_to_CorospoigString(DamageTypes.IN_WALL)
            ),value -> true);
    private static final ModConfigSpec.ConfigValue<List<? extends String>> BlacklistedEntitys = BUILDER
            .comment("The Entity's with a delay between hits")
            .defineListAllowEmpty("BlacklistedEntitys",() -> List.of(
                    "minecraft:magma_cube",
                    "minecraft:slime"
            ),value-> true);
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
    public static List<String> blacklistedEntitysValue = new ArrayList<>();

    public static String damageType_to_CorospoigString(ResourceKey<DamageType> type){
        return type.toString().split("/ ")[1].split("]")[0];
    }
    public static String entity_to_CotospoigStrig(Entity entity){
        return String.valueOf(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()));
    }
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        NoAttackCooldownValue=NoAttackCooldown.get();
        NoDamageDelayValue=NoDamageDelay.get();
        RemoveCooldownIndicatorValue=RemoveCooldownIndicator.get();
        damageTypesListValue= (List<String>) DamageDelay.get();
        blacklistedEntitysValue=(List<String>) BlacklistedEntitys.get();
        LogDamageValue=LogDamage.get();

    }
}
