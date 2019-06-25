package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public abstract class MemoryValue<O> implements IMemoryValue<O>
{
    private final IMemorySlot<O, IMemoryValue<O>> slot;

    public MemoryValue(IMemorySlot<O, IMemoryValue<O>> slot)
    {
        this.slot = slot;
    }

    @Nonnull
    @Override
    public IMemorySlot getSlot()
    {
        return slot;
    }
}
