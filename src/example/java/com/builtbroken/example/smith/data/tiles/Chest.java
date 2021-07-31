package com.builtbroken.example.smith.data.tiles;

import com.builtbroken.example.smith.data.inventory.Inventory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class Chest
{
    public static final int SLOTS = 10;
    public static final int SLOT_LIMIT = 25;

    @Setter(value = AccessLevel.NONE)
    private Inventory inventory = new Inventory(SLOTS, SLOT_LIMIT);
}
