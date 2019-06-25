package com.builtbroken.decisiontree.imp.memory.slot;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import com.builtbroken.decisiontree.DTReferences;
import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.memory.IMemoryValue;
import com.builtbroken.decisiontree.imp.memory.MemorySlot;
import com.builtbroken.decisiontree.imp.memory.value.ObjectMemoryValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@JsonTemplate(type = DTReferences.JSON_MEMORY)
public class ObjectMemory extends MemorySlot<Object>
{

    @JsonConstructor()
    public static MemorySlot build(@JsonMapping(keys = "name", type = ConverterRefs.STRING) String name)
    {
        ObjectMemory action = new ObjectMemory();
        action.name = name;
        return action;
    }

    @Override
    public IMemoryValue<Object> newValue(@Nonnull IMemoryContext memory, @Nullable Object oldValue)
    {
        return new ObjectMemoryValue(this).setValue(oldValue);
    }
}
