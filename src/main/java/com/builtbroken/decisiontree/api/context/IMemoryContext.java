package com.builtbroken.decisiontree.api.context;

import java.util.Map;

/**
 * Context about an {@link com.builtbroken.decisiontree.logic.ActorLogic} memory
 * <p>
 * Memory is by index for faster lookup times. This way be done as an array or a map.
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IMemoryContext extends IActorContext
{

    /**
     * Get value stored in the memory slot
     *
     * @param index
     * @return
     */
    Object getValueStored(int index);

    /**
     * Called to map memory names to slot ids.
     * <p>
     * If the context had previous memory it will
     * attempt to map the old indexes to the new
     * indexes.
     *
     * @param slots
     */
    void mapSlots(Map<String, Integer> slots);

    /**
     * Clears all stored memory
     */
    void clear();
}
