package geek.winterthings.items;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

public class SnowmanSpawner extends Item {

    EntityType<?> entityToSpawn;

    public SnowmanSpawner(Properties properties, @NonNull EntityType<?> entityToSpawn) {
        super(properties);
        this.entityToSpawn = entityToSpawn;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int p_41431_) {
        // TODO Auto-generated method stub
        super.onUseTick(level, entity, stack, p_41431_);
        if(entity instanceof Player player){
            //SpawnEggItem
            entityToSpawn.spawn((ServerLevel)level, getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY).getBlockPos(), MobSpawnType.SPAWN_EGG);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        entityToSpawn.spawn((ServerLevel)level, getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY).getBlockPos(), MobSpawnType.SPAWN_EGG);
        return super.use(level, player, usedHand);
    }
    

    
}
