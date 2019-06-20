package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class MemoryChoice extends Choice implements IMemoryAction
{
    @JsonObjectWiring(jsonFields = "memory", objectType = DTReferences.JSON_MEMORY)
    protected IMemorySlot memorySlot;

    @Override
    public void collectMemory(Consumer<IMemorySlot> collector)
    {
        collector.accept(memorySlot);
    }
}
