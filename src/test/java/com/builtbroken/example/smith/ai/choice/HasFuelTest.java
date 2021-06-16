package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.example.smith.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
class HasFuelTest
{
    @Test
    void testHasFuel_true() {
        //Setup test world
        final World world = new World();
        world.getFurnace().addFuelItem(1);

        //Test expectations of starting conditions
        Assertions.assertEquals(10, world.getFurnace().getFuelTime());

        //Invoke task
        final HasFuel hasFuel = HasFuel.build("has_fuel");

        //Test expectations
        Assertions.assertTrue(hasFuel.isTrue(world, null));
    }

    @Test
    void testHasFuel_false() {
        //Setup test world
        final World world = new World();

        //Test expectations of starting conditions
        Assertions.assertEquals(0, world.getFurnace().getFuelTime());

        //Invoke task
        final HasFuel hasFuel = HasFuel.build("has_fuel");

        //Test expectations
        Assertions.assertFalse(hasFuel.isTrue(world, null));
    }
}
