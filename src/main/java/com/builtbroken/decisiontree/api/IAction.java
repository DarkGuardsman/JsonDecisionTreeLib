package com.builtbroken.decisiontree.api;

/**
 * Action is anything the {@link IActor} does to react to the world through {@link IActionContext}
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
    boolean canAction(IActionContext  outputContext);

    /**
     * Called to do the action
     *
     * @param triggerContext - context to decide what action to use
     * @param outputContext  - context to act on the environment to complete the action
     * @return true if action was run to some extent even if it failed
     */
    boolean trigger(IActionContext  triggerContext, IActionContext outputContext);
}
