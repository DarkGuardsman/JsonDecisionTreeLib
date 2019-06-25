package com.builtbroken.decisiontree.imp.memory.value;


import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.imp.memory.MemoryValue;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class ObjectMemoryValue extends MemoryValue<Object>
{

    private Object object;

    public ObjectMemoryValue(IMemorySlot<Object, IMemoryValue<Object>> slot)
    {
        super(slot);
    }

    @Nullable
    @Override
    public Object getValue()
    {
        return object;
    }

    @Override
    public IMemoryValue<Object> setValue(@Nullable Object value)
    {
        this.object = value;
        return this;
    }

    @Override
    public boolean hasValue()
    {
        return getValue() != null;
    }
}
