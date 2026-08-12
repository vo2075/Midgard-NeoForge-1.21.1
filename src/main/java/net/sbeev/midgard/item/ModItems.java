package net.sbeev.midgard.item;

import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.sbeev.midgard.Midgard;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sbeev.midgard.block.ModBlocks;
import net.sbeev.midgard.entity.ModBoatTypeProxies;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Midgard.MOD_ID);

    public static final DeferredItem<Item> PINE_SIGN = ITEMS.register("pine_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.PINE_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));
    public static final DeferredItem<Item> PINE_HANGING_SIGN = ITEMS.register("pine_hanging_sign",
            () -> new HangingSignItem(ModBlocks.PINE_HANGING_SIGN.get(), ModBlocks.PINE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> PINE_BOAT = ITEMS.register("pine_boat",
            () -> new BoatItem(false, ModBoatTypeProxies.PINE_PROXY.getValue(), new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PINE_CHEST_BOAT = ITEMS.register("pine_chest_boat",
            () -> new BoatItem(true, ModBoatTypeProxies.PINE_PROXY.getValue(), new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> ASPEN_SIGN = ITEMS.register("aspen_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.ASPEN_SIGN.get(), ModBlocks.ASPEN_WALL_SIGN.get()));
    public static final DeferredItem<Item> ASPEN_HANGING_SIGN = ITEMS.register("aspen_hanging_sign",
            () -> new HangingSignItem(ModBlocks.ASPEN_HANGING_SIGN.get(), ModBlocks.ASPEN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> ASPEN_BOAT = ITEMS.register("aspen_boat",
            () -> new BoatItem(false, ModBoatTypeProxies.ASPEN_PROXY.getValue(), new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ASPEN_CHEST_BOAT = ITEMS.register("aspen_chest_boat",
            () -> new BoatItem(true, ModBoatTypeProxies.ASPEN_PROXY.getValue(), new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MAPLE_SIGN = ITEMS.register("maple_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get()));
    public static final DeferredItem<Item> MAPLE_HANGING_SIGN = ITEMS.register("maple_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MAPLE_HANGING_SIGN.get(), ModBlocks.MAPLE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> MAPLE_BOAT = ITEMS.register("maple_boat",
            () -> new BoatItem(false, ModBoatTypeProxies.MAPLE_PROXY.getValue(), new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MAPLE_CHEST_BOAT = ITEMS.register("maple_chest_boat",
            () -> new BoatItem(true, ModBoatTypeProxies.MAPLE_PROXY.getValue(), new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
