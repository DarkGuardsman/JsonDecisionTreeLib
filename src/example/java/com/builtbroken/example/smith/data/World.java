package com.builtbroken.example.smith.data;

import com.builtbroken.decisiontree.api.context.IActorContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.data.context.ActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public class World extends ActionContext implements IWorldContext
{
    public Furnace furnace;
    public Chest chest;

    public World(IActorContext actorContext)
    {
        super(actorContext);
    }
}
