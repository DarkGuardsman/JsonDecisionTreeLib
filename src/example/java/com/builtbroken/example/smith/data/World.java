package com.builtbroken.example.smith.data;

import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.data.context.ActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public class World extends ActionContext implements IWorldContext
{
    public Furnace furnace;
    public Chest chest;

    public void tick(int tick)
    {
        if(furnace != null)
        {
            furnace.tick();
        }
    }

    @Override
    public String toString()
    {
        return "World[" + getContextOwner() + "]";
    }
}
