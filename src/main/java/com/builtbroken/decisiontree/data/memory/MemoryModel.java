package com.builtbroken.decisiontree.data.memory;

import com.builtbroken.decisiontree.api.memory.IMemoryModel;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;

import java.util.Set;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
public class MemoryModel implements IMemoryModel
{
    private IMemorySlot[] slots;

    public void build(Set<IMemorySlot> memoryList)
    {
        slots = new IMemorySlot[memoryList.size()];
        int index = 0;
        for(IMemorySlot slot : memoryList)
        {
            slots[index] = slot;
            slot.setSlotID(index++);
        }
    }

    @Override
    public IMemorySlot getSlot(int index)
    {
        if (index >= 0 && index < size())
        {
            return slots[index];
        }
        return null;
    }

    @Override
    public int size()
    {
        return slots != null ? slots.length : 0;
    }
}
