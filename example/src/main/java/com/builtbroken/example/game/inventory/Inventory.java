package com.builtbroken.example.game.inventory;

import com.builtbroken.example.game.content.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * <p>
     * if exact is false it will check for greater than equal to the amount Ex: slot(10) >= count(5)
     *
     * @param item  to check
     * @param count to check
     * @param exact match of count
     * @return true if slot matches params
     */
    public boolean hasItem(final Item item, final int count, final boolean exact)
    {
        return slots.stream().anyMatch(slot -> hasItemInSlot(slot.getSlotIndex(), item, count, exact));
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

    /**
     * Will move the exact amount requested or do nothing
     *
     * @param targetInventory to add towards
     * @param slotIndex       to remove from
     * @param removeCount     amount to remove
     * @return true if items were moved
     */
    public boolean moveItemToInventory(Inventory targetInventory, int slotIndex, int removeCount, boolean simulate)
    {
        final Slot slot = getSlot(slotIndex);
        final Item slotContents = slot.getItem();

        //Validate we have something to move
        if (slotContents != null && slot.getCount() >= removeCount)
        {
            //Check if a move would work
            final int simulatedAdd = targetInventory.addItemToInventory(slotContents, removeCount, true);
            if (simulatedAdd == removeCount)
            {
                //Do move
                if (!simulate)
                {
                    setSlot(slotIndex, slotContents, slot.getCount() - removeCount);
                    targetInventory.addItemToInventory(slotContents, removeCount, false);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Moves all items matching reference items to the other inventory.
     * <p>
     * Will match exact amount
     *
     * @param targetInventory to add towards
     * @param itemRemoving    item to match
     * @param removeCount     amount in total to remove
     * @param simulate        when set to true will calculates results without editing inventory
     * @return true if items were moved
     */
    public boolean moveAllItemsToInventory(Inventory targetInventory, Item itemRemoving, int removeCount, boolean simulate)
    {
        final List<Slot> slotsContainingItem = slots.stream()
                .filter(slot -> slot.getItem() == itemRemoving)
                .collect(Collectors.toList());

        int amountMoved = 0;
        for (Slot slot : slotsContainingItem)
        {
            final int amountLeft = removeCount - amountMoved;
            final int amountToMove = Math.min(slot.getCount(), amountLeft);

            if (moveItemToInventory(targetInventory, slot.getSlotIndex(), amountToMove, simulate))
            {
                amountMoved += amountToMove;
            }
            else
            {
                return false;
            }
        }

        return amountMoved == removeCount;
    }

    /**
     * Tries to add the item to the inventory
     *
     * @param itemToInsert defines item being added
     * @param count        defines quantity of items to add
     * @param simulate     when set to true will calculates results without editing inventory
     * @return amount added
     */
    public int addItemToInventory(@Nonnull final Item itemToInsert, final int count, final boolean simulate)
    {
        int itemsAdded = 0;
        for (int slotIndex = 0; slotIndex < slots.size() && itemsAdded < count; slotIndex++)
        {
            itemsAdded += addItemToSlot(itemToInsert, slotIndex, count - itemsAdded, simulate);
        }
        return itemsAdded;
    }

    /**
     *
     * @param itemToRemove
     * @param amountToRemove
     * @param simulate
     * @return number of items removed
     */
    public int removeItemFromInventory(final Item itemToRemove, final int amountToRemove, final boolean simulate)
    {
        int itemsRemoved = 0;
        for (int slotIndex = 0; slotIndex < slots.size() && itemsRemoved < amountToRemove; slotIndex++)
        {
            itemsRemoved += removeItemFromSlot(itemToRemove, slotIndex, amountToRemove - itemsRemoved, simulate);
        }
        return itemsRemoved;
    }

    public int addItemToSlot(@Nonnull final Item itemToInsert, final int slotIndex, final int amountToAdd, final boolean simulate)
    {
        final Slot slot = getSlot(slotIndex);
        final Item slotContents = slot.getItem();

        //Checking if item can be in slot
        if (slotContents == itemToInsert || slotContents == null)
        {
            //Figure out room left
            final int maxStackCount = Math.min(itemToInsert.getMaxStack(), slot.getMaxCount());
            final int roomLeft = maxStackCount - slot.getCount();
            if (roomLeft > 0)
            {
                //Figure out how much we can store
                final int itemsToAdd = Math.min(amountToAdd, roomLeft);

                //Do insert
                if (!simulate)
                {
                    setSlot(slotIndex, itemToInsert, slot.getCount() + itemsToAdd);
                }
                return itemsToAdd;
            }
        }
        return 0;
    }

    public int removeItemFromSlot(@Nonnull final Item itemFromSlot, final int slotIndex, final int amountToRemove, final boolean simulate)
    {
        final Slot slot = getSlot(slotIndex);
        final Item slotContents = slot.getItem();

        //Checking if item can be in slot
        if (slotContents == itemFromSlot && slot.getCount() > 0)
        {
            //Figure out how much we can store
            final int itemsToRemove = Math.min(amountToRemove, slot.getCount());

            //Do insert
            if (!simulate)
            {
                setSlot(slotIndex, itemFromSlot, slot.getCount() - itemsToRemove);
            }
            return itemsToRemove;
        }
        return 0;
    }

    public int countItems(Item item)
    {
        return slots.stream()
                .filter(slot -> slot.getItem() == item)
                .mapToInt(slot -> item == null ? slot.getMaxCount() : slot.getCount())
                .sum();
    }
}
