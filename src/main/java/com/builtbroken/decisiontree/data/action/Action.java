package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.decisiontree.api.IAction;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public abstract class Action implements IAction, IJsonGeneratedObject
{
    @JsonMapping(keys = "priority", type = "int", required = false)
    public int priority = 0;

    protected String name;

    @Override
    public int getPriority()
    {
        return priority;
    }

    public String getJsonUniqueID()
    {
        return name;
    }
}
