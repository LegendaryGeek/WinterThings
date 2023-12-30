package geek.winterthings.items;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.BlockHitResult;
import org.checkerframework.checker.nullness.qual.NonNull;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.NotNull;

public class SnowmanSpawner extends Item {

    private final Supplier<? extends EntityType<? extends Mob>> entityToSpawn;

    public SnowmanSpawner(Properties properties, Supplier<? extends EntityType<? extends Mob>> type) {
        super(properties);
        entityToSpawn = type;
    }

    // @Override
    // public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int p_41431_) {
    //     super.onUseTick(level, entity, stack, p_41431_);
    //     if(entity instanceof Player player){
    //         //ForgeSpawnEggItem
    //         entityToSpawn.spawn((ServerLevel)level, getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY).getBlockPos(), MobSpawnType.SPAWN_EGG);
    //     }
    // }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        final ItemStack heldItem = player.getItemInHand(usedHand);
        if(!level.isClientSide && level instanceof ServerLevel serverLevel){
            final BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            final BlockPos pos = result.getBlockPos().offset(result.getDirection().getNormal());
            final Entity entity = entityToSpawn.get().spawn(serverLevel, pos, MobSpawnType.SPAWN_EGG);
            if(entity == null) {
                return InteractionResultHolder.fail(heldItem);
            }
        }
        return InteractionResultHolder.sidedSuccess(heldItem, level.isClientSide());
    }
}
