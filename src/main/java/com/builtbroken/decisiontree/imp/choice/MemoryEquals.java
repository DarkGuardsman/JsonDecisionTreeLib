package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(type = DTReferences.JSON_MEMORY_VALUE)
public class MemoryEquals extends MemoryChoice<MemoryEquals>
{
    @JsonMapping(keys = "value", type = ConverterRefs.OBJECT, required = true)
    private Object value;

    @JsonConstructor()
    public static MemoryEquals build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        MemoryEquals action = new MemoryEquals();
        action.name = name;
        return action;
    }

    @Override
    public boolean isTrue(IWorldContext world, IMemoryContext memory)
    {
        if (memory != null)
        {
            final Object valueStored = memorySlot.getValue(memory);
            return value.equals(valueStored);
        }
        return false;
    }

    @Override
    public void copyInto(MemoryEquals choice)
    {
        super.copyInto(choice);
        choice.value = value;
    }
}
