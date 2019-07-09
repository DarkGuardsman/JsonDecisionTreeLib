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
@JsonTemplate(type = DTReferences.JSON_MEMORY_HAS)
public class MemoryHas extends MemoryChoice<MemoryHas>
{
    @JsonConstructor()
    public static MemoryHas build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        MemoryHas action = new MemoryHas();
        action.name = name;
        return action;
    }

    @Override
    public boolean isTrue(IWorldContext world, IMemoryContext memory)
    {
        return memorySlot != null && memorySlot.hasValue(memory);
    }
}
