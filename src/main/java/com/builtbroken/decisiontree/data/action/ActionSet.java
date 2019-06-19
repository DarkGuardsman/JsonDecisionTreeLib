package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActionContext;
import com.builtbroken.decisiontree.data.action.Action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActionSet extends Action implements IJsonGeneratedObject
{
    private final List<IAction> actions = new ArrayList();

    public void sort(Comparator<IAction> comparator)
    {
        actions.sort(comparator);
    }

    @Override
    public boolean canTrigger(IActionContext triggerContext)
    {
        return actions.stream().anyMatch(a -> a.canTrigger(triggerContext));
    }

    @Override
    public boolean canAction(IActionContext outputContext)
    {
        return actions.stream().anyMatch(a -> a.canAction(outputContext));
    }

    @Override
    public boolean trigger(IActionContext triggerContext, IActionContext outputContext)
    {
        for (IAction action : actions)
        {
            if (action.trigger(triggerContext, outputContext))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTION_SET;
    }
}
