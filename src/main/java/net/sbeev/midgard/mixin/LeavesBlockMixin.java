package net.sbeev.midgard.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void midgard$onTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        int oldDistance = (Integer)state.getValue(LeavesBlock.DISTANCE);

        BlockState newState = midgard$updateDiagonalDistance(state, level, pos);
        int newDistance = newState.getValue(LeavesBlock.DISTANCE);

        if (oldDistance != newDistance) {
            level.setBlock(pos, newState, 3);

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos neighborPos = pos.offset(x, y, z);
                        BlockState neighborState = level.getBlockState(neighborPos);

                        if (neighborState.getBlock() instanceof LeavesBlock) {
                            level.scheduleTick(neighborPos, neighborState.getBlock(), 1);
                        }
                    }
                }
            }
        }
        ci.cancel();
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void midgard$onRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!state.getValue(LeavesBlock.PERSISTENT)) {
            BlockState newState = midgard$updateDiagonalDistance(state, level, pos);
            if (newState.getValue(LeavesBlock.DISTANCE) == 7) {
                Block.dropResources(state, level, pos);
                level.removeBlock(pos, false);
            } else if (state.getValue(LeavesBlock.DISTANCE) != newState.getValue(LeavesBlock.DISTANCE)) {
                level.setBlock(pos, newState, 3);
            }
        }
        ci.cancel();
    }

    @Unique
    private BlockState midgard$updateDiagonalDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int shortestDistance = 7;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    mutablePos.setWithOffset(pos, x, y, z);
                    BlockState neighborState = level.getBlockState(mutablePos);

                    int distance = 7;
                    if (neighborState.is(BlockTags.LOGS)) {
                        distance = 0;
                    } else if (neighborState.getBlock() instanceof LeavesBlock) {
                        distance = neighborState.getValue(LeavesBlock.DISTANCE);
                    }

                    shortestDistance = Math.min(shortestDistance, distance + 1);
                    if (shortestDistance == 1) break;
                }
                if (shortestDistance == 1) break;
            }
            if (shortestDistance == 1) break;
        }

        return state.setValue(LeavesBlock.DISTANCE, shortestDistance);
    }
}