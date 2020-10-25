/*
    Copyright 2020 naPalm (hello@napalm.me)

    CC0 1.0 Universal
*/

package me.napalm.unpatchzerotick.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractPlantPartBlock.class)
public class ZeroTickAbstractPlantPartBlock  {

    @Inject(at = @At("TAIL"), method = "scheduledTick")
    public void scheduledTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, CallbackInfo info) {

        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
            return;
        }

        Block curBlock = state.getBlock();
        BlockPos curPos = pos;

        // if weeping vines, find stem by going down
        if(curBlock.toString().contains("weeping")) {
            while (curBlock.toString().contains("_plant")) {
                curPos = curPos.down();
                curBlock = world.getBlockState(curPos).getBlock();
            }
        }
        else {
            // otherwise find stem by going up
            while (curBlock.toString().contains("_plant")) {
                curPos = curPos.up();
                curBlock = world.getBlockState(curPos).getBlock();
            }
        }

        if(!world.isAir(curPos)) {
            // once stem is found, call randomTick on it
            try {
                AbstractPlantStemBlock stemBlock = AbstractPlantStemBlock.class.cast(curBlock);
                stemBlock.randomTick(world.getBlockState(curPos), world, curPos, random);
            } catch (Exception ex) {
            }
        }
    }
}

