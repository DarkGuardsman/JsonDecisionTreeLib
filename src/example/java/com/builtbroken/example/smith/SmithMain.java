package com.builtbroken.example.smith;

import com.builtbroken.decisiontree.data.context.ActorContext;
import com.builtbroken.decisiontree.logic.ActorLogic;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public class SmithMain
{

    public static void main(String... args)
    {
        //Setup
        final ActorLogic actorLogic = new ActorLogic(new ActorContext("smithy"));
        final World world = new World();

        //init
        actorLogic.setStage(world, null, false);


        //Run loop
        try
        {
            int tick = 0;
            boolean running = true;
            long lastLoopTime = System.currentTimeMillis();
            while (running)
            {
                loop(world, tick);
                lastLoopTime = sleep(lastLoopTime);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static long sleep(long lastLoopTime) throws InterruptedException
    {
        final long current = System.currentTimeMillis();
        lastLoopTime = current - lastLoopTime;
        if (lastLoopTime < 50)
        {
            Thread.sleep(lastLoopTime);
        }
        return current;
    }

    public static void loop(World world, int tick)
    {

    }
}
