package com.builtbroken.decisiontree.data;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.IAction;
import com.builtbroken.decisiontree.api.action.IActionTree;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.memory.IMemoryModel;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.data.memory.MemoryModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
@JsonTemplate(TreeTemplateTypes.TREE)
@NoArgsConstructor(access = AccessLevel.NONE)
public class ActionTree implements IActionTree, IJsonGeneratedObject
{
    private String name;

    @JsonObjectWiring(jsonFields = "start", objectType = TreeTemplateTypes.ACTION, required = true)
    private IAction treeStart;

    private MemoryModel memoryModel;

    @JsonConstructor
    public static ActionTree build(@JsonMapping(keys = "name", type = "string", required = true) String name)
    {
        final ActionTree actionTree = new ActionTree();
        actionTree.name = name;
        return actionTree;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TreeTemplateTypes.TREE;
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
        final ActionTree tree = build(name);
        if (tree.treeStart != null)
        {
            tree.treeStart = (IAction) treeStart.copy();
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
                ((IMemoryAction) action).collectMemory(memoryList::add);
            }
        });

        //TODO validate two memory objects don't share the same name

        //Cache
        memoryModel = new MemoryModel();
        memoryModel.build(memoryList);
    }

    @Override
    public IMemoryModel getMemoryModel()
    {
        return memoryModel;
    }
}
