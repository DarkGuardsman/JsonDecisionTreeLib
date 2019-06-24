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
    @JsonMapping(keys = "input_count", type = ConverterRefs.INT, required = true)
    private int inputCount;

    //TODO find way to make this generic using some type of data accessor or adapter
    //      This way we only have 1 choice class for a range of usage
    //      Idea would be to place some data structure into the world context that we
    //      can queue for information about the focus of the AI's attention. Then
    //      we can just do if(accessor.getValue(key) >= expectedValue)

    @Override
    public void copyInto(HasInput choice)
    {
        choice.inputCount = inputCount;
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        return world.furnace.inputCount >= inputCount;
    }
}
