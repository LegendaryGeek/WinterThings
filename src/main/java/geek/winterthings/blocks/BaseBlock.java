package geek.winterthings.blocks;

import javax.annotation.Nullable;

import geek.winterthings.WTRegistries;
import geek.winterthings.entity.BaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BaseBlock extends Block implements EntityBlock{

    public BaseBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state2, boolean p_60570_) {
        super.onPlace(state, level, pos, state2, p_60570_);

    }

    @Override
    @Deprecated
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2,
            boolean p_60519_) {
        super.onRemove(state, level, pos, state2, p_60519_);
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_,
            InteractionHand p_60507_, BlockHitResult p_60508_) {
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BaseBlockEntity(pos, state);
    }

}
