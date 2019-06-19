package com.builtbroken.decisiontree.data.context;

import com.builtbroken.decisiontree.api.context.IActorContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActorContext implements IActorContext
{
    private final String uniqueID;

    public String name;
    public int instanceID;

    public ActorContext(String uniqueID)
    {
        this.uniqueID = uniqueID;
    }

    @Override
    public String getUniqueName()
    {
        return uniqueID;
    }

    @Override
    public String getDisplayName()
    {
        if(name == null)
        {
            return uniqueID;
        }
        return name;
    }

    @Override
    public int getInstanceID()
    {
        return instanceID;
    }
}
