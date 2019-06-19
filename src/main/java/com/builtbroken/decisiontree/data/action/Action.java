package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActionContext;
import com.builtbroken.decisiontree.data.context.ActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public abstract class Action implements IAction, IJsonGeneratedObject
{
    @JsonMapping(keys = "priority", type = "int", required = false)
    public int priority = 0;

    protected String name;

    public String getJsonUniqueID()
    {
        return name;
    }
}
