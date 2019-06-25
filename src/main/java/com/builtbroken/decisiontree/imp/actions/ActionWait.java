package com.builtbroken.decisiontree.imp.actions;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.data.action.Action;
import com.builtbroken.decisiontree.imp.memory.slot.IntegerMemory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
@JsonTemplate(type = DTReferences.JSON_ACTION_WAIT)
public class ActionWait extends Action<ActionWait, IWorldContext, IMemoryContext> implements IMemoryAction
{

    private final IntegerMemory waitTime = new IntegerMemory();

    @JsonMapping(keys = "ticks", type = ConverterRefs.INT, required = true)
    private int waitInit = 1;

    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTION_WAIT;
    }

    @Nonnull
    @Override
    public ActionResult start(@Nonnull IWorldContext world, @Nullable IMemoryContext memory)
    {
        if (memory != null)
        {
            waitTime.set(memory, waitInit);
            return ActionResult.RUNNING;
        }
        return ActionResult.ERROR;
    }

    @Nonnull
    @Override
    public ActionResult update(@Nonnull IWorldContext world, @Nullable IMemoryContext memory, int tick, float delta)
    {
        if (memory != null && waitTime.hasValue(memory))
        {
            if (waitTime.get(memory) == 0)
            {
                return ActionResult.COMPLETE;
            }
            waitTime.getMemory(memory).sub(1);
            return ActionResult.RUNNING;
        }
        return ActionResult.ERROR;
    }

    @Override
    public boolean isCompatible(@Nonnull IWorldContext worldContext, @Nullable IMemoryContext memoryContext)
    {
        return memoryContext != null;
    }

    @Override
    public void collectMemory(Consumer<IMemorySlot> collector)
    {
        collector.accept(waitTime);
    }
}
