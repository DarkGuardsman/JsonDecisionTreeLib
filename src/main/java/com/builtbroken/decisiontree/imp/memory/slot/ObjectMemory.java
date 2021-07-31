package com.builtbroken.decisiontree.imp.memory.slot;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.TreeTemplateTypes;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.ObjectMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(value = ObjectMemory.TEMPLATE_ID, registry = TreeTemplateTypes.MEMORY)
public class ObjectMemory extends MemorySlot<ObjectMemory, Object, ObjectMemoryValue> implements IJsonGeneratedObject
{
    public static final String TEMPLATE_ID = TreeTemplateTypes.MEMORY + ".object";

    @JsonConstructor()
    public static MemorySlot build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        ObjectMemory action = new ObjectMemory();
        action.name = name;
        return action;
    }

    @Override
    public ObjectMemoryValue newValue(@Nonnull IMemoryContext memory, @Nullable Object oldValue)
    {
        return new ObjectMemoryValue().setSlot(this).setValue(oldValue);
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }
}
