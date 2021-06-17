package com.builtbroken.example.smith.data.tiles;

import com.builtbroken.example.smith.data.inventory.Inventory;
import lombok.Data;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class Chest
{
    private Inventory inventory = new Inventory(10, 25);
}
