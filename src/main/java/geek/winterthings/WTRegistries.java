package geek.winterthings;

import org.antlr.v4.parse.ANTLRParser.finallyClause_return;

import geek.winterthings.blocks.BaseBlock;
import geek.winterthings.entity.BaseBlockEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WTRegistries {

    // Registries
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WinterThings.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WinterThings.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BETYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WinterThings.MODID);


    // Items
    public static final RegistryObject<Item> WINTER_JAM = registerItem("winter_jam",new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
            

    // Blocks
    public static final RegistryObject<Block> BASE_BLOCK = registerBlock("Base",new BaseBlock(BlockBehaviour.Properties.of().strength(2.0f)));

    // Block Entities
    public static final RegistryObject<BlockEntityType<BaseBlockEntity>> BASE_BLOCK_ENTITY = BETYPES.register("base_block_entity", () -> 
    BlockEntityType.Builder.of(BaseBlockEntity::new, BASE_BLOCK.get()).build(null));

    // Misc

    public static RegistryObject<Block> registerBlock(String name, Block block){
        return BLOCKS.register(name, () -> block);
    }

        public static RegistryObject<Item> registerItem(String name, Item item){
        return ITEMS.register(name, () -> item);
    }

}
