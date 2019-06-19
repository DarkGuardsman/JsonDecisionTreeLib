package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.ActionResult;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActionContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTION_SET)
public class ActionSet extends Action implements IJsonGeneratedObject
{

    @JsonMapping(keys = "action", type = ConverterRefs.LIST)
    private final List<IAction> actions = new ArrayList();

    @JsonMapping(keys = "run_all", type = ConverterRefs.BOOLEAN)
    private boolean runAll = false;

    @JsonConstructor()
    public static ActionSet build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        ActionSet action = new ActionSet();
        action.name = name;
        return action;
    }

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
    public ActionResult start(IActionContext triggerContext, IActionContext outputContext)
    {
        for (IAction action : actions)
        {
            ActionResult result = action.start(triggerContext, outputContext);
            if (!runAll && result != ActionResult.PASS)
            {
                return result;
            }
            else if (result != ActionResult.PASS && result != ActionResult.COMPLETE)
            {
                return ActionResult.ERROR; //Run all only works if all complete first tick, as we can only run 1 task at a time
            }
        }
        return ActionResult.COMPLETE;
    }

    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTION_SET;
    }
}
