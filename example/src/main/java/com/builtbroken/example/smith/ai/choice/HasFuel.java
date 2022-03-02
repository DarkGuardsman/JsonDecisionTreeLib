package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@JsonTemplate(value = HasFuel.TEMPLATE_ID, registry = TreeTemplateTypes.CHOICE)
public class HasFuel extends WorldChoice<HasFuel>
{
    public static final String TEMPLATE_ID = "smith:has_fuel";

    @JsonMapping(keys = "fuel_level", type = ConverterRefs.INT, required = true)
    private int fuelLevel = 1;

    @JsonConstructor
    public static HasFuel build(@JsonMapping(keys = "name", type = ConverterRefs.STRING, required = true) String name)
    {
        final HasFuel object = new HasFuel();
        object.name = name;
        return object;
    }

    @Override
    public void copyInto(HasFuel choice)
    {
        choice.fuelLevel = fuelLevel;
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        return world.getFurnace().getFuelTime() >= fuelLevel;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
