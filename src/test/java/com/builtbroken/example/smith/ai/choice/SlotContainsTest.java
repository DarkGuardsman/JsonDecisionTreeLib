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
 * Created by Robin Seifert on 6/16/2021.
 */
class SlotContainsTest
{
    private World world;
    private IMemoryContext memory;
    private SlotContains slotContains;

    @BeforeEach
    void beforeEach()
    {
        //Setup world
        world = new World();

        //Setup memory
        final StringMemoryValue memoryValue = new StringMemoryValue();
        memoryValue.setValue("furnace");
        memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        //Setup logic block
        slotContains = SlotContains.build("smith:furnace.check.input");
        slotContains.slot = 1;
        slotContains.item = Items.ingots;
    }

    @Test
    void containsItem_true()
    {
        //Setup world
        world.getFurnace().getInventory().setSlot(1, Items.ingots, 2);

        //Validate starting conditions
        Assertions.assertTrue(world.getFurnace().getInventory().hasItemInSlot(1, Items.ingots, 2, true));

        //Invoke logic
        final boolean result = slotContains.isTrue(world, memory);

        //Validating results
        Assertions.assertTrue(result);
    }

    @Test
    void containsItem_false()
    {

        //Validate starting conditions
        Assertions.assertTrue(world.getFurnace().getInventory().hasItemInSlot(1, null, 0, true));

        //Invoke logic
        final boolean result = slotContains.isTrue(world, memory);

        //Validating results
        Assertions.assertFalse(result);
    }
}
