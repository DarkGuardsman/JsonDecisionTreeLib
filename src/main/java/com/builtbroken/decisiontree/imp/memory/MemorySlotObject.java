package com.builtbroken.decisiontree.imp.memory;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.context.IMemoryContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(type = DTReferences.JSON_MEMORY)
public class MemorySlotObject extends MemorySlot<Object>
{
    @JsonConstructor()
    public static MemorySlot build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        MemorySlotObject action = new MemorySlotObject();
        action.name = name;
        return action;
    }

    @Override
    public Object getValue(IMemoryContext memory)
    {
        return memory.getValueStored(getSlotID());
    }

    @Override
    public boolean hasValue(IMemoryContext memory)
    {
        return memory.getValueStored(getSlotID()) != null;
    }
}
