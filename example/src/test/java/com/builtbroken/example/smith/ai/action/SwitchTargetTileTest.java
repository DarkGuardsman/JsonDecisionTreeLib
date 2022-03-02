package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;
import com.builtbroken.example.smith.SingleMemoryStub;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.smith.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
class SwitchTargetTileTest
{
    @Test
    void updateTarget() {

        //Setup
        final StringMemoryValue memoryValue = new StringMemoryValue();
        final IMemoryContext memory = new SingleMemoryStub(MemorySlots.MEMORY_FOCUSED_TILE, memoryValue);

        final SwitchTargetTile targetTile = new SwitchTargetTile();
        targetTile.tile = "Bees";

        //Invoke
        final ActionResult result = targetTile.start(new World(), memory);

        //Expect to complete action
        Assertions.assertEquals(ActionResult.COMPLETE, result);

        //Expect memory to have been updated
        Assertions.assertEquals("Bees", memoryValue.getValue());
    }
}
