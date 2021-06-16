package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@JsonTemplate(type = "smith:has_input")
public class HasInput extends WorldChoice<HasInput>
{
    @JsonMapping(keys = "count", type = ConverterRefs.INT, required = true)
    private int inputCount;

    public static HasInput build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        HasInput object = new HasInput();
        object.name = name;
        return object;
    }

    @Override
    public void copyInto(HasInput choice)
    {
        choice.inputCount = inputCount;
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        return world.getFurnace().getInputCount() >= inputCount;
    }
}
