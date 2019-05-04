package ladysnake.illuminations.common.init;

import ladysnake.illuminations.common.Illuminations;
import ladysnake.illuminations.common.blocks.FireflyNestBlock;
import ladysnake.illuminations.common.blocks.LightningBugNestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

public class IlluminationsBlocks {

    public static Block FIREFLY_NEST;
    public static Block LIGHTNING_BUG_NEST;


    public static void init() {
        FIREFLY_NEST = registerBlock(new FireflyNestBlock(Block.Settings.of(Material.WOOD).strength(0.5F, 1.5F)), "firefly_nest");
        LIGHTNING_BUG_NEST = registerBlock(new LightningBugNestBlock(Block.Settings.of(Material.WOOD).strength(0.5F, 1.5F)), "lightning_bug_nest");
    }

    private static Block registerBlock(Block block, String name) {
        return registerBlock(block, name, true);
    }

    private static Block registerBlock(Block block, String name, boolean doItem) {
        Registry.register(Registry.BLOCK, Illuminations.MOD_ID + ":" + name, block);

        if (doItem) {
            BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(ItemGroup.DECORATIONS));
            item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
            IlluminationsItems.registerItem(item, name);
        }

        return block;
    }

}