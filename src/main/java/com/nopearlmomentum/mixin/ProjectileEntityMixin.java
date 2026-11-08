package com.nopearlmomentum.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Vanilla {@link ProjectileEntity#setVelocity(Entity, float, float, float, float, float)}
 * adds the shooter's velocity after applying throw speed. This inject strips that
 * inheritance for ender pearls so trajectory matches look direction only.
 */
@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {
	@Inject(
		method = "setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V",
		at = @At("TAIL")
	)
	private void nopearlmomentum$stripShooterVelocity(
		Entity shooter,
		float pitch,
		float yaw,
		float roll,
		float speed,
		float divergence,
		CallbackInfo ci
	) {
		ProjectileEntity self = (ProjectileEntity) (Object) this;
		if (!(self instanceof EnderPearlEntity)) {
			return;
		}

		// 1.21.11 adds Entity#getMovement() (not getVelocity) into pearl speed.
		Vec3d inherited = shooter.getMovement();
		double inheritedY = shooter.isOnGround() ? 0.0 : inherited.y;
		self.setVelocity(self.getVelocity().subtract(inherited.x, inheritedY, inherited.z));
	}
}
