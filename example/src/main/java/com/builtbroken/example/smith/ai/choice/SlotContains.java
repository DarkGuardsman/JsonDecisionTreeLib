package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.example.smith.ai.AiHelper;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.game.World;
import com.builtbroken.example.game.content.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
@JsonTemplate(value = SlotContains.TEMPLATE_ID, registry = TreeTemplateTypes.CHOICE)
@NoArgsConstructor(access = AccessLevel.NONE)
public final class SlotContains extends WorldChoice<SlotContains> implements IMemoryAction
{
    public static final String TEMPLATE_ID = "smith:slot.contains";

    @JsonObjectWiring(jsonFields = "item", objectType = "game:content.item")
    protected Item item;

    //TODO make slot a named object and write a worldContext to convert from string to int
    //      Example: "furnace:input" -> 0
    @JsonMapping(keys = "slot", type = ConverterRefs.INT, required = true)
    protected int slot;

    @JsonMapping(keys = "count", type = ConverterRefs.INT)
    protected int count = 1;

    @JsonMapping(keys = "exact", type = ConverterRefs.BOOLEAN)
    protected boolean exact = false;

    @Override
    public void copyInto(SlotContains choice)
    {
        choice.item = item;
        choice.slot = slot;
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
        return AiHelper.actOnInventory(world, memory,
                (inventory) -> inventory.hasItemInSlot(slot, item, count, exact));
    }

    /**
     * Factory to build the object using the JSON content builder system
     *
     * @param name to give this logic check
     * @return new instance
     */
    @JsonConstructor
    public static SlotContains build(@JsonMapping(keys = "name", type = ConverterRefs.STRING, required = true) String name)
    {
        final SlotContains object = new SlotContains();
        object.name = name;
        return object;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
