package geek.winterthings.blocks;

import javax.annotation.Nullable;

import geek.winterthings.WTRegistries;
import geek.winterthings.entity.BaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.TeamCommand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;

public class BaseBlock extends Block implements EntityBlock{

    public BaseBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState p_49849_, @Nullable LivingEntity player,
            ItemStack p_49851_) {
        super.setPlacedBy(level, pos, p_49849_, player, p_49851_);
        BlockEntity be = level.getBlockEntity(pos);
        PlayerTeam team = new PlayerTeam(level.getScoreboard(), player.getName().getString() + "'s Team");
        if(be instanceof BaseBlockEntity blockEntity){
            blockEntity.setTeam(team);
        }
                
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
