package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public abstract class Action<A extends Action> implements IAction, IJsonGeneratedObject
{

    @JsonMapping(keys = "priority", type = "int", required = false)
    public int priority = 0;

    protected String name;

    @JsonObjectWiring(jsonFields = "next", objectType = DTReferences.JSON_ACTION)
    public IAction next;

    @Override
    public IAction end(IWorldContext world, IMemoryContext memory)
    {
        return next;
    }

    @Override
    public IAction copy()
    {
        try
        {
            final A action = (A) getClass().newInstance();
            copyInto(action);
            return action;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    protected void copyInto(A action)
    {
        action.priority = priority;
        action.next = next != null ? next.copy() : null;
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
