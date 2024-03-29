package com.builtbroken.example.smith.ai.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonObjectWiring;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.example.smith.ai.AiHelper;
import com.builtbroken.example.smith.data.JsonKeys;
import com.builtbroken.example.game.World;
import com.builtbroken.example.game.content.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
@JsonTemplate(value = TakeItemFromSlot.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
@NoArgsConstructor(access = AccessLevel.NONE)
public class TakeItemFromSlot extends WorldAction<TakeItemFromSlot>
{
    public static final String TEMPLATE_ID = "smith:slot.remove.item";

    @JsonObjectWiring(jsonFields = "item", objectType = JsonKeys.ITEM)
    protected Item item;

    @JsonMapping(keys = "count", type = ConverterRefs.INT, required = true)
    protected int removeCount;

    @JsonMapping(keys = "slot", type = ConverterRefs.INT, required = true)
    protected int slot;

    @JsonMapping(keys = "exact", type = ConverterRefs.BOOLEAN)
    protected boolean exact = false;

    @JsonConstructor
    public static TakeItemFromSlot build(@JsonMapping(keys = "name", type = "string", required = true) String name)
    {
        final TakeItemFromSlot newObject = new TakeItemFromSlot();
        newObject.name = name;
        return newObject;
    }

    @Nonnull
    @Override
    public ActionResult start(@Nonnull World world, @Nullable IMemoryContext memory)
    {
        final boolean result = AiHelper.actOnInventory(world, memory,
                (inventory) -> {
                    if(inventory.hasItemInSlot(slot, item, removeCount, exact)) {
                        return inventory.moveItemToInventory(world.getAiInventory(), slot, removeCount, false);
                    }
                    return false;
                });
        return result ? ActionResult.STEP : ActionResult.END;
    }

    @Override
    public void copyInto(TakeItemFromSlot choice)
    {
        choice.item = item;
        choice.removeCount = removeCount;
        choice.slot = slot;
        choice.exact = exact;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
