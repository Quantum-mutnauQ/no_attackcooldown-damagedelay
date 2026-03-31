package quatum.no_attackcooldowndamagedelay.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quatum.no_attackcooldowndamagedelay.Config;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "resetAttackStrengthTicker",at = @At("HEAD"),cancellable = true)
    public void resetAttackStrengthTicker(CallbackInfo ci) {
        if(Config.NoAttackCooldownValue)
            ci.cancel();
    }
    @Inject(method = "getAttackStrengthScale",at = @At("HEAD"),cancellable = true)
      public void getAttackStrengthScale(float p_36404_, CallbackInfoReturnable<Float> cir) {
        if(Config.NoAttackCooldownValue)
        cir.setReturnValue(1F);
    }
}
