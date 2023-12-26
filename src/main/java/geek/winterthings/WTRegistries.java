package geek.winterthings;

import geek.winterthings.entity.SnowMan;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.BlockEntity;

import geek.winterthings.nexus.NexusBlock;
import geek.winterthings.nexus.NexusBlockEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(modid = WinterThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WTRegistries {

    // Registries
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WinterThings.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WinterThings.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BETYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WinterThings.MODID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WinterThings.MODID);


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

    // Entities
    public static final RegistryObject<EntityType<SnowMan>> SNOW_MAN = registerEntity("snow_man", EntityType.Builder.of(SnowMan::new, MobCategory.MONSTER).sized(0.35F, 0.6F).clientTrackingRange(8).updateInterval(2));


    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BETYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(WTRegistries::registerAttributes);
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<? extends Block> blockFactory) {
        RegistryObject<Block> result = BLOCKS.register(name, blockFactory);
        ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties()));
        return result;
    }

    private static RegistryObject<Item> registerItem(String name, Supplier<? extends Item> itemFactory) {
        return ITEMS.register(name, itemFactory);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> factory, RegistryObject<Block> block) {
        return BETYPES.register(name, () -> BlockEntityType.Builder.of(factory, block.get()).build(null));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(name));
    }

    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(SNOW_MAN.get(), SnowMan.createAttributes().build());
    }
}
