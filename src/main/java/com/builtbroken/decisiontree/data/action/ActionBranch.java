package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.action.IActionChoice;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(value = ActionBranch.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
public class ActionBranch implements IAction<ActionBranch, IWorldContext, IMemoryContext>, IJsonGeneratedObject
{
    public static final String TEMPLATE_ID = TreeTemplateTypes.ACTION + ".branch";
    private String name;

    @JsonMapping(keys = "priority", type = ConverterRefs.INT)
    public int priority = 0;

    @JsonObjectWiring(jsonFields = "choice", objectType = TreeTemplateTypes.CHOICE, required = true)
    public IActionChoice choice;

    @JsonObjectWiring(jsonFields = "true", objectType = TreeTemplateTypes.ACTION, required = true)
    public IAction trueAction;

    @JsonObjectWiring(jsonFields = "false", objectType = TreeTemplateTypes.ACTION, required = true)
    public IAction falseAction;

    @JsonConstructor()
    public static ActionBranch build(@JsonMapping(keys = "name", type = ConverterRefs.STRING, required = true) String name)
    {
        ActionBranch action = new ActionBranch();
        action.name = name;
        return action;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }

    @Override
    public String getJsonRegistryID()
    {
        return TreeTemplateTypes.ACTION;
    }

    @Override
    public String getJsonUniqueID()
    {
        return name;
    }

    @Override
    public int getPriority()
    {
        return priority;
    }

    @Override
    public boolean isCompatible(IWorldContext worldContext, IMemoryContext memoryContext)
    {
        return false;
    }

    @Override
    public ActionResult start(IWorldContext world, IMemoryContext memory)
    {
        return ActionResult.COMPLETE;
    }

    @Override
    public IAction end(IWorldContext world, IMemoryContext memory)
    {
        if (choice.isTrue(world, memory))
        {
            return trueAction;
        }
        return falseAction;
    }

    @Override
    public ActionBranch copy()
    {
        ActionBranch branch = new ActionBranch();
        branch.name = name;
        branch.priority = priority;
        branch.choice = (IActionChoice) choice.copy();
        branch.trueAction = (IAction) trueAction.copy();
        branch.falseAction = (IAction) falseAction.copy();
        return branch;
    }
}
