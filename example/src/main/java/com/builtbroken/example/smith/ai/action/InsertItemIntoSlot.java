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
import com.builtbroken.example.smith.data.World;
import com.builtbroken.example.smith.data.content.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 6/17/2021.
 */
@JsonTemplate(value = InsertItemIntoSlot.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
@NoArgsConstructor(access = AccessLevel.NONE)
public class InsertItemIntoSlot extends WorldAction<InsertItemIntoSlot>
{
    public static final String TEMPLATE_ID = "smith:slot.insert";

    @JsonObjectWiring(jsonFields = "item", objectType = JsonKeys.ITEM)
    protected Item item;

    @JsonMapping(keys = "count", type = ConverterRefs.INT, required = true)
    protected int count;

    @JsonMapping(keys = "exact", type = ConverterRefs.BOOLEAN)
    protected boolean exact = false;


    @JsonMapping(keys = "slot", type = ConverterRefs.INT, required = true)
    protected int slot;

    @JsonConstructor
    public static InsertItemIntoSlot build(@JsonMapping(keys = "name", type = "string", required = true) String name)
    {
        final InsertItemIntoSlot newObject = new InsertItemIntoSlot();
        newObject.name = name;
        return newObject;
    }

    @Nonnull
    @Override
    public ActionResult start(@Nonnull World world, @Nullable IMemoryContext memory)
    {
        final boolean result = AiHelper.actOnInventory(world, memory,
                (inventory) -> {

                    //Calculate how many items we can move
                    final int added = inventory.addItemToSlot(item, slot, count, true);
                    final int removed = world.getAiInventory().removeItemFromInventory(item, count, true);
                    if (exact && added < count)
                    {
                        return false;
                    }

                    //Calculate actual target move amount
                    final int targetMoveCount = Math.min(added, removed);

                    //Do move
                    world.getAiInventory().removeItemFromInventory(item, targetMoveCount, false);
                    inventory.addItemToSlot(item, slot, targetMoveCount, false);
                    return true;
                });
        return result ? ActionResult.STEP : ActionResult.END;
    }

    @Override
    public void copyInto(InsertItemIntoSlot choice)
    {
        choice.item = item;
        choice.count = count;
        choice.slot = slot;
        choice.exact = exact;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
