package geek.winterthings;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.BlockEntity;

import geek.winterthings.nexus.NexusBlock;
import geek.winterthings.nexus.NexusBlockEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class WTRegistries {

    // Registries
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WinterThings.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WinterThings.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BETYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WinterThings.MODID);


    // Items
    public static final RegistryObject<Item> WINTER_JAM = registerItem("winter_jam", () -> new Item(
            new Item.Properties().food(
                    new FoodProperties.Builder()
                            .alwaysEat()
                            .nutrition(1)
                            .saturationMod(2f)
                            .build()
            )));


    // Blocks
    public static final RegistryObject<Block> NEXUS_BLOCK = registerBlock("nexus", () -> new NexusBlock(BlockBehaviour.Properties.of().strength(2.0f)));

    // Block Entities
    public static final RegistryObject<BlockEntityType<NexusBlockEntity>> NEXUS_BLOCK_ENTITY = registerBlockEntity("nexus", NexusBlockEntity::new, NEXUS_BLOCK);


    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BETYPES.register(modEventBus);
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<? extends Block> blockFactory) {
        RegistryObject<Block> result = BLOCKS.register(name, blockFactory);
        ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties()));
        return result;
    }

    public static RegistryObject<Item> registerItem(String name, Supplier<? extends Item> itemFactory) {
        return ITEMS.register(name, itemFactory);
    }

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> factory, RegistryObject<Block> block) {
        return BETYPES.register(name, () -> BlockEntityType.Builder.of(factory, block.get()).build(null));
    }
}
