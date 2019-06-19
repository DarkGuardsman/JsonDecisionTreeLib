package com.builtbroken.decisiontree.data;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActionTree;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTOR)
public class ActionTree implements IActionTree, IJsonGeneratedObject
{
    private String name;

    @JsonObjectWiring(jsonFields = "starts", objectType = DTReferences.JSON_ACTION_SET)
    private IAction treeStart;

    @JsonConstructor
    public static ActionTree build(@JsonMapping(keys = "name", type = "string") String name)
    {
        ActionTree actionTree = new ActionTree();
        actionTree.name = name;
        return actionTree;
    }

    @Override
    public String getJsonType()
    {
        return DTReferences.JSON_ACTOR;
    }

    @Override
    public String getJsonUniqueID()
    {
        return name;
    }

    @Override
    public IAction getEntryPoint()
    {
        return treeStart;
    }
}
