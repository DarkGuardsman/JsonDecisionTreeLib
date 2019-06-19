package com.builtbroken.decisiontree.api.action;

import com.builtbroken.decisiontree.api.memory.IMemorySlot;

import java.util.function.Consumer;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IMemoryAction
{

    /**
     * Called to collect memory used by this action
     *
     * @param collector
     */
    void collectMemory(Consumer<IMemorySlot> collector);
}
