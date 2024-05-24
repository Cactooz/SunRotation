package dev.cactooz.sunrotation.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@ModifyExpressionValue(method = "renderSky",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/client/multiplayer/ClientLevel;getSunAngle(F)F")
			),
			at = @At(
					value = "CONSTANT",
					target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;",
					args = "floatValue=90.0",
					ordinal = 0))
	private float unRotateSunBloom(float original) {
		return 0f;
	}
	
	@ModifyExpressionValue(method = "renderSky",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/client/multiplayer/ClientLevel;getRainLevel(F)F")
			),
			at = @At(
					value = "CONSTANT",
					target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;",
					args = "floatValue=-90.0",
					ordinal = 0))
	private float unRotateSun(float original) {
		return 0f;
	}
}
