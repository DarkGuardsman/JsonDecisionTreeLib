package com.builtbroken.example.smith.data.inventory;

import com.builtbroken.example.smith.data.content.Item;
import com.builtbroken.example.smith.Items;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
class InventoryTest
{
    static final Item itemA = Item.build("test:item.a");
    static final Item itemB = Item.build("test:item.b");

    private Items itemList;

    @BeforeEach
    void setup() {
        itemList = new Items();
        itemList.setup();
    }

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
        void itemsMatch_true()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 2);
            Assertions.assertTrue(inventory.hasItemInSlot(0, itemList.getFuel(), 1, false));
        }

        @Test
        void itemsMatch_false()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getIngots(), 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, itemList.getFuel(), 1, false));
        }

        @Test
        void itemsMatch_countExact_true()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 1);
            Assertions.assertTrue(inventory.hasItemInSlot(0, itemList.getFuel(), 1, true));
        }

        @Test
        void itemsMatch_countExact_false()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, itemList.getFuel(), 1, true));
        }

        @Test
        void itemsMatch_countLessThan()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 2);
            Assertions.assertFalse(inventory.hasItemInSlot(0, itemList.getFuel(), 5, false));
        }

        @Test
        void itemsMatch_countEqual()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 5);
            Assertions.assertTrue(inventory.hasItemInSlot(0, itemList.getFuel(), 5, false));
        }

        @Test
        void itemsMatch_countEqual_exact()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 5);
            Assertions.assertTrue(inventory.hasItemInSlot(0, itemList.getFuel(), 5, true));
        }

        @Test
        void itemsMatch_countGreater()
        {
            final Inventory inventory = new Inventory(1, 10);
            inventory.setSlot(0, itemList.getFuel(), 5);
            Assertions.assertFalse(inventory.hasItemInSlot(0, itemList.getFuel(), 10, false));
        }
    }

    @Nested
    class InventoryHasItem
    {
        @Test
        void contains_true()
        {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, itemList.getFuel(), 2);
            Assertions.assertTrue(inventory.hasItem(itemList.getFuel(), 1, false));
        }

        @Test
        void contains_false()
        {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, itemList.getIngots(), 2);
            Assertions.assertFalse(inventory.hasItem(itemList.getFuel(), 1, false));
        }

        @Test
        void contains_emptyInventory_false()
        {
            final Inventory inventory = new Inventory(5, 10);
            Assertions.assertFalse(inventory.hasItem(itemList.getFuel(), 1, false));
        }

        @Test
        void contains_mixedInventory_true()
        {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(1, itemList.getIngots(), 2);
            inventory.setSlot(2, itemList.getOre(), 4);
            inventory.setSlot(3, itemList.getFuel(), 2);
            Assertions.assertTrue(inventory.hasItem(itemList.getFuel(), 1, false));
        }

        @Test
        void contains_mixedInventory_false()
        {
            final Inventory inventory = new Inventory(5, 10);
            inventory.setSlot(2, itemList.getIngots(), 2);
            inventory.setSlot(1, itemList.getOre(), 4);
            Assertions.assertFalse(inventory.hasItem(itemList.getFuel(), 1, false));
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

    @Nested
    class MoveItemToInventory {

        @Test
        void moveItems_actual() {
            final Inventory fromInventory = new Inventory(5, 10);
            fromInventory.setSlot(0, itemA, 5);
            fromInventory.setSlot(1, itemB, 2);
            fromInventory.setSlot(2, itemA, 5);
            fromInventory.setSlot(3, itemA, 1);

            final Inventory targetInventory = new Inventory(5, 10);

            Assertions.assertTrue(fromInventory.moveItemToInventory(targetInventory, 0, 2, false));

            //Validate target counts
            Assertions.assertEquals(2, targetInventory.countItems(itemA));
            Assertions.assertEquals(40, targetInventory.countItems(null));

            //Validate from counts
            Assertions.assertEquals(9, fromInventory.countItems(itemA));
            Assertions.assertEquals(2, fromInventory.countItems(itemB));
            Assertions.assertEquals(10, fromInventory.countItems(null));
        }

        @Test
        void moveItems_lessThanRequested_actual() {
            final Inventory fromInventory = new Inventory(5, 10);
            fromInventory.setSlot(0, itemA, 5);
            fromInventory.setSlot(1, itemB, 2);
            fromInventory.setSlot(2, itemA, 5);
            fromInventory.setSlot(3, itemA, 1);

            final Inventory targetInventory = new Inventory(5, 10);

            Assertions.assertFalse(fromInventory.moveItemToInventory(targetInventory, 0, 10, false));

            //Validate target counts
            Assertions.assertEquals(0, targetInventory.countItems(itemA));
            Assertions.assertEquals(50, targetInventory.countItems(null));

            //Validate from counts
            Assertions.assertEquals(11, fromInventory.countItems(itemA));
            Assertions.assertEquals(2, fromInventory.countItems(itemB));
            Assertions.assertEquals(10, fromInventory.countItems(null));
        }

        @Test
        void moveItems_simulate() {
            final Inventory fromInventory = new Inventory(5, 10);
            fromInventory.setSlot(0, itemA, 5);
            fromInventory.setSlot(1, itemB, 2);
            fromInventory.setSlot(2, itemA, 5);
            fromInventory.setSlot(3, itemA, 1);

            final Inventory targetInventory = new Inventory(5, 10);

            Assertions.assertTrue(fromInventory.moveItemToInventory(targetInventory, 0, 2, true));

            //Validate target counts
            Assertions.assertEquals(0, targetInventory.countItems(itemA));
            Assertions.assertEquals(50, targetInventory.countItems(null));

            //Validate from counts
            Assertions.assertEquals(11, fromInventory.countItems(itemA));
            Assertions.assertEquals(2, fromInventory.countItems(itemB));
            Assertions.assertEquals(10, fromInventory.countItems(null));
        }
    }

    @Nested
    class MoveItemsToInventory {
        @Test
        void moveItems_actual() {
            final Inventory fromInventory = new Inventory(5, 10);
            fromInventory.setSlot(0, itemA, 5);
            fromInventory.setSlot(1, itemB, 2);
            fromInventory.setSlot(2, itemA, 5);
            fromInventory.setSlot(3, itemA, 1);

            final Inventory targetInventory = new Inventory(5, 10);

            Assertions.assertTrue(fromInventory.moveAllItemsToInventory(targetInventory, itemA, 6, false));

            //Validate target counts
            Assertions.assertEquals(6, targetInventory.countItems(itemA));
            Assertions.assertEquals(30, targetInventory.countItems(null));

            //Validate from counts
            Assertions.assertEquals(5, fromInventory.countItems(itemA));
            Assertions.assertEquals(2, fromInventory.countItems(itemB));
            Assertions.assertEquals(20, fromInventory.countItems(null));
        }

        @Test
        void moveItems_simulate() {
            final Inventory fromInventory = new Inventory(5, 10);
            fromInventory.setSlot(0, itemA, 5);
            fromInventory.setSlot(1, itemB, 2);
            fromInventory.setSlot(2, itemA, 5);
            fromInventory.setSlot(3, itemA, 1);

            final Inventory targetInventory = new Inventory(5, 10);

            Assertions.assertTrue(fromInventory.moveAllItemsToInventory(targetInventory, itemA, 6, true));

            //Validate target counts
            Assertions.assertEquals(0, targetInventory.countItems(itemA));
            Assertions.assertEquals(50, targetInventory.countItems(null));

            //Validate from counts
            Assertions.assertEquals(11, fromInventory.countItems(itemA));
            Assertions.assertEquals(2, fromInventory.countItems(itemB));
            Assertions.assertEquals(10, fromInventory.countItems(null));
        }
    }

    @Nested
    class AddItemToInventory
    {
        final int TOTAL_SLOTS = 10;
        final int MAX_SLOT_COUNT = 20;

        Inventory inventory;

        @BeforeEach
        void beforeEach() {
            inventory = new Inventory(TOTAL_SLOTS, MAX_SLOT_COUNT);
        }

        @ParameterizedTest(name = "empty_inv: [{index}] Insert: ({1} x {0}), Added: {2}, Simulated: {3}, ItemCount: {4}, AirCount: {5}")
        @MethodSource("com.builtbroken.example.smith.data.inventory.InventoryTest#emptyInventoryAddItemsData")
        void emptyInventory(final Item insertItem, final int insertCount,
                            final int expectedAdd,
                            final boolean simulated,
                            final int expectedInsertedCount, final int expectedAirCount)
        {
            final int result = inventory.addItemToInventory(insertItem, insertCount, simulated);
            Assertions.assertEquals(expectedAdd, result);

            //Validate slot
            Assertions.assertEquals(expectedInsertedCount, inventory.countItems(itemA));
            Assertions.assertEquals(expectedAirCount, inventory.countItems(null));
        }

        //TODO test with mixed inventory

        //TODO test with same item inventory

        //TODO test with full inventory
    }

    private static Stream<Arguments> emptyInventoryAddItemsData() {
        final int maxAir = 20 * 10;
        return Stream.of(
                //Simulated: add 1 item
                Arguments.of(
                        itemA, 1,
                        1,
                        true,
                        0,
                        maxAir
                ),
                //Simulated: add 1 stack
                Arguments.of(
                        itemA, itemA.getMaxStack(),
                        itemA.getMaxStack(),
                        true,
                        0,
                        maxAir
                ),
                //Simulated: add 4 stack + 3 items
                Arguments.of(
                        itemA, 23,
                        23,
                        true,
                        0,
                        maxAir
                ),
                //Simulate: add 10 stacks
                Arguments.of(
                        itemA, 50,
                        50,
                        true,
                        0,
                        maxAir
                ),
                //Simulate: add 20 stacks
                Arguments.of(
                        itemA, 100,
                        50,
                        true,
                        0,
                        maxAir
                ),

                //Actual: add 1 item
                Arguments.of(
                        itemA, 1,
                        1,
                        false,
                        1,
                        9 * 20
                ),
                //Actual: add 1 stack
                Arguments.of(
                        itemA, itemA.getMaxStack(),
                        itemA.getMaxStack(),
                        false,
                        itemA.getMaxStack(),
                        9 * 20
                ),
                //Actual: add 4 stack + 3 items
                Arguments.of(
                        itemA, 23,
                        23,
                        false,
                        23,
                        5 * 20
                ),
                //Actual: add 10 stacks
                Arguments.of(
                        itemA, 50,
                        50,
                        false,
                        50,
                        0
                ),
                //Actual: add 20 stacks
                Arguments.of(
                        itemA, 100,
                        50,
                        false,
                        50,
                        0
                )
        );
    }

    @Nested
    class AddItemToSlot
    {

        @ParameterizedTest(name = "[{index}] Slot: ({2} x {1} / {0}), Insert: ({4} x {3}), Added: {5}, Simulated: {6}, SlotEnd: ({8} X {7} / {0})")
        @MethodSource("com.builtbroken.example.smith.data.inventory.InventoryTest#addItemToSlotData")
        void addItemToSlot(final int inventoryLimit,
                                        final Item slotItem, final int slotCount,
                                        final Item insertItem, final int insertCount,
                                        final int expectedAdd,
                                        final boolean simulated,
                                        final Item slotFinalItem, final int slotFinalCount)
        {
            final int slotIndex = 0;

            //Test setup
            final Inventory inventory = new Inventory(1, inventoryLimit);
            inventory.setSlot(slotIndex, slotItem, slotCount);

            //Invoke
            final int result = inventory.addItemToSlot(insertItem, slotIndex, insertCount, simulated);

            //Validate addition
            Assertions.assertEquals(expectedAdd, result);

            //Validate slot
            Assertions.assertEquals(slotFinalItem, inventory.getSlot(slotIndex).getItem());
            Assertions.assertEquals(slotFinalCount, inventory.getSlot(slotIndex).getCount());
        }
    }

    private static Stream<Arguments> addItemToSlotData()
    {


        return Stream.of(

                ///Simulated = true
                //[ItemA@1/5] + ItemB@1/5 -> 0, match:false, empty:false, roomLeft:N/A
                Arguments.of(5,
                        itemA, 1,
                        itemB, 1,
                        0,
                        true,
                        itemA, 1),

                //[ItemA@5/5] + ItemA@1/5 -> 0, match:true, empty:N/A, roomLeft:false
                Arguments.of(5,
                        itemA, 5,
                        itemB, 1,
                        0,
                        true,
                        itemA, 5),

                //[null@0/10] + ItemA@5/5 -> 5
                Arguments.of(10,
                        null, 0,
                        itemA, 5,
                        5,
                        true,
                        null, 0),

                //[null@0/2] + ItemA@5/5 -> 2
                Arguments.of(2,
                        null, 0,
                        itemA, 5,
                        2,
                        true,
                        null, 0),

                ///[ItemA@1/5] + ItemA@5/5 -> 4
                Arguments.of(5,
                        itemA, 1,
                        itemA, 5,
                        4,
                        true,
                        itemA, 1),

                ///[ItemA@1/5] + ItemA@2/5 -> 3
                Arguments.of(5,
                        itemA, 1,
                        itemA, 2,
                        2,
                        true,
                        itemA, 1),

                ///Simulated = false
                //[ItemA@1/5] + ItemB@1/5 -> 0, match:false, empty:false, roomLeft:N/A
                Arguments.of(5,
                        itemA, 1,
                        itemB, 1,
                        0,
                        false,
                        itemA, 1),

                //[ItemA@5/5] + ItemA@1/5 -> 0, match:true, empty:N/A, roomLeft:false
                Arguments.of(5,
                        itemA, 5,
                        itemB, 1,
                        0,
                        false,
                        itemA, 5),

                //[null@0/10] + ItemA@5/5 -> 5
                Arguments.of(10,
                        null, 0,
                        itemA, 5,
                        5,
                        false,
                        itemA, 5),

                //[null@0/2] + ItemA@5/5 -> 2
                Arguments.of(2,
                        null, 0,
                        itemA, 5,
                        2,
                        false,
                        itemA, 2),

                ///[ItemA@1/5] + ItemA@5/5 -> 4
                Arguments.of(5,
                        itemA, 1,
                        itemA, 5,
                        4,
                        false,
                        itemA, 5),

                ///[ItemA@1/5] + ItemA@2/5 -> 2
                Arguments.of(5,
                        itemA, 1,
                        itemA, 2,
                        2,
                        false,
                        itemA, 3)
        );
    }
}
