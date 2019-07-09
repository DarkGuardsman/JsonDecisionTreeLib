package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.data.action.Action;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public abstract class WorldAction<A extends WorldAction> extends Action<WorldAction, World, IMemoryContext>
{
    @Override
    public boolean isCompatible(IWorldContext worldContext, IMemoryContext memoryContext)
    {
        return worldContext instanceof World;
    }
}
