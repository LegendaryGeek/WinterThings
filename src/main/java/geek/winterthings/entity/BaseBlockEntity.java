package geek.winterthings.entity;

import geek.winterthings.WTRegistries;
import geek.winterthings.WinterThings;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.PlayerTeam;

public class BaseBlockEntity extends BlockEntity{

    private PlayerTeam team;

    public BaseBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(WTRegistries.BASE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        CompoundTag WTData = nbt.getCompound(WinterThings.MODID);
        WTData.putString("team", team.getName());
    }
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag WTData = nbt.getCompound(WinterThings.MODID);
        team = team.getScoreboard().getPlayerTeam(WTData.getString("team"));
    }

    /**
     * @return the team
     */
    public PlayerTeam getTeam() {
        return team;
    }
    /**
     * @param team the team to set
     */
    public void setTeam(PlayerTeam team) {
        this.team = team;
    }
}
