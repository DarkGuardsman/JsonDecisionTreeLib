package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.context.IActionMemory;
import com.builtbroken.decisiontree.api.context.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public abstract class Action implements IAction, IJsonGeneratedObject
{
    @JsonMapping(keys = "priority", type = "int", required = false)
    public int priority = 0;

    protected String name;

    @JsonObjectWiring(jsonFields = "next", objectType = DTReferences.JSON_ACTION)
    public IAction next;

    @Override
    public IAction end(IWorldContext world, IActionMemory memory)
    {
        return next;
    }

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
