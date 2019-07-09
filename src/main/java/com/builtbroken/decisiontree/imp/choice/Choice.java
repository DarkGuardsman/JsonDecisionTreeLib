package com.builtbroken.decisiontree.imp.choice;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IActionChoice;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public abstract class Choice<C extends Choice, W extends IWorldContext, M extends IMemoryContext> implements IJsonGeneratedObject, IActionChoice<C, W, M>
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

    @Override
    public C copy()
    {
        try
        {
            C choice = (C) getClass().newInstance();
            choice.name = name;
            copyInto(choice);
            return choice;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public abstract void copyInto(C choice);
}
