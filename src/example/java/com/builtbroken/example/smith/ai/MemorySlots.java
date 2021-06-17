package com.builtbroken.example.smith.ai;

import com.builtbroken.decisiontree.imp.memory.slot.StringMemory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public final class MemorySlots
{
    /** What tile is the AI looking at currently */
    public static final StringMemory MEMORY_FOCUSED_TILE = StringMemory.build("target_tile");
}
