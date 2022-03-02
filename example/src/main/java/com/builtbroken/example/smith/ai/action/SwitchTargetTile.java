package com.builtbroken.example.smith.ai.action;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.action.ActionResult;
import com.builtbroken.decisiontree.api.action.IMemoryAction;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.StringMemoryValue;
import com.builtbroken.example.smith.ai.MemorySlots;
import com.builtbroken.example.game.World;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@JsonTemplate(value = SwitchTargetTile.TEMPLATE_ID, registry = TreeTemplateTypes.ACTION)
@NoArgsConstructor(access = AccessLevel.NONE)
public class SwitchTargetTile extends WorldAction<SwitchTargetTile> implements IMemoryAction
{
    public static final String TEMPLATE_ID = "smith:memory.update.target.tile";

    @JsonMapping(keys = "tile", type = ConverterRefs.STRING, required = true)
    protected String tile;

    @JsonConstructor
    public static SwitchTargetTile build(@JsonMapping(keys = "name", type = "string", required = true) String name)
    {
        final SwitchTargetTile newObject = new SwitchTargetTile();
        newObject.name = name;
        return newObject;
    }

    @Nonnull
    @Override
    public ActionResult start(@Nonnull World world, @Nullable IMemoryContext memory)
    {
        if(memory != null)
        {
            final StringMemoryValue value = memory.getValueStored(MemorySlots.MEMORY_FOCUSED_TILE);
            if (value != null)
            {
                value.setValue(tile);
                return ActionResult.COMPLETE;
            }
        }
        return ActionResult.ERROR;
    }

    @Override
    public void collectMemory(Consumer<IMemorySlot> collector)
    {
        collector.accept(MemorySlots.MEMORY_FOCUSED_TILE);
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
