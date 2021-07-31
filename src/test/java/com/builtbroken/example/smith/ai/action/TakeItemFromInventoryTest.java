package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;
import com.builtbroken.example.smith.SingleMemoryStub;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.Items;
import com.builtbroken.example.smith.data.inventory.Inventory;
import com.builtbroken.example.smith.data.tiles.Chest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/18/2021.
 */
class TakeItemFromInventoryTest
{
    @Test
    void takeItem_true() {
        //Setup world
        final World world = new World();
        final Inventory chest = world.getChest().getInventory();
        chest.setSlot(0, Items.ingots, 3);
        chest.setSlot(1, Items.ore, 3);
        chest.setSlot(2, Items.ore, 1);
        chest.setSlot(3, Items.fuel, 4);
        chest.setSlot(4, Items.fuel, 3);
        chest.setSlot(5, Items.fuel, 6);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("chest");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final TakeItemFromInventory action = new TakeItemFromInventory();
        action.item = Items.fuel;
        action.removeCount = 10;
        action.exact = false;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.STEP, result);

        //Validate self inventory
        Assertions.assertEquals(10, world.getAiInventory().countItems(Items.fuel));
        Assertions.assertEquals((World.AI_SLOTS - 1) * World.AI_SLOT_LIMIT, world.getAiInventory().countItems(null));

        //Validate chest inventory
        Assertions.assertEquals(3, chest.countItems(Items.ingots));
        Assertions.assertEquals(4, chest.countItems(Items.ore));
        Assertions.assertEquals(3, chest.countItems(Items.fuel));
        Assertions.assertEquals((Chest.SLOTS - 4) * Chest.SLOT_LIMIT, chest.countItems(null));
    }

    @Test
    void takeItem_false() {
        //Setup world
        final World world = new World();
        final Inventory chest = world.getChest().getInventory();
        chest.setSlot(0, Items.ingots, 3);
        chest.setSlot(1, Items.ore, 3);
        chest.setSlot(2, Items.ore, 1);
        chest.setSlot(3, Items.fuel, 4);
        chest.setSlot(4, Items.fuel, 3);
        chest.setSlot(5, Items.fuel, 6);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("chest");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final TakeItemFromInventory action = new TakeItemFromInventory();
        action.item = Items.fuel;
        action.removeCount = 20;
        action.exact = false;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.END, result);

        //Validate self inventory
        Assertions.assertEquals(0, world.getAiInventory().countItems(Items.fuel));
        Assertions.assertEquals(World.AI_SLOTS * World.AI_SLOT_LIMIT, world.getAiInventory().countItems(null));

        //Validate chest inventory
        Assertions.assertEquals(3, chest.countItems(Items.ingots));
        Assertions.assertEquals(4, chest.countItems(Items.ore));
        Assertions.assertEquals(13, chest.countItems(Items.fuel));
        Assertions.assertEquals((Chest.SLOTS - 6) * Chest.SLOT_LIMIT, chest.countItems(null));
    }
}
