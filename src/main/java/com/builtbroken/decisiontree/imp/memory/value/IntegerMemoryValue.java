package com.builtbroken.decisiontree.imp.memory.value;


import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.imp.memory.MemoryValue;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class IntegerMemoryValue extends MemoryValue<Integer>
{
    private int number;

    public IntegerMemoryValue(IMemorySlot<Integer, IMemoryValue<Integer>> slot)
    {
        super(slot);
    }

    @Nullable
    @Override
    public Integer getValue()
    {
        return Integer.valueOf(number);
    }

    public int get()
    {
        return number;
    }

    @Override
    public IMemoryValue<Integer> setValue(@Nullable Integer value)
    {
        if (value != null)
        {
            number = (int) value;
        }
        else
        {
            number = 0;
        }
        return this;
    }

    public void set(int i)
    {
        this.number = i;
    }

    @Override
    public boolean hasValue()
    {
        return true;
    }
}
