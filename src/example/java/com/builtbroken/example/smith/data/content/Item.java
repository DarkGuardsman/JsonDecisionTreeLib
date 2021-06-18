package com.builtbroken.example.smith.data.content;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
@AllArgsConstructor
public class Item
{
    /** Unique ID of the item */
    private final int itemID;
    /** Max inventory stack of the item */
    private final int maxStack;
    /** User readable name */
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }
}
