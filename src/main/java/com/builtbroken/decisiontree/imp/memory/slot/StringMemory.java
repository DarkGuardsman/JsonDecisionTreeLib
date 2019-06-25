package com.builtbroken.decisiontree.imp.memory.slot;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.IntegerMemoryValue;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class StringMemory extends MemorySlot<String>
{
    @Override
    public IMemoryValue<String> newValue(@Nonnull IMemoryContext memory, @Nullable String oldValue)
    {
        return new StringMemoryValue(this).setValue(oldValue);
    }
}
