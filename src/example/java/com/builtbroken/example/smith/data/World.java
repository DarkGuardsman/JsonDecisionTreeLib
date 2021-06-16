package com.builtbroken.example.smith.data;

import com.builtbroken.decisiontree.api.context.world.IWorldAccessType;
import com.builtbroken.decisiontree.api.context.world.IWorldAccessor;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.api.context.world.IWorldPointer;
import com.builtbroken.decisiontree.data.context.ActionContext;
import lombok.Data;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class World extends ActionContext implements IWorldContext
{
    private final Furnace furnace = new Furnace();
    private final Chest chest = new Chest();

    public void tick(int tick)
    {
        furnace.tick();
    }

    @Override
    public String toString()
    {
        return "World[" + getContextOwner() + "]";
    }

    @Override
    public IWorldAccessor getAccessor(IWorldPointer pointer, IWorldAccessType type)
    {
        return null;
    }
}
