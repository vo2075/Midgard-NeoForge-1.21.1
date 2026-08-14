package net.sbeev.midgard.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DiagonalLeavesBlock extends LeavesBlock {

    public DiagonalLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        int i = getDistanceAt(neighborState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            level.scheduleTick(pos, this, 1);
        }
        return state;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDiagonalDistance(state, level, pos), 3);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(PERSISTENT)) {
            BlockState newState = updateDiagonalDistance(state, level, pos);
            if (newState.getValue(DISTANCE) == 7) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            } else if (state.getValue(DISTANCE) != newState.getValue(DISTANCE)) {
                level.setBlock(pos, newState, 3);
            }
        }
    }


    private BlockState updateDiagonalDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int shortestDistance = 7;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    mutablePos.setWithOffset(pos, x, y, z);
                    BlockState neighborState = level.getBlockState(mutablePos);

                    int distance = getDistanceAt(neighborState) + 1;
                    shortestDistance = Math.min(shortestDistance, distance);

                    if (shortestDistance == 1) {
                        break;
                    }
                }
                if (shortestDistance == 1) break;
            }
            if (shortestDistance == 1) break;
        }
        return state.setValue(DISTANCE, shortestDistance);
    }

    private static int getDistanceAt(BlockState neighborState) {
        if (neighborState.is(BlockTags.LOGS)) {
            return 0;
        }
        if (neighborState.getBlock() instanceof LeavesBlock) {
            return neighborState.getValue(DISTANCE);
        }
        return 7;
    }
}
