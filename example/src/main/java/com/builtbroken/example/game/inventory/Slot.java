package com.builtbroken.example.game.inventory;

import com.builtbroken.example.game.content.Item;
import lombok.Data;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
public class Slot
{
    /** Index in the inventory */
    private final int slotIndex;
    /** Limit of storage */
    private final int maxCount;

    /** Item stored */
    private Item item;
    /** Number of items stored */
    private int count;

    public Slot(int index, int maxCount) {
        this.slotIndex = index;
        this.maxCount = maxCount;
    }
}
