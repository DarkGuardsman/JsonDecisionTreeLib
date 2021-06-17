package com.builtbroken.decisiontree.loader;

import com.builtbroken.builder.pipe.Pipe;
import com.builtbroken.builder.pipe.nodes.NodeActionResult;
import com.builtbroken.builder.pipe.nodes.NodeType;
import com.builtbroken.builder.pipe.nodes.prefab.PipeNode;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.data.action.ActionSet;
import com.google.gson.JsonElement;

import java.util.Comparator;
import java.util.Queue;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class PipeSortActions extends PipeNode<ActionSet>
{
    protected PipeSortActions(Pipe pipe)
    {
        super(pipe, NodeType.POST, DTReferences.PIPE_SORT);
    }

    @Override
    public void receive(JsonElement data, ActionSet currentObject, Queue<Object> objectsOut)
    {
        currentObject.sort(Comparator.comparingInt(IAction::getPriority));
    }

    @Override
    public NodeActionResult shouldReceive(JsonElement data, Object currentObject)
    {
        return currentObject instanceof ActionSet ? NodeActionResult.CONTINUE : NodeActionResult.SKIP;
    }
}
