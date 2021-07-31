package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(value = MemoryHas.TEMPLATE_ID, registry = TreeTemplateTypes.CHOICE)
public class MemoryHas extends MemoryChoice<MemoryHas>
{
    public static final String TEMPLATE_ID = TreeTemplateTypes.CHOICE + ".memory.has";

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

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
