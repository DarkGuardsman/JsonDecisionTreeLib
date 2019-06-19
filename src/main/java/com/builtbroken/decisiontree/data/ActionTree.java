package com.builtbroken.decisiontree.data;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.action.IActionTree;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(type = DTReferences.JSON_ACTOR)
public class ActionTree implements IActionTree, IJsonGeneratedObject
{

    private String name;

    @JsonObjectWiring(jsonFields = "starts", objectType = DTReferences.JSON_ACTION_SET)
    private IAction treeStart;

    private ImmutableMap<String, Integer> memorySlots;

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

    @Override
    public IActionTree copy()
    {
        ActionTree tree = build(name);
        if (tree.treeStart != null)
        {
            tree.treeStart = treeStart.copy();
        }
        return tree;
    }

    @Override
    public void bakeTree()
    {
        final Set<IMemorySlot> memoryList = new HashSet();

        //Collect all memory
        collectActions((action) ->
        {
            if (action instanceof IMemoryAction)
            {
                ((IMemoryAction) action).collectMemory(mem ->
                {
                    memoryList.add(mem);
                });
            }
        });

        //Map IDs
        final HashMap<String, Integer> memorySlots = new HashMap();
        int index = 0;
        for (IMemorySlot slot : memoryList)
        {
            final String name = slot.getUniqueName();
            if (!memorySlots.containsKey(name))
            {
                slot.setSlotID(index);
                memorySlots.put(name, index);
            }
        }

        //Cache
        ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
        builder.putAll(memorySlots);
        this.memorySlots = builder.build();
    }

    @Override
    public Map<String, Integer> getMemorySlots()
    {
        return memorySlots;
    }
}
