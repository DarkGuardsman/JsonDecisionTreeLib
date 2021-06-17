package com.builtbroken.example.smith.data.inventory;

import com.builtbroken.example.smith.data.content.Items;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
class InventoryTest
{
    @Nested
    class Initialization
    {
        //TODO test supply slots

        //TODO test init slots
    }

    @Nested
    class SlotHasItem
    {
        @Test
        void itemsMatch_true() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 2);
            Assertions.assertTrue(inventory.hasItemInSlot(0, Items.fuel, 1, false));
        }

        @Test
        void itemsMatch_false() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.ingots, 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, Items.fuel, 1, false));
        }

        @Test
        void itemsMatch_countExact_true() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 1);
            Assertions.assertTrue(inventory.hasItemInSlot(0, Items.fuel, 1, true));
        }

        @Test
        void itemsMatch_countExact_false() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, Items.fuel, 1, true));
        }

        @Test
        void itemsMatch_countLessThan() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, Items.fuel, 5, false));
        }

        @Test
        void itemsMatch_countEqual() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 5);
            Assertions.assertTrue(inventory.hasItemInSlot(0, Items.fuel, 5, false));
        }

        @Test
        void itemsMatch_countEqual_exact() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 5);
            Assertions.assertTrue(inventory.hasItemInSlot(0, Items.fuel, 5, true));
        }

        @Test
        void itemsMatch_countGreater() {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, Items.fuel, 5);
            Assertions.assertFalse(inventory.hasItemInSlot(0, Items.fuel, 10, false));
        }
    }

    @Nested
    class InventoryHasItem
    {
        @Test
        void contains_true() {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, Items.fuel, 2);
            Assertions.assertTrue(inventory.hasItem(Items.fuel, 1, false));
        }

        @Test
        void contains_false() {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, Items.ingots, 2);
            Assertions.assertFalse(inventory.hasItem(Items.fuel, 1, false));
        }

        @Test
        void contains_emptyInventory_false() {
            final Inventory inventory = new Inventory(5, 10);
            Assertions.assertFalse(inventory.hasItem(Items.fuel, 1, false));
        }

        @Test
        void contains_mixedInventory_true() {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(1, Items.ingots, 2);
            inventory.setSlot(2, Items.ore, 4);
            inventory.setSlot(3, Items.fuel, 2);
            Assertions.assertTrue(inventory.hasItem(Items.fuel, 1, false));
        }

        @Test
        void contains_mixedInventory_false() {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, Items.ingots, 2);
            inventory.setSlot(1, Items.ore, 4);
            Assertions.assertFalse(inventory.hasItem(Items.fuel, 1, false));
        }
    }

    @Nested
    class SetSlot
    {
        //TODO set item

        //TODO replace item

        //TODO clear item

        //TODO update count
    }

    @Nested
    class GetSlot
    {
        @Test
        void insideBounds()
        {
            final Inventory inventory = new Inventory(10, 5);
            final Slot slot = inventory.getSlot(3);
            Assertions.assertEquals(3, slot.getSlotIndex());
        }

        @Test
        void underBounds()
        {
            final Inventory inventory = new Inventory(10, 5);
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> inventory.getSlot(-1));
        }

        @Test
        void overBounds()
        {
            final Inventory inventory = new Inventory(10, 5);
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> inventory.getSlot(10));
        }
    }
}
