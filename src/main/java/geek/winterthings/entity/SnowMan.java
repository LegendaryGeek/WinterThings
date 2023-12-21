package geek.winterthings.entity;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import net.minecraft.server.commands.TeamCommand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class SnowMan extends PathfinderMob {

    protected SnowMan(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    
    
}
