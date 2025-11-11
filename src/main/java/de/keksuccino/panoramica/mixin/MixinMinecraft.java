package de.keksuccino.panoramica.mixin;

import de.keksuccino.panoramica.LegacyPanoramicaHandler;
import de.keksuccino.panoramica.PanoramicaHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    private static LegacyPanoramicaHandler legacyHandler;
    private static PanoramicaHandler handler;

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        if (legacyHandler == null && handler == null) {
            // Initialize handlers on first tick
            legacyHandler = new LegacyPanoramicaHandler();
            handler = new PanoramicaHandler();
        }
        
        // Call handlers directly
        if (legacyHandler != null) {
            legacyHandler.onTick();
        }
        if (handler != null) {
            handler.onClientTick();
        }
    }

}
