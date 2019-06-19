package com.builtbroken.decisiontree.api;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IActionTree
{

    /**
     * Start of the logic tree for the actor.
     * This can be a single action or a series of
     * actions in which 1 will be run to start the logic path
     *
     * @return
     */
    IAction getEntryPoint();
}
