package com.builtbroken.example.smith.data.inventory;

import com.builtbroken.example.smith.data.content.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
public class Inventory
{
    @Setter(value = AccessLevel.NONE)
    private final List<Slot> slots;

    public Inventory(Slot... slots)
    {
        this.slots = Arrays.asList(slots);
    }

    public Inventory(int slotCount, int slotStackLimit)
    {
        slots = new ArrayList<>(slotCount);
        for (int i = 0; i < slotCount; i++)
        {
            slots.add(new Slot(i, slotStackLimit));
        }
    }

    public Slot getSlot(int index)
    {
        return slots.get(index);
    }

    /**
     * Checks if the inventory has an item
     *
     * @param slotIndex to reference the slot
     * @param item      to check if contains
     * @return true if the slot contains the item
     */
    public boolean hasItemInSlot(final int slotIndex, final Item item, final int count, final boolean exact)
    {
        final Slot slot = getSlot(slotIndex);
        if (slot != null && slot.getItem() == item)
        {
            if (exact)
            {
                return count == slot.getCount();
            }
            return count <= slot.getCount();
        }
        return false;
    }

    /**
     * Checks if any slot in the inventory contains the given item
     *
     * if exact is false it will check for greater than equal to the amount Ex: slot(10) >= count(5)
     *
     * @param item to check
     * @param count to check
     * @param exact match of count
     * @return true if slot matches params
     */
    public boolean hasItem(final Item item, final int count, final boolean exact)
    {
        return slots.stream().anyMatch(slot -> {
            boolean b = hasItemInSlot(slot.getSlotIndex(), item, count, exact);
            return b;
        });
    }

    /**
     * Sets the contents of the slot
     *
     * @param slotIndex to reference the slot
     * @param item      to insert
     * @param count     to set item at
     */
    public void setSlot(final int slotIndex, final Item item, final int count)
    {
        final Slot slot = getSlot(slotIndex);
        if (slot != null)
        {
            //Debug code to find broken logic, should never be used in theory
            if (item != null && count <= 0)
            {
                new IllegalArgumentException("Inventory: Item's count should be greater than 0").printStackTrace();
            }

            //Calculate slot limit
            final int slotLimit = Math.min(item != null ? item.getMaxStack() : 0, slot.getMaxCount());

            //Update slot
            slot.setItem(count <= 0 ? null : item);
            slot.setCount(item != null ? Math.max(0, Math.min(count, slotLimit)) : 0);
        }
    }
}
