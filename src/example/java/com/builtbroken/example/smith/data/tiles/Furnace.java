package com.builtbroken.example.smith.data.tiles;

import com.builtbroken.example.smith.data.inventory.Inventory;
import com.builtbroken.example.smith.data.inventory.Slot;
import lombok.Data;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class Furnace
{
    public static final int FUEL_ITEM_VALUE = 10;

    private int cookTime = 0;
    private int fuelTime = 0;

    public final Slot inputSlot = new Slot(0, 5);
    public final Slot outputSlot = new Slot(1, 5);
    public final Slot fuelSlot = new Slot(2, 2);

    private Inventory inventory = new Inventory(inputSlot, outputSlot, fuelSlot);

    public void tick()
    {

    }

    public boolean isRunning()
    {
        return cookTime > 0 && fuelTime > 0;
    }
}
