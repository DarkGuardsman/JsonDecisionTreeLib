package com.builtbroken.example.smith;

import com.builtbroken.builder.ContentBuilderLib;
import com.builtbroken.builder.handler.IJsonObjectHandler;
import com.builtbroken.builder.loader.file.FileLocatorSimple;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.data.ActionTree;
import com.builtbroken.decisiontree.data.context.ActorContext;
import com.builtbroken.decisiontree.logic.ActorLogic;
import com.builtbroken.example.smith.data.World;

import java.io.File;

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

        //Run content engine
        ContentBuilderLib.getMainLoader().addFileLocator(new FileLocatorSimple(new File(System.getProperty("user.dir"), "/json")));
        ContentBuilderLib.setup();
        ContentBuilderLib.load();

        //GET AI
        final IJsonObjectHandler<ActionTree> handler = (IJsonObjectHandler<ActionTree>) ContentBuilderLib.getMainLoader()
                .jsonObjectHandlerRegistry.getHandler(TreeTemplateTypes.TREE);
        actorLogic.tree = handler.getObject("smith:furnace.insert.fuel");

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
                //Increase tick
                tick++;

                //Logging
                System.out.println("Tick: " + tick);

                //Tick world
                world.tick(tick);

                //Tick AI
                actorLogic.update(tick, 0);
                running = tick <= 100000; //TODO tie to AI state

                lastLoopTime = sleep(lastLoopTime); //TODO track delta and pass into loop
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
}
