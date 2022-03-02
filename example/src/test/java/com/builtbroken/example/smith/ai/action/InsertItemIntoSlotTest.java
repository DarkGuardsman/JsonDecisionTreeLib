package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;
import com.builtbroken.example.smith.SingleMemoryStub;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.game.World;
import com.builtbroken.example.smith.Items;
import com.builtbroken.example.game.inventory.Inventory;
import com.builtbroken.example.game.content.tiles.Furnace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/18/2021.
 */
class InsertItemIntoSlotTest
{
    private Items itemList;

    @BeforeEach
    void setup() {
        itemList = new Items();
        itemList.setup();
    }

    @Test
    void insertItem_true() {
        //Setup world
        final World world = new World();
        final Inventory aiInventory = world.getAiInventory();
        aiInventory.setSlot(0, itemList.getIngots(), 3);
        aiInventory.setSlot(1, itemList.getOre(), 3);
        aiInventory.setSlot(2, itemList.getOre(), 1);
        aiInventory.setSlot(3, itemList.getFuel(), 4);
        aiInventory.setSlot(4, itemList.getFuel(), 3);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("furnace");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final InsertItemIntoSlot action = new InsertItemIntoSlot();
        action.item = itemList.getFuel();
        action.slot = world.getFurnace().fuelSlot.getSlotIndex();
        action.count = 1;
        action.exact = true;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.STEP, result);

        //Validate furnace inventory
        Assertions.assertEquals(1, world.getFurnace().getInventory().countItems(itemList.getFuel()));
        Assertions.assertEquals(10, world.getFurnace().getInventory().countItems(null));

        //Validate ai inventory
        Assertions.assertEquals(3, aiInventory.countItems(itemList.getIngots()));
        Assertions.assertEquals(4, aiInventory.countItems(itemList.getOre()));
        Assertions.assertEquals(6, aiInventory.countItems(itemList.getFuel()));
        Assertions.assertEquals(0, aiInventory.countItems(null));
    }

    @Test
    void insertItem_false() {
        //Setup world
        final World world = new World();
        final Inventory aiInventory = world.getAiInventory();
        aiInventory.setSlot(0, itemList.getIngots(), 3);
        aiInventory.setSlot(1, itemList.getOre(), 3);
        aiInventory.setSlot(2, itemList.getOre(), 1);
        aiInventory.setSlot(3, itemList.getFuel(), 4);
        aiInventory.setSlot(4, itemList.getFuel(), 3);

        world.getFurnace().getInventory().setSlot(Furnace.FUEL_SLOT, itemList.getFuel(), 2);

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("furnace");
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup action
        final InsertItemIntoSlot action = new InsertItemIntoSlot();
        action.item = itemList.getFuel();
        action.slot = Furnace.FUEL_SLOT;
        action.count = 1;
        action.exact = true;

        //Trigger action
        final ActionResult result = action.start(world, memory);

        //Good result is step
        Assertions.assertEquals(ActionResult.END, result);

        //Validate furnace inventory
        Assertions.assertEquals(2, world.getFurnace().getInventory().countItems(itemList.getFuel()));
        Assertions.assertEquals(10, world.getFurnace().getInventory().countItems(null));

        //Validate ai inventory
        Assertions.assertEquals(3, aiInventory.countItems(itemList.getIngots()));
        Assertions.assertEquals(4, aiInventory.countItems(itemList.getOre()));
        Assertions.assertEquals(7, aiInventory.countItems(itemList.getFuel()));
        Assertions.assertEquals(0, aiInventory.countItems(null));
    }
}
