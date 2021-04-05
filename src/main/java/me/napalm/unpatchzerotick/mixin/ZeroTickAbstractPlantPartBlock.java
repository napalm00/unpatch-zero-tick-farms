/*
    Copyright 2021 naPalm (hello@napalm.me)

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
    TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package me.napalm.unpatchzerotick.mixin;

import me.napalm.unpatchzerotick.UnpatchZeroTick;
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
        if(!UnpatchZeroTick.getConfig().isZeroTickUnpatchEnabled || !UnpatchZeroTick.getConfig().isVinesZeroTickEnabled) {
            return;
        }

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

