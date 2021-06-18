package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.data.content.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
public class TakeItemFromInventory extends WorldAction<TakeItemFromInventory>
{
    protected Item item;
    protected int removeCount;

    @Nonnull
    @Override
    public ActionResult start(@Nonnull World world, @Nullable IMemoryContext memory)
    {
        return ActionResult.ERROR;
    }

    @Override
    public void copyInto(TakeItemFromInventory choice)
    {

    }
}
