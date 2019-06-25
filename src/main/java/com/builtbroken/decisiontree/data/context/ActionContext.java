package com.builtbroken.decisiontree.data.context;

import com.builtbroken.decisiontree.api.context.IActionContext;
import com.builtbroken.decisiontree.api.context.IActorContext;

/**
 * Used to give an action information about the context by which the action was triggered in
 * or where the action will be acting on.
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActionContext implements IActionContext
{

    private IActorContext actorContext;

    @Override
    public IActorContext getContextOwner()
    {
        return actorContext;
    }

    @Override
    public void init(IActorContext context)
    {
        if (actorContext != null)
        {
            this.actorContext = context;
        }
        else
        {
            throw new UnsupportedOperationException("Actor context can only be set once, " + this);
        }
    }

    @Override
    public String toString()
    {
        return getClass() + "[" + actorContext + "]@" + hashCode();
    }
}
