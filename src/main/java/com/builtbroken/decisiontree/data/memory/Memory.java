package com.builtbroken.decisiontree.data.memory;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.data.context.ActionContext;

import java.util.Map;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class Memory extends ActionContext implements IMemoryContext
{

    private Map<String, IMemorySlot> nameToSlot;
    private IMemoryValue[] memoryValues;
    private IMemorySlot[] slots;

    @Override
    public Object getValueStored(int index)
    {
        if (slots != null && index >= 0 && index < slots.length)
        {
            return getValueStored(slots[index]);
        }
        return null;
    }

    @Override
    public <O extends Object, M extends IMemoryValue<O, M>> M getValueStored(IMemorySlot<O, M> slot)
    {
        final int index = slot.getSlotID();
        if (memoryValues != null && index >= 0 && index < memoryValues.length)
        {
            final IMemoryValue value = memoryValues[index];
            if (value != null)
            {
                if (value.getSlot() == slot)
                {
                    return (M) value.getValue();
                }
            }
            else
            {
                memoryValues[index] = slot.newValue(this, null);
            }
        }
        return null;
    }

    @Override
    public void mapSlots(Iterable<IMemoryValue> slots)
    {

    }

    @Override
    public void clear()
    {
        memoryValues = null;
    }
}
