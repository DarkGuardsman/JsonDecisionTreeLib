package com.builtbroken.example.game.content.tiles;

import com.builtbroken.example.game.inventory.Inventory;
import com.builtbroken.example.game.inventory.Slot;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class Furnace
{
    public static final int FUEL_ITEM_VALUE = 10;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int FUEL_SLOT = 2;

    private int cookTime = 0;
    private int fuelTime = 0;

    public final Slot inputSlot = new Slot(INPUT_SLOT, 5);
    public final Slot outputSlot = new Slot(OUTPUT_SLOT, 5);
    public final Slot fuelSlot = new Slot(FUEL_SLOT, 2);

    @Setter(value = AccessLevel.NONE)
    private Inventory inventory = new Inventory(inputSlot, outputSlot, fuelSlot);

    public void tick()
    {

    }

    public boolean isRunning()
    {
        return cookTime > 0 && fuelTime > 0;
    }
}
