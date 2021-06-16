package com.builtbroken.example.smith.ai.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.data.Chest;
import com.builtbroken.example.smith.data.Furnace;
import com.builtbroken.example.smith.data.World;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-25.
 */
@JsonTemplate(type = "smith:add_fuel")
public class AddFuel extends WorldAction<AddFuel>
{
    @JsonMapping(keys = "count", type = ConverterRefs.INT)
    private int count = 1;

    public static AddFuel build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        AddFuel object = new AddFuel();
        object.name = name;
        return object;
    }

    @Override
    public String getJsonType()
    {
        return "smith:add_fuel";
    }

    @Override
    public ActionResult start(World world, IMemoryContext memory)
    {
        final Chest chest = world.getChest();
        final Furnace furnace = world.getFurnace();
        if (chest.getFuelItems() > 0)
        {
            //Pull item from storage
            final int amountStored = chest.getFuelItems();
            final int remove = Math.min(count, amountStored);
            chest.setFuelItems(amountStored - remove);

            //Place item into furnace
            furnace.addFuelItem(remove);

            //End
            return ActionResult.STEP;
        }
        return ActionResult.PASS;
    }
}
