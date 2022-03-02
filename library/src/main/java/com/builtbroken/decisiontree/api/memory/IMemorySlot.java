package com.builtbroken.decisiontree.api.memory;

import com.builtbroken.decisiontree.api.context.IMemoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IMemorySlot<S extends IMemorySlot<S, O, M>, O extends Object, M extends IMemoryValue<O, M>>
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
    @Nonnull
    String getUniqueName();

    /**
     * Retrieves a display version of the memory
     *
     * @param memory
     * @return
     */
    @Nonnull
    default String getDisplayValue(@Nonnull IMemoryContext memory)
    {
        final M value = memory.getValueStored(this);
        if (value != null)
        {
            return getUniqueName() + "='" + value.getValue() + "'";
        }
        return getUniqueName() + "=null";
    }

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
     * called to generate a new memory value object
     * for storing values.
     *
     * @param memory   - memory that will store this value
     * @param oldValue - old value if it existed
     * @return
     */
    M newValue(@Nonnull IMemoryContext memory, @Nullable O oldValue);

    /**
     * Gets the value of the memory
     *
     * @param memory - memory to use
     * @return
     */
    default O getValue(@Nonnull IMemoryContext memory)
    {
        final M value = memory.getValueStored(this);
        if (value != null)
        {
            return value.getValue();
        }
        return null;
    }

    /**
     * Gets the value wrapper stored in memory.
     * <p>
     * This can be a more effective way to access some
     * types of memory such as integers, and bytes that
     * may be inefficient to access as an object.
     *
     * @param memory - memory to use
     * @return value stored, or null if nothing is stored
     */
    default M getMemory(@Nonnull IMemoryContext memory)
    {
        return memory.getValueStored(this);
    }

    /**
     * Checks if we have a value stored
     *
     * @param memory - memory to use
     * @return
     */
    default boolean hasValue(IMemoryContext memory)
    {
        final M value = memory.getValueStored(this);
        if (value != null)
        {
            return value.hasValue();
        }
        return false;
    }

    /**
     * Called to create a copy of the component.
     * <p>
     * Used when it duplicating logic trees for usage
     * in other places without worrying about
     * referenced being changed between trees by mistake.
     *
     * @return copy
     */
    @Nonnull
    S copy();
}
