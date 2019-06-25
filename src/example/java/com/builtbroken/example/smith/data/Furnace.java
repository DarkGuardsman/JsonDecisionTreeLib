package com.builtbroken.example.smith.data;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public class Furnace
{

    public int cookTime = 0;
    public int fuelTime = 0;

    public int inputCount = 0;
    public int outputCount = 0;

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
