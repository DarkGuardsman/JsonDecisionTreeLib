package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.ActionResult;
import com.builtbroken.decisiontree.api.IActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTION_PRINT_LINE)
public class ActionPrintln extends Action
{
    @JsonMapping(keys = "print", type = ConverterRefs.STRING)
    public String lineToPrint = null;

    @JsonMapping(keys = "context", type = ConverterRefs.STRING)
    public boolean printContext = true;

    @JsonConstructor()
    public static ActionPrintln build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        ActionPrintln println = new ActionPrintln();
        println.name = name;
        return println;
    }

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
    public ActionResult start(IActionContext triggerContext, IActionContext outputContext)
    {
        if (lineToPrint != null && lineToPrint.trim().isEmpty())
        {
            System.out.println(lineToPrint);
        }
        if (printContext)
        {
            System.out.println("Trigger: " + triggerContext);
            System.out.println("Output: " + outputContext);
        }
        return ActionResult.COMPLETE;
    }
}
