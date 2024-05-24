package dev.cactooz.sunrotation.mixin;

import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
	@ModifyArgs(method = "setupColor",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/client/multiplayer/ClientLevel;getSunAngle(F)F")
			),
			at = @At(
					value = "INVOKE",
					target = "Lorg/joml/Vector3f;<init>(FFF)V",
					ordinal = 0))
	private static void reset(Args args) {
		float x = args.get(0);
		args.set(0, 0.0f);
		args.set(1, 0.0f);
		args.set(2, -x);
	}
}
