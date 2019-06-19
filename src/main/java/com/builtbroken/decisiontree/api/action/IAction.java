package com.builtbroken.decisiontree.api.action;

import com.builtbroken.decisiontree.api.context.IActionContext;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;

import java.util.function.Consumer;

/**
 * Action is anything the {@link IActionTree} does to react to the world through {@link IActionContext}
 * <p>
 * Actions are shared between AI so do not store anything in the action object itself. Instead
 * use some external system or the AI itself to track information about the state of the AI.
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IAction
{

    /**
     * Priority of this action, only used
     * when several actions are being considered
     * for runtime.
     *
     * @return int priority, used by sorters
     */
    int getPriority();

    /**
     * Called to do the action
     *
     * @param world  - information about the world the actor is inside
     * @param memory - memory stored of previous actions
     * @return result of start
     * <p>
     * return pass to ignore this action in a chain of actions
     * return complete if this is a one off action with no continue
     * return running to continue to receive ticks on the action
     */
    ActionResult start(IWorldContext world, IMemoryContext memory);


    /**
     * Called each update of the action
     *
     * @param world  - information about the world the actor is inside
     * @param memory - memory stored of previous actions
     * @param tick   - tick count
     * @param delta  - time since last tick
     * @return state of the action
     */
    default ActionResult update(IWorldContext world, IMemoryContext memory, int tick, float delta)
    {
        return ActionResult.COMPLETE;
    }

    /**
     * Called at the end of the action
     *
     * @param world  - information about the world the actor is inside
     * @param memory - memory stored of previous actions
     * @return next action to run
     */
    default IAction end(IWorldContext world, IMemoryContext memory)
    {
        return null;
    }

    /**
     * Called to collect all actions for use in other process.
     * This is normally used to collect memory variables
     * and other information during setup.
     *
     * @param collector - thing that wants to collect
     */
    default void collectActions(Consumer<IAction> collector)
    {
        collector.accept(this);
    }

    /**
     * Called to copy the action in the case
     * of {@link IActionTree#copy()}
     *
     * @return copy of the action and all sub actions/settings
     */
    IAction copy();
}