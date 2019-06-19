package com.builtbroken.decisiontree.data.action;

import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class ActionPrintln extends Action
{
    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTION_PRINT_LINE;
    }

    @Override
    public boolean canTrigger(IActionContext triggerContext)
    {
        return true;
    }

    @Override
    public boolean canAction(IActionContext outputContext)
    {
        return true;
    }

    @Override
    public boolean trigger(IActionContext triggerContext, IActionContext outputContext)
    {
        System.out.println("Trigger: " + triggerContext);
        System.out.println("Output:")
        return true;
    }
}
