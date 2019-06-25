package com.builtbroken.decisiontree.imp.memory.value;


import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.imp.memory.MemoryValue;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class StringMemoryValue extends MemoryValue<String, StringMemoryValue>
{
    private String string;

    public StringMemoryValue(IMemorySlot<String, StringMemoryValue> slot)
    {
        super(slot);
    }

    @Nullable
    @Override
    public String getValue()
    {
        return String.valueOf(string);
    }

    @Override
    public StringMemoryValue setValue(@Nullable String value)
    {
        string = value;
        return this;
    }

    @Override
    public boolean hasValue()
    {
        return string != null;
    }
}
