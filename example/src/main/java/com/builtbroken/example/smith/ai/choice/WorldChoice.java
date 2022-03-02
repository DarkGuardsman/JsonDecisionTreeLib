package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.imp.choice.Choice;
import com.builtbroken.example.game.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public abstract class WorldChoice<C extends WorldChoice> extends Choice<C, World, IMemoryContext>
{
    @Override
    public boolean isCompatible(IWorldContext worldContext, IMemoryContext memoryContext)
    {
        return worldContext instanceof World;
    }
}
