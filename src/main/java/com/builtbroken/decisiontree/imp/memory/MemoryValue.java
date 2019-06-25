package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public abstract class MemoryValue<O, M extends MemoryValue<O, M>> implements IMemoryValue<O, M>
{
    private final IMemorySlot<O, M> slot;

    public MemoryValue(IMemorySlot<O, M> slot)
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
