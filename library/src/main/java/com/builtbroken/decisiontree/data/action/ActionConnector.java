package com.builtbroken.decisiontree.data.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.world.IWorldContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Exists to wrapper a reusable AI action to allow connection without
 * forcing an action to be rewritten dozens of times.
 *
 * Created by Robin Seifert on 6/20/2021.
 */
@JsonTemplate(value = ActionConnector.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
public class ActionConnector extends Action<ActionConnector, IWorldContext, IMemoryContext>
{
    public static final String TEMPLATE_ID = TreeTemplateTypes.ACTION + ".connection";

    @JsonObjectWiring(jsonFields = "invoke", objectType = TreeTemplateTypes.ACTION, required = true)
    private IAction invokedAction;

    @JsonConstructor()
    public static ActionConnector build(@JsonMapping(keys = "name", type = ConverterRefs.STRING, required = true) String name)
    {
        ActionConnector action = new ActionConnector();
        action.name = name;
        return action;
    }

    @Nonnull
    @Override
    public ActionResult start(@Nonnull IWorldContext world, @Nullable IMemoryContext memory)
    {
        return invokedAction.start(world, memory); //TODO check if action has a next()
    }

    @Override
    public boolean isCompatible(@Nonnull IWorldContext worldContext, @Nullable IMemoryContext memoryContext)
    {
        return invokedAction.isCompatible(worldContext, memoryContext);
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
