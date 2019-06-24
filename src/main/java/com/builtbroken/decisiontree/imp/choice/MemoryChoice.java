package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class MemoryChoice<C extends MemoryChoice> extends Choice<C, IWorldContext, IMemoryContext> implements IMemoryAction
{

    @JsonObjectWiring(jsonFields = "memory", objectType = DTReferences.JSON_MEMORY)
    protected IMemorySlot memorySlot;

    @Override
    public void collectMemory(Consumer<IMemorySlot> collector)
    {
        collector.accept(memorySlot);
    }

    @Override
    public boolean isCompatible(IWorldContext worldContext, IMemoryContext memoryContext)
    {
        return memoryContext != null;
    }

    @Override
    public void copyInto(C choice)
    {
        choice.memorySlot = memorySlot;
    }
}
