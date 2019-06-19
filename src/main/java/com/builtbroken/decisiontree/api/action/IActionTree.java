package com.builtbroken.decisiontree.api.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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

    /**
     * Called to copy the tree. This is done
     * if modifications to the tree are needed
     * but the orginal needs to remain.
     * <p>
     * Example: Parenting of trees
     *
     * @return
     */
    IActionTree copy();

    /**
     * Called to bake all settings and elements of the tree.
     * This is done to prep the tree for runtime usage.
     * <p>
     * Generates {@link #getMemorySlots()} return
     */
    void bakeTree();

    /**
     * Reserved memory slots of this tree. Used
     * by {@link com.builtbroken.decisiontree.api.context.IMemoryContext}
     *
     * @return memory slots
     */
    Map<String, Integer> getMemorySlots();

    /**
     * Called to collect all actions for use in other process.
     * This is normally used to collect memory variables
     * and other information during setup.
     *
     * @param collector - thing that wants to collect
     */
    default void collectActions(Consumer<IAction> collector)
    {
        if (getEntryPoint() != null)
        {
            getEntryPoint().collectActions(collector);
        }
    }
}
