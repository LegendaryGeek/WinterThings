package geek.winterthings.nexus;

import geek.winterthings.WTRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.PlayerTeam;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Core team block
 */
public class NexusBlockEntity extends BlockEntity{

    private static final String NBT_TEAM = "team";

    private PlayerTeam team;

    // Temp storage as level doesn't exist on load
    private String teamNameTemp;

    public NexusBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(WTRegistries.NEXUS_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        if(team != null) {
            nbt.putString(NBT_TEAM, team.getName());
        }
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        if(nbt.contains(NBT_TEAM, 8)) {
            teamNameTemp = nbt.getString(NBT_TEAM);
        }
    }

    /**
     * Gets the team, will lazy load team
     *
     * @return team
     */
    public @Nullable PlayerTeam getTeam() {
        if(team == null && teamNameTemp != null) {
            team = this.level.getScoreboard().getPlayerTeam(teamNameTemp);
            teamNameTemp = null;
        }
        return team;
    }

    /**
     * Sets the team
     *
     * @param team the team to set
     */
    public void setTeam(@Nullable PlayerTeam team) {
        this.team = team;
    }
}
