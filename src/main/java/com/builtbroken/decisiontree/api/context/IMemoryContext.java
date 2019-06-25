package com.builtbroken.decisiontree.api.context;

import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Context about an {@link com.builtbroken.decisiontree.logic.ActorLogic} memory
 * <p>
 * Memory is by index for faster lookup times. This way be done as an array or a map.
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IMemoryContext extends IActionContext
{

    /**
     * Get value stored in the memory slot
     *
     * @param index - index
     * @return
     */
    @Nullable
    Object getValueStored(int index);


    /**
     * Get value stored in the memory slot
     *
     * @param slot - memory slot
     * @return value object
     */
    @Nullable
    <O extends Object, M extends IMemoryValue<O, M>> M getValueStored(IMemorySlot<O, M> slot);

    /**
     * Called to map memory names to slot ids.
     * <p>
     * If the context had previous memory it will
     * attempt to map the old indexes to the new
     * indexes.
     *
     * @param slots
     */
    void mapSlots(@Nonnull Iterable<IMemoryValue> slots);

    /**
     * Clears all stored memory
     */
    void clear();
}
