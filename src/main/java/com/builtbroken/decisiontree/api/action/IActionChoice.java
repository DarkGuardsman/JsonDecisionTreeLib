package com.builtbroken.decisiontree.api.action;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-20.
 */
public interface IActionChoice<I extends IActionChoice, W extends IWorldContext, M extends IMemoryContext> extends IActionComponent<I>
{
    boolean isTrue(W world, M memory);
}
