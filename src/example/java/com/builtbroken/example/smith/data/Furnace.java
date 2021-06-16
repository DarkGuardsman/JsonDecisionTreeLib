package com.builtbroken.example.smith.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@Data
public class Furnace
{
    @Setter(value = AccessLevel.NONE)
    private int cookTime = 0;
    @Setter(value = AccessLevel.NONE)
    private int fuelTime = 0;

    @Setter(value = AccessLevel.NONE)
    private int inputCount = 0;
    @Setter(value = AccessLevel.NONE)
    private int outputCount = 0;

    public void tick()
    {

    }


    public boolean isRunning()
    {
        return cookTime > 0 && fuelTime > 0;
    }

    public void addFuelItem(int count)
    {
       addFuel(count * 10);
    }

    public void addFuel(int time)
    {
        fuelTime += time;
    }

    public void addInput(int count)
    {
        inputCount += count;
    }

    public void takeOutput(int count)
    {
        outputCount -= count;
    }
}
