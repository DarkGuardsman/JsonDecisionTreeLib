package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
@JsonTemplate(type = "smith:has_input")
public class HasOutput extends WorldChoice<HasOutput>
{
    @JsonMapping(keys = "count", type = ConverterRefs.INT, required = true)
    private int count;

    public static HasOutput build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        HasOutput hasOutput = new HasOutput();
        hasOutput.name = name;
        return hasOutput;
    }

    @Override
    public void copyInto(HasOutput choice)
    {
        choice.count = count;
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        return world.furnace.outputCount >= count;
    }
}
