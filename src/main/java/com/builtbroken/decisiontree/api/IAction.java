package com.builtbroken.decisiontree.api;

/**
 * Action is anything the {@link IActionTree} does to react to the world through {@link IActionContext}
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IAction
{

    int getPriority();

    /**
     * Called to check if the context can trigger the action
     *
     * @param triggerContext
     * @return
     */
    boolean canTrigger(IActionContext triggerContext);

    /**
     * Called to check if the action can work in the context
     *
     * @param outputContext
     * @return
     */
    boolean canAction(IActionContext outputContext);

    /**
     * Called to do the action
     *
     * @param triggerContext - context to decide what action to use
     * @param outputContext  - context to act on the environment to complete the action
     * @return result of start
     * <p>
     * return pass to ignore this action in a chain of actions
     * return complete if this is a one off action with no continue
     * return running to continue to receive ticks on the action
     */
    ActionResult start(IActionContext triggerContext, IActionContext outputContext);


    /**
     * Called each update of the action
     *
     * @param triggerContext - context to decide what action to use
     * @param outputContext  - context to act on the environment to complete the action
     * @param tick           - tick count
     * @param delta          - time since last tick
     * @return state of the action
     */
    default ActionResult update(IActionContext triggerContext, IActionContext outputContext, int tick, float delta)
    {
        return ActionResult.COMPLETE;
    }

    /**
     * Called at the end of the action
     *
     * @param triggerContext - context to decide what action to use
     * @param outputContext  - context to act on the environment to complete the action
     * @return next action to run
     */
    default IAction end(IActionContext triggerContext, IActionContext outputContext)
    {
        return null;
    }
}
