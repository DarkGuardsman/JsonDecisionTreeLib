package com.builtbroken.decisiontree.api.action;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
@FunctionalInterface
public interface IActionChoice
{
    boolean isTrue(IWorldContext world, IMemoryContext memory);
}
