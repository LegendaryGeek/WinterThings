package geek.winterthings;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;

public class SnowEffect extends MobEffect{

    protected SnowEffect(MobEffectCategory category, int liquidColorIn) {
        super(category, liquidColorIn);
    }

	@Override
	public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier)
	{
        entityLivingBaseIn.level().gameEvent(GameEvent.BLOCK_PLACE, entityLivingBaseIn.blockPosition(), GameEvent.Context.of(entityLivingBaseIn, Blocks.SNOW.defaultBlockState()));
	}
    
}
