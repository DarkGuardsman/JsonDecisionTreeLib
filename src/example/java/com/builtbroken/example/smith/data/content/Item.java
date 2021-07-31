package com.builtbroken.example.smith.data.content;

import com.builtbroken.builder.converter.ConverterRefs;
import com.builtbroken.builder.data.IJsonGeneratedObject;
import com.builtbroken.builder.mapper.anno.JsonConstructor;
import com.builtbroken.builder.mapper.anno.JsonMapping;
import com.builtbroken.builder.mapper.anno.JsonTemplate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
@JsonTemplate(Item.TEMPLATE_ID)
@NoArgsConstructor(access = AccessLevel.NONE)
public class Item implements IJsonGeneratedObject
{
    public static final String TEMPLATE_ID = "game:item";

    /** Max inventory stack of the item */
    @Setter(value = AccessLevel.NONE)
    @JsonMapping(keys = "stack_size", type = ConverterRefs.INT)
    private int maxStack;

    /** User readable name */
    @Setter(value = AccessLevel.NONE)
    @JsonMapping(keys = "display_name", type = ConverterRefs.STRING)
    private String displayName;

    @Setter(value = AccessLevel.NONE)
    private String uniqueID;

    @JsonConstructor
    public static Item build(@JsonMapping(keys = "id", type = ConverterRefs.STRING, required = true) String id)
    {
        final Item object = new Item();
        object.uniqueID = id;
        return object;
    }

    @Override
    public String toString() {
        return uniqueID;
    }

    @Override
    public String getJsonTemplateID()
    {
        return TEMPLATE_ID;
    }

    @Override
    public String getJsonUniqueID()
    {
        return uniqueID;
    }
}
