package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.data.content.Item;
import com.builtbroken.example.smith.data.inventory.Inventory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

/**
 * Created by Robin Seifert on 2021-06-17.
 */
@JsonTemplate(type = "smith:inventory.contains")
@NoArgsConstructor(access = AccessLevel.NONE)
public final class InventoryContains extends WorldChoice<InventoryContains> implements IMemoryAction
{
    @JsonObjectWiring(jsonFields = "item", objectType = "game:content.item")
    protected Item item;

    @JsonMapping(keys = "count", type = ConverterRefs.INT)
    protected int count = 1;

    @JsonMapping(keys = "exact", type = ConverterRefs.BOOLEAN)
    protected boolean exact = false;

    @Override
    public void copyInto(InventoryContains choice)
    {
        choice.item = item;
        choice.count = count;
        choice.exact = exact;
    }

    @Override
    public void collectMemory(Consumer<IMemorySlot> collector)
    {
        collector.accept(MemorySlots.MEMORY_FOCUSED_TILE);
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        final String tile = MemorySlots.MEMORY_FOCUSED_TILE.getValue(memory);
        final Inventory inventory = world.getInventory(tile);
        if (inventory != null)
        {
            return inventory.hasItem(item, count, exact);
        }
        return false;
    }

    /**
     * Factory to build the object using the JSON content builder system
     *
     * @param name to give this logic check
     * @return new instance
     */
    public static InventoryContains build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        final InventoryContains object = new InventoryContains();
        object.name = name;
        return object;
    }
}
