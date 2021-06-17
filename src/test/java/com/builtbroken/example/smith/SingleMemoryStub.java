package com.builtbroken.example.smith;

import com.builtbroken.decisiontree.api.context.IActorContext;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemoryModel;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
@AllArgsConstructor
public class SingleMemoryStub implements IMemoryContext
{
    private IMemorySlot slot;
    private IMemoryValue value;

    @Nonnull
    @Override
    public IActorContext getContextOwner()
    {
        return null;
    }

    @Override
    public void init(@Nonnull IActorContext context)
    {

    }

    @Nullable
    @Override
    public Object getValueStored(int index)
    {
        return null;
    }

    @Nullable
    @Override
    public <S extends IMemorySlot<S, O, M>, O, M extends IMemoryValue<O, M>> M getValueStored(IMemorySlot<S, O, M> slot)
    {
        if (slot == this.slot)
        {
            return (M) value;
        }
        return null;
    }

    @Override
    public void mapModel(@Nonnull IMemoryModel model)
    {

    }

    @Override
    public void clear()
    {

    }
}
