package com.builtbroken.decisiontree.api.memory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public interface IMemoryValue<O, M extends IMemoryValue<O, M>>
{

    /**
     * Gets the slot that goes with this value.
     * <p>
     * Used for accessing information about the slot
     * for mapping and storing the value.
     *
     * @return
     */
    @Nonnull
    IMemorySlot<O, M> getSlot();

    /**
     * Gets the stored value
     *
     * @return
     */
    @Nullable
    O getValue();

    /**
     * Called set the value into storage
     *
     * @param value
     */
    M setValue(@Nullable O value);


    /**
     * Checks to see if a value is stored in memory
     *
     * @return
     */
    boolean hasValue();
}
