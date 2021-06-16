package com.builtbroken.example.smith.ai.action;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.example.smith.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/15/2021.
 */
class AddFuelTest
{
    @Test
    void testAddFuel() {

        //Setup test world
        final World world = new World();
        world.getChest().setFuelItems(2);

        //Test expectations of starting conditions
        Assertions.assertEquals(0, world.getFurnace().getFuelTime());
        Assertions.assertEquals(2, world.getChest().getFuelItems());

        //Invoke task
        final AddFuel fuelTask = AddFuel.build("fuel_add");
        final ActionResult result = fuelTask.start(world, null);

        //Validate
        Assertions.assertEquals(ActionResult.STEP, result);
        Assertions.assertEquals(1, world.getChest().getFuelItems());
        Assertions.assertEquals(10, world.getFurnace().getFuelTime());
    }

    @Test
    void testNoFuelToAdd() {

        //Setup test world
        final World world = new World();

        //Test expectations of starting conditions
        Assertions.assertEquals(0, world.getFurnace().getFuelTime());
        Assertions.assertEquals(0, world.getChest().getFuelItems());

        //Invoke task
        final AddFuel fuelTask = AddFuel.build("fuel_add");
        final ActionResult result = fuelTask.start(world, null);

        //Validate
        Assertions.assertEquals(ActionResult.PASS, result);
        Assertions.assertEquals(0, world.getChest().getFuelItems());
        Assertions.assertEquals(0, world.getFurnace().getFuelTime());
    }
}
