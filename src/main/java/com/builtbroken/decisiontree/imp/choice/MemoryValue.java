package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IActionChoice;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.MemorySlotObject;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(type = DTReferences.JSON_MEMORY_VALUE)
public class MemoryValue extends MemoryChoice<MemoryValue>
{
    @JsonMapping(keys = "value", type = ConverterRefs.OBJECT, required = true)
    private Object value;

    @JsonConstructor()
    public static MemoryValue build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        MemoryValue action = new MemoryValue();
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
    public void copyInto(MemoryValue choice)
    {
        super.copyInto(choice);
        choice.value = value;
    }
}
