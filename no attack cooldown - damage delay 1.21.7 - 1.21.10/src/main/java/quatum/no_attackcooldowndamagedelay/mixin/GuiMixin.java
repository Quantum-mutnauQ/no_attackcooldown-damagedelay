package quatum.no_attackcooldowndamagedelay.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quatum.no_attackcooldowndamagedelay.Config;

@Mixin(Gui.class)
public class GuiMixin {
    @Redirect(method = "renderCrosshair", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;crosshairPickEntity:Lnet/minecraft/world/entity/Entity;", opcode = Opcodes.GETFIELD))
    public Entity noCollDownRender(Minecraft instance) {
        if(Config.RemoveCooldownIndicatorValue)return null;
        return instance.crosshairPickEntity;
    }
}
