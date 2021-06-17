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
        world.getFurnace().setFuelTime(10);

        //Invoke task
        final HasFuel hasFuel = HasFuel.build("has_fuel");
        final boolean result = hasFuel.isTrue(world, null);

        //Test expectations
        Assertions.assertTrue(result);
    }

    @Test
    void testHasFuel_false() {
        //Setup test world
        final World world = new World();

        //Invoke task
        final HasFuel hasFuel = HasFuel.build("has_fuel");
        final boolean result = hasFuel.isTrue(world, null);

        //Test expectations
        Assertions.assertFalse(result);
    }
}
