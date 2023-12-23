package geek.winterthings.entity;

import geek.winterthings.WTRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BaseBlockEntity extends BlockEntity{

    public BaseBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(WTRegistries.BASE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }
    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        // TODO Auto-generated method stub
        super.saveAdditional(p_187471_);
    }
}
