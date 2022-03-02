package com.builtbroken.decisiontree.data.memory;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemoryModel;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.data.context.ActionContext;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class Memory extends ActionContext implements IMemoryContext
{

    private IMemoryValue[] memoryValues;
    private IMemoryModel memoryModel;

    @Override
    public Object getValueStored(int index)
    {
        return getValueStored(memoryModel.getSlot(index));
    }

    @Override
    public <S extends IMemorySlot<S, O, M>,
            O extends Object,
            M extends IMemoryValue<O, M>>
    M getValueStored(IMemorySlot<S, O, M> slot)
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
    public void mapModel(@Nonnull IMemoryModel model)
    {
        this.memoryModel = model;
    }

    @Override
    public void clear()
    {
        memoryValues = null;
    }
}
