/*
    Copyright 2020 naPalm (hello@napalm.me)

    CC0 1.0 Universal
*/

package me.napalm.unpatchzerotick.mixin;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BambooBlock.class)
public class BambooBlockTickMixin {
	@Shadow
	public void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {}

	@Inject(at = @At("TAIL"), method = "scheduledTick")
	public void scheduledTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, CallbackInfo info) {
		if(!world.isAir(pos.down())) {
			this.randomTick(state, world, pos, random);
		}
	}
}
