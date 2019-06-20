package com.builtbroken.decisiontree.api.memory;

import com.builtbroken.decisiontree.api.context.IMemoryContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IMemorySlot<O extends Object>
{

    /**
     * Unique ID for the memory slot
     * <p>
     * Needs to be unique for the actor not the entire runtime.
     * Though it can be useful to ensure global unique memory
     * in case addons provide expanded trees for the actor later.
     * As well memory slots can be shared so its best to pick
     * a unique and use it over several actions as needed
     *
     * @return unique string value
     */
    String getUniqueName();

    /**
     * Retrieves a display version of the memory
     *
     * @param memory
     * @return
     */
    String getDisplayValue(IMemoryContext memory);

    /**
     * Gets the index of the memory in the {@link IMemoryContext}
     * <p>
     * Int is used for faster lookup times rather than using
     * a hash of {@link #getUniqueName()}
     *
     * @return id
     */
    int getSlotID();

    /**
     * Sets the memory slot
     * <p>
     * This is called on setup of the action tree
     * once during runtime. If the tree is changed
     * it will be called again.
     *
     * @param index
     */
    void setSlotID(int index);

    /**
     * Gets the value of the memory
     *
     * @param memory
     * @return
     */
    O getValue(IMemoryContext memory);

    /**
     * Checks if we have a value stored
     *
     * @param memory
     * @return
     */
    boolean hasValue(IMemoryContext memory);
}
