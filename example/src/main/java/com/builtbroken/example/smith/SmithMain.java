package com.builtbroken.example.smith;

import com.builtbroken.builder.ContentBuilderLib;
import com.builtbroken.builder.handler.IJsonObjectHandler;
import com.builtbroken.builder.loader.file.FileLocatorSimple;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.data.ActionTree;
import com.builtbroken.decisiontree.data.context.ActorContext;
import com.builtbroken.decisiontree.logic.ActorLogic;
import com.builtbroken.example.game.Game;

import java.io.File;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public class SmithMain
{
    public static void main(String... args)
    {
        //Setup
        loadContent();

        //Build game
        final Game game = new Game();
        game.addActor(generateAI());

        //Run game
        startLoop(game, 100000);
    }

    //Handles loading all the JSON data from the file system
    private static void loadContent()
    {
        ContentBuilderLib.getMainLoader().addFileLocator(new FileLocatorSimple(new File(System.getProperty("user.dir"), "/json")));
        ContentBuilderLib.setup();
        ContentBuilderLib.load();
    }

    //Generates our main AI, future this might be more than 1 AI interacting with the same world or each other
    private static ActorLogic generateAI() {
        final ActorLogic actorLogic = new ActorLogic(new ActorContext("smithy"));
        final IJsonObjectHandler<ActionTree> handler = (IJsonObjectHandler<ActionTree>) ContentBuilderLib.getMainLoader()
                .jsonObjectHandlerRegistry.getHandler(TreeTemplateTypes.TREE);
        actorLogic.tree = handler.getObject("smith:furnace.insert.fuel");
        return actorLogic;
    }

    //Generic game loop
    private static void startLoop(final Game game, final int tickCutOff)
    {
        try
        {
            int tick = 0;
            long lastLoopTime = System.currentTimeMillis();
            while (tick <= tickCutOff && game.isRunning)
            {
                //Increase tick
                tick++;

                //Logging
                System.out.println("Tick: " + tick);

                //Tick game
                game.tick(tick, 0);

                lastLoopTime = sleep(lastLoopTime); //TODO track delta and pass into loop
            }
        }
        catch (Exception e)
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
