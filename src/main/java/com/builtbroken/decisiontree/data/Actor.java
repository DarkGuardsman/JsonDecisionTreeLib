package com.builtbroken.decisiontree.data;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.IAction;
import com.builtbroken.decisiontree.api.IActor;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTOR)
public class Actor implements IActor, IJsonGeneratedObject
{
    private String name;

    private IAction currentAction;

    @JsonObjectWiring(jsonFields = "starts", objectType = DTReferences.JSON_ACTION_SET)
    private IAction treeStart;

    @JsonConstructor
    public static Actor build(@JsonMapping(keys = "name", type = "string") String name)
    {
        Actor actor = new Actor();
        actor.name = name;
        return actor;
    }

    public void reset()
    {
        currentAction = treeStart;
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
}
