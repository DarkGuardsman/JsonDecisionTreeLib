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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/18/2021.
 */
class TakeItemFromInventoryTest
{
    private Items itemList;

    @BeforeEach
    void setup() {
        itemList = new Items();
        itemList.setup();
    }

    @Test
    void takeItem_true() {
        //Setup world
        final World world = new World();
        final Inventory chest = world.getChest().getInventory();
        chest.setSlot(0, itemList.getIngots(), 3);
        chest.setSlot(1, itemList.getOre(), 3);
        chest.setSlot(2, itemList.getOre(), 1);
        chest.setSlot(3, itemList.getFuel(), 4);
        chest.setSlot(4, itemList.getFuel(), 3);
        chest.setSlot(5, itemList.getFuel(), 6);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("chest");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final TakeItemFromInventory action = new TakeItemFromInventory();
        action.item = itemList.getFuel();
        action.removeCount = 10;
        action.exact = false;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.STEP, result);

        //Validate self inventory
        Assertions.assertEquals(10, world.getAiInventory().countItems(itemList.getFuel()));
        Assertions.assertEquals((World.AI_SLOTS - 1) * World.AI_SLOT_LIMIT, world.getAiInventory().countItems(null));

        //Validate chest inventory
        Assertions.assertEquals(3, chest.countItems(itemList.getIngots()));
        Assertions.assertEquals(4, chest.countItems(itemList.getOre()));
        Assertions.assertEquals(3, chest.countItems(itemList.getFuel()));
        Assertions.assertEquals((Chest.SLOTS - 4) * Chest.SLOT_LIMIT, chest.countItems(null));
    }

    @Test
    void takeItem_false() {
        //Setup world
        final World world = new World();
        final Inventory chest = world.getChest().getInventory();
        chest.setSlot(0, itemList.getIngots(), 3);
        chest.setSlot(1, itemList.getOre(), 3);
        chest.setSlot(2, itemList.getOre(), 1);
        chest.setSlot(3, itemList.getFuel(), 4);
        chest.setSlot(4, itemList.getFuel(), 3);
        chest.setSlot(5, itemList.getFuel(), 6);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("chest");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final TakeItemFromInventory action = new TakeItemFromInventory();
        action.item = itemList.getFuel();
        action.removeCount = 20;
        action.exact = false;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.END, result);

        //Validate self inventory
        Assertions.assertEquals(0, world.getAiInventory().countItems(itemList.getFuel()));
        Assertions.assertEquals(World.AI_SLOTS * World.AI_SLOT_LIMIT, world.getAiInventory().countItems(null));

        //Validate chest inventory
        Assertions.assertEquals(3, chest.countItems(itemList.getIngots()));
        Assertions.assertEquals(4, chest.countItems(itemList.getOre()));
        Assertions.assertEquals(13, chest.countItems(itemList.getFuel()));
        Assertions.assertEquals((Chest.SLOTS - 6) * Chest.SLOT_LIMIT, chest.countItems(null));
    }
}
