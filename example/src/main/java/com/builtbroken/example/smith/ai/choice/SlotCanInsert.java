package com.builtbroken.example.smith.ai.choice;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.ai.AiHelper;
import com.builtbroken.example.smith.data.JsonKeys;
import com.builtbroken.example.game.World;
import com.builtbroken.example.game.content.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
@JsonTemplate(value = SlotCanInsert.TEMPLATE_ID, registry = TreeTemplateTypes.CHOICE)
@NoArgsConstructor(access = AccessLevel.NONE)
public class SlotCanInsert extends WorldChoice<SlotCanInsert>
{
    public static final String TEMPLATE_ID = "smith:slot.insert.check";

    @JsonObjectWiring(jsonFields = "item", objectType = JsonKeys.ITEM, required = true)
    protected Item item;

    @JsonMapping(keys = "count", type = ConverterRefs.INT)
    protected int count = 1;

    @JsonConstructor
    public static SlotCanInsert build(@JsonMapping(keys = "name", type = "string", required = true) String name)
    {
        final SlotCanInsert newObject = new SlotCanInsert();
        newObject.name = name;
        return newObject;
    }

    @Override
    public boolean isTrue(World world, IMemoryContext memory)
    {
        return AiHelper.actOnInventory(world, memory,
                (inventory) -> world.getAiInventory().moveAllItemsToInventory(inventory, item, count, true));
    }

    @Override
    public void copyInto(SlotCanInsert choice)
    {
        choice.item = item;
        choice.count = count;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
