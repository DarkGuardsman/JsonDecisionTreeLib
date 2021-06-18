package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.ai.AiHelper;
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.data.content.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
public class TakeItemFromSlot extends WorldAction<TakeItemFromSlot>
{
    protected Item item;
    protected int removeCount;
    protected int slot;
    protected boolean exact;

    @Nonnull
    @Override
    public ActionResult start(@Nonnull World world, @Nullable IMemoryContext memory)
    {
        final boolean result = AiHelper.actOnInventory(world, memory,
                (inventory) -> {
                    if(inventory.hasItemInSlot(slot, item, removeCount, exact)) {
                        return inventory.moveItemToInventory(world.getAiInventory(), slot, removeCount, false);
                    }
                    return false;
                });
        return result ? ActionResult.STEP : ActionResult.END;
    }

    @Override
    public void copyInto(TakeItemFromSlot choice)
    {

    }
}
