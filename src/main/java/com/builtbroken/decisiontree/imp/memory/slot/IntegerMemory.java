package com.builtbroken.decisiontree.imp.memory.slot;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.IntegerMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class IntegerMemory extends MemorySlot<IntegerMemory, Integer, IntegerMemoryValue>
{

    public static IntegerMemory build(String name)
    {
        IntegerMemory memory = new IntegerMemory();
        memory.name = name;
        return memory;
    }

    @Override
    public IntegerMemoryValue newValue(@Nonnull IMemoryContext memory, @Nullable Integer oldValue)
    {
        return new IntegerMemoryValue().setSlot(this).setValue(oldValue);
    }

    public int get(IMemoryContext memory)
    {
        final IntegerMemoryValue value = getMemory(memory);
        if (value != null)
        {
            return value.get();
        }
        return 0;
    }

    public void set(IMemoryContext memory, int n)
    {
        final IntegerMemoryValue value = getMemory(memory);
        if (value != null)
        {
            value.set(n);
        }
    }
}
