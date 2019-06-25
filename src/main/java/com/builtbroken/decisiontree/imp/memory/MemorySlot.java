package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class MemorySlot<O extends Object> implements IMemorySlot<O, IMemoryValue<O>>
{
    protected String name;
    private int id;

    @Override
    public String getUniqueName()
    {
        return name;
    }

    @Override
    public String getDisplayValue(IMemoryContext memory)
    {
        return DTReferences.JSON_MEMORY;
    }

    @Override
    public int getSlotID()
    {
        return id;
    }

    @Override
    public void setSlotID(int index)
    {
        this.id = index;
    }
}
