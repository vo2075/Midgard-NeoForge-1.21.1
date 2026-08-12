package net.sbeev.midgard.entity;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.sbeev.midgard.block.ModBlocks;
import net.sbeev.midgard.item.ModItems;

import java.util.function.Supplier;

@SuppressWarnings({ "Convert2MethodRef", "FunctionalExpressionCanBeFolded" })
public class ModBoatTypeProxies {

    public static final EnumProxy<Boat.Type> PINE_PROXY = new EnumProxy<>(Boat.Type.class,
            (Supplier<Block>) () -> ModBlocks.PINE_PLANKS.get(),
            "midgard:pine",
            (Supplier<Item>) () -> ModItems.PINE_BOAT.get(),
            (Supplier<Item>) () -> ModItems.PINE_CHEST_BOAT.get(),
            (Supplier<Item>) () -> Items.STICK,
            false);

    public static final EnumProxy<Boat.Type> ASPEN_PROXY = new EnumProxy<>(Boat.Type.class,
            (Supplier<Block>) () -> ModBlocks.ASPEN_PLANKS.get(),
            "midgard:aspen",
            (Supplier<Item>) () -> ModItems.ASPEN_BOAT.get(),
            (Supplier<Item>) () -> ModItems.ASPEN_CHEST_BOAT.get(),
            (Supplier<Item>) () -> Items.STICK,
            false);
    
    public static final EnumProxy<Boat.Type> MAPLE_PROXY = new EnumProxy<>(Boat.Type.class,
            (Supplier<Block>) () -> ModBlocks.MAPLE_PLANKS.get(),
            "midgard:maple",
            (Supplier<Item>) () -> ModItems.MAPLE_BOAT.get(),
            (Supplier<Item>) () -> ModItems.MAPLE_CHEST_BOAT.get(),
            (Supplier<Item>) () -> Items.STICK,
            false);

}
