package com.builtbroken.example.smith;

import com.builtbroken.example.smith.data.content.Item;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Deprecated
public class Items
{
    public static final Item fuel = ObjectBuilder.buildObject(Item.class,
            "name", "item:fuel",
            "maxStack", 3
    );
    public static final Item ore = ObjectBuilder.buildObject(Item.class,
            "name", "item:ore",
            "maxStack", 3
    );
    public static final Item ingots = ObjectBuilder.buildObject(Item.class,
            "name", "item:ingots",
            "maxStack", 5
    );
}
