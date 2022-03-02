package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public abstract class MemoryValue<O, M extends MemoryValue<O, M>> implements IMemoryValue<O, M>
{

    private IMemorySlot slot;

    public M setSlot(IMemorySlot slot)
    {
        this.slot = slot;
        return (M) this;
    }

    @Nonnull
    @Override
    public IMemorySlot getSlot()
    {
        return slot;
    }
}
