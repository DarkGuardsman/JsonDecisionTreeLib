package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;
import com.builtbroken.example.smith.SingleMemoryStub;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.data.content.Items;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
public class InventoryContainsTest
{
    private World world;
    private IMemoryContext memory;
    private InventoryContains inventoryContains;

    @BeforeEach
    void beforeEach()
    {
        //Setup world
        world = new World();

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("chest");
        memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup logic block
        inventoryContains = InventoryContains.build("smith:chest.contains.fuel");
        inventoryContains.count = 4;
        inventoryContains.exact = false;
        inventoryContains.item = Items.fuel;
    }

    @Test
    void containsItem_mixedInventory_true()
    {
        //Setup world
        world.getChest().getInventory().setSlot(2, Items.ore, 3);
        world.getChest().getInventory().setSlot(6, Items.ingots, 2);
        world.getChest().getInventory().setSlot(4, Items.fuel, 5);

        //Invoke logic
        final boolean result = inventoryContains.isTrue(world, memory);

        //Validating results
        Assertions.assertTrue(result);
    }

    @Test
    void containsItem_exactInventory_true()
    {
        //Setup world
        world.getChest().getInventory().setSlot(4, Items.fuel, 5);

        //Invoke logic
        final boolean result = inventoryContains.isTrue(world, memory);

        //Validating results
        Assertions.assertTrue(result);
    }

    @Test
    void containsItem_emptyInventory_false()
    {
        //Invoke logic
        final boolean result = inventoryContains.isTrue(world, memory);

        //Validating results
        Assertions.assertFalse(result);
    }
}
