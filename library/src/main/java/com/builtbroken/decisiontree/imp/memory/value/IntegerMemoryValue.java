package com.builtbroken.decisiontree.imp.memory.value;

import com.builtbroken.decisiontree.imp.memory.MemoryValue;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class IntegerMemoryValue extends MemoryValue<Integer, IntegerMemoryValue>
{
    private int number;

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
    public IntegerMemoryValue setValue(@Nullable Integer value)
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

    public void sub(int count)
    {
        set(get() - count);
    }

    public void add(int count)
    {
        set(get() + count);
    }

    public void multi(int count)
    {
        set(get() * count);
    }

    public void div(int count)
    {
        set(get() / count);
    }

    @Override
    public boolean hasValue()
    {
        return true;
    }
}
