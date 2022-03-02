package com.builtbroken.example.smith.ai;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.game.World;
import com.builtbroken.example.game.inventory.Inventory;

import java.util.function.Function;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
public class AiHelper
{
    public static boolean actOnInventory(final World world, final IMemoryContext memory, final Function<Inventory, Boolean> callback)
    {
        final String tile = MemorySlots.MEMORY_FOCUSED_TILE.getValue(memory);
        final Inventory inventory = world.getInventory(tile);
        if (inventory != null)
        {
            return callback.apply(inventory);
        }
        return false;
    }
}
