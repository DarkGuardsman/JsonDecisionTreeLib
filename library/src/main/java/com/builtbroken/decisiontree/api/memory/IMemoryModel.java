package com.builtbroken.decisiontree.api.memory;

/**
 * Representation of how memory is mapped.
 * <p>
 * Memory models are shared between AIs that use the same
 * logic trees.
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public interface IMemoryModel
{
    IMemorySlot getSlot(int index);

    int size();
}
