package com.builtbroken.decisiontree.logic;

import com.builtbroken.decisiontree.api.ActionResult;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActionContext;
import com.builtbroken.decisiontree.data.ActionTree;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActorLogic
{

    public ActionTree tree;

    private IAction currentAction;

    private IActionContext lastActiontTrigger;
    private IActionContext worldContext;

    private Consumer<String> errorHandler = (string) -> System.out.println("Error: " + string);

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
                currentAction.start(lastActiontTrigger, worldContext);
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
        final ActionResult result = currentAction.update(lastActiontTrigger, worldContext, tick, delta);
        if (result == ActionResult.COMPLETE || result == ActionResult.END)
        {

            //End AI
            if (result == ActionResult.END)
            {
                reset();
                return true;
            }
            //End AI loop
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
        final ActionResult result = currentAction.start(lastActiontTrigger, worldContext);
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
        currentAction = currentAction.end(lastActiontTrigger, worldContext);
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
