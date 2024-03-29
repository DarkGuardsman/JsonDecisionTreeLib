package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class MemorySlot<S extends MemorySlot<S, O, M>, O extends Object, M extends IMemoryValue<O, M>> implements IMemorySlot<S, O, M>, IJsonGeneratedObject
{
    protected String name;
    private int id;

    @Override
    public String getJsonTemplateID()
    {
        return TreeTemplateTypes.MEMORY;
    }

    @Override
    public String getJsonUniqueID()
    {
        return name;
    }

    @Override
    public String getUniqueName()
    {
        return name;
    }

    @Override
    public String getDisplayValue(IMemoryContext memory)
    {
        return TreeTemplateTypes.MEMORY;
    }

    @Override
    public int getSlotID()
    {
        return id;
    }

    @Override
    public void setSlotID(int index)
    {
        this.id = index;
    }

    @Nonnull
    @Override
    public S copy()
    {
        try
        {
            S copy = (S) getClass().newInstance();
            return copy;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
