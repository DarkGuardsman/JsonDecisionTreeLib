package com.builtbroken.decisiontree.imp.memory.slot;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class StringMemory extends MemorySlot<StringMemory, String, StringMemoryValue>
{
    public static StringMemory build(String name)
    {
        StringMemory memory = new StringMemory();
        memory.name = name;
        return memory;
    }

    @Override
    public StringMemoryValue newValue(@Nonnull IMemoryContext memory, @Nullable String oldValue)
    {
        return new StringMemoryValue().setSlot(this).setValue(oldValue);
    }
}
