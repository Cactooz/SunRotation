package dev.cactooz.sunrotation.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.irisshaders.iris.uniforms.CelestialUniforms;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Pseudo
@Mixin(CelestialUniforms.class)
public class IrisCelestialUniformsMixin {
	@ModifyExpressionValue(method = "getCelestialPosition",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lorg/joml/Matrix4f;<init>(Lorg/joml/Matrix4fc;)V"
					)
			),
			at = @At(
					value = "CONSTANT",
					target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;",
					args = "floatValue=-90.0",
					ordinal = 0
			)
	)
	private float unRotateCelestial(float original) {
		return 0f;
	}
	
	@ModifyExpressionValue(method = "getCelestialPositionInWorldSpace",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lorg/joml/Matrix4f;identity()Lorg/joml/Matrix4f;"
					)
			),
			at = @At(
					value = "CONSTANT",
					target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;",
					args = "floatValue=-90.0",
					ordinal = 0
			)
	)
	private float unRotateWorldSpace(float original) {
		return 0f;
	}
}
