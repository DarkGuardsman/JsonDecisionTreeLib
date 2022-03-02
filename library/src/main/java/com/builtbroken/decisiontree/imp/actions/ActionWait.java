package com.builtbroken.decisiontree.imp.actions;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.data.action.Action;
import com.builtbroken.decisiontree.imp.memory.slot.IntegerMemory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/** Simple AI action to wait
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
@JsonTemplate(value = ActionWait.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
public class ActionWait extends Action<ActionWait, IWorldContext, IMemoryContext> implements IMemoryAction
{
    public static final String TEMPLATE_ID = TreeTemplateTypes.ACTION + ".wait";

    //Memory slot that tracks the wait decrement in the AI's memory
    private IntegerMemory waitTime;

    //Amount of time to wait, set at start and decreased in update loop
    @JsonMapping(keys = "ticks", type = ConverterRefs.INT, required = true)
    private int waitInit = 1;

    private ActionWait()
    {
        //Empty to allow reflection building and private to prevent mistake use
    }

    public ActionWait build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        ActionWait wait = new ActionWait();
        wait.name = name;
        wait.waitTime = IntegerMemory.build(name + "_wait");
        return wait;
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
            //If at end then complete
            if (waitTime.get(memory) <= 0)
            {
                return ActionResult.COMPLETE;
            }

            //Decrement counter
            waitTime.getMemory(memory).sub(1);

            //Step to next tick, ends AI loop
            return ActionResult.STEP;
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

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
