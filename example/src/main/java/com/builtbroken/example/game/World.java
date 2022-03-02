package com.builtbroken.example.game;

import com.builtbroken.decisiontree.api.context.world.IWorldAccessType;
import com.builtbroken.decisiontree.api.context.world.IWorldAccessor;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.api.context.world.IWorldPointer;
import com.builtbroken.decisiontree.data.context.ActionContext;
import com.builtbroken.example.game.inventory.Inventory;
import com.builtbroken.example.game.content.tiles.Chest;
import com.builtbroken.example.game.content.tiles.Furnace;
import lombok.Data;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class World extends ActionContext implements IWorldContext
{
    public static final int AI_SLOTS = 5;
    public static final int AI_SLOT_LIMIT = 10;

    private final Furnace furnace = new Furnace();
    private final Chest chest = new Chest();
    private final Inventory aiInventory = new Inventory(AI_SLOTS, AI_SLOT_LIMIT);

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

    public Inventory getInventory(String tile) {
        if ("furnace".equalsIgnoreCase(tile))
        {
            return furnace.getInventory();
        }
        else if ("chest".equalsIgnoreCase(tile))
        {
            return chest.getInventory();
        }
        return null;
    }
}
