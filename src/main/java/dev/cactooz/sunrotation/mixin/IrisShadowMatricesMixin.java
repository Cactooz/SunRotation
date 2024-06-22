package dev.cactooz.sunrotation.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.irisshaders.iris.shadows.ShadowMatrices;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ShadowMatrices.class)
public class IrisShadowMatricesMixin {
	@Inject(method = "createBaselineModelViewMatrix",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lorg/joml/Matrix4f;translate(FFF)Lorg/joml/Matrix4f;"
					)
			),
			at = @At(value = "RETURN")
	)
	private static void yAxisInject(PoseStack target, float shadowAngle, float sunPathRotation, CallbackInfo ci) {
		target.mulPose(Axis.YP.rotationDegrees(-90.0f));
	}
}
