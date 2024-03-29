package com.builtbroken.decisiontree.logic;

import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IActorContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.data.ActionTree;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActorLogic
{
    public ActionTree tree;

    //Information about the actor
    private final IActorContext actorContext;

    //Current task
    private IAction currentAction;

    private IMemoryContext memory;
    private IWorldContext world;

    private final Consumer<String> errorHandler = (string) -> System.out.println("Error: " + string);

    public ActorLogic(IActorContext actorContext)
    {
        this.actorContext = actorContext;
    }

    public void setStage(@Nonnull IWorldContext worldContext, @Nullable IMemoryContext memory, boolean reset)
    {
        this.world = worldContext;
        this.memory = memory;

        //init
        worldContext.init(actorContext);
        if(memory != null)
        {
            memory.init(actorContext);
        }

        //Reset
        if (reset)
        {
            reset();
        }
    }

    /**
     * Called to update the actor
     *
     * @param tick  - current tick count
     * @param delta - time since last tick
     */
    public void update(int tick, float delta)
    {
        if (currentAction == null)
        {
            currentAction = tree.getEntryPoint();
            if (currentAction != null)
            {
                currentAction.start(world, memory);
            }
        }

        //Loop until we run of of task or get an end call
        boolean end = false;
        while (currentAction != null && !end)
        {
            end = doAction(tick, delta);
        }
    }

    protected boolean doAction(int tick, float delta)
    {
        final ActionResult result = currentAction.update(world, memory, tick, delta);
        if (result == ActionResult.COMPLETE || result == ActionResult.END)
        {

            //End AI loop, reset ai
            if (result == ActionResult.END)
            {
                reset();
                return true;
            }
            //End AI loop, but keep current action
            else if (result == ActionResult.STEP)
            {
                return true;
            }

            return endAction();
        }
        else if (result == ActionResult.ERROR)
        {
            errorHandler.accept(" action[" + currentAction + "] errored during update tick, resetting AI tree.");
            reset();
            return true;
        }
        return false;
    }

    protected boolean startAction()
    {
        final ActionResult result = currentAction.start(world, memory);
        if (result == ActionResult.END)
        {
            reset();
            return true;
        }
        else if (result == ActionResult.COMPLETE)
        {
            return endAction();
        }
        else if (result == ActionResult.STEP)
        {
            return true;
        }
        else if (result == ActionResult.ERROR)
        {
            errorHandler.accept(" action[" + currentAction + "] errored during start tick, resetting AI tree.");
            reset();
            return true;
        }
        return false;
    }

    protected boolean endAction()
    {
        //Start next action
        currentAction = currentAction.end(world, memory);
        if (currentAction != null)
        {
            return startAction();
        }
        //End AI as we have no next
        else
        {
            reset();
            return true;
        }
    }

    public void reset()
    {
        currentAction = tree.getEntryPoint();
    }
}
