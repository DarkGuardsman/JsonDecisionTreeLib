package com.builtbroken.decisiontree.imp.actions;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;
import com.builtbroken.decisiontree.data.action.Action;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTION_PRINT_LINE)
public class ActionPrintln extends Action<ActionPrintln>
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
    public ActionResult start(IWorldContext world, IMemoryContext memory)
    {
        if (lineToPrint != null && lineToPrint.trim().isEmpty())
        {
            System.out.println(lineToPrint);
        }
        if (printContext)
        {
            System.out.println("World: " + world);
            System.out.println("Memory: " + memory);
        }
        return ActionResult.COMPLETE;
    }

    @Override
    protected void copyInto(ActionPrintln action)
    {
        super.copyInto(action);
        action.lineToPrint = lineToPrint;
        action.printContext = printContext;
    }
}
