package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IActionChoice;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class Choice implements IJsonGeneratedObject, IActionChoice
{
    protected String name;

    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTION_CHOICE;
    }

    @Override
    public String getJsonUniqueID()
    {
        return name;
    }
}
