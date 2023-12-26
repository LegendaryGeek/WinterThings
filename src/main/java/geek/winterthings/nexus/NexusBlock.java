package geek.winterthings.nexus;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.scores.PlayerTeam;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class NexusBlock extends Block implements EntityBlock {

    public NexusBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity entity,
                            @NotNull ItemStack p_49851_) {
        super.setPlacedBy(level, pos, state, entity, p_49851_);

        if(entity instanceof Player player && !level.isClientSide()) {

            final BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof NexusBlockEntity nexus) {
                final String playerName = player.getGameProfile().getName();

                // First check if the player is already on a team, if no team then create
                PlayerTeam currentTeam = level.getScoreboard().getPlayersTeam(playerName);
                if(currentTeam == null) {
                    currentTeam = level.getScoreboard().addPlayerTeam(playerName);
                    level.getScoreboard().addPlayerToTeam(playerName, currentTeam);
                }

                nexus.setTeam(currentTeam);
            }
        }
    }

    @Override
    @Deprecated
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state2,
                         boolean p_60519_) {
        super.onRemove(state, level, pos, state2, p_60519_);
    }

    @Override
    @Deprecated
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player,
                                 @NotNull InteractionHand hand, @NotNull BlockHitResult hitScan) {
        final BlockEntity blockEntity = level.getBlockEntity(pos);
        if(blockEntity instanceof NexusBlockEntity nexus) {
            if(!level.isClientSide()) {
                player.sendSystemMessage(Component.literal("Team: " + Optional.ofNullable(nexus.getTeam()).map(PlayerTeam::getName).orElse("---")));
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hitScan);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new NexusBlockEntity(pos, state);
    }

}
