package com.builtbroken.decisiontree.api.context.world;

import com.builtbroken.decisiontree.api.context.IActionContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IWorldContext<P extends IWorldPointer, A extends IWorldAccessor> extends IActionContext
{

    /**
     * Gets a new accessor at the location
     *
     * @param pointer - location
     * @return accessor with context
     */
    A getAccessor(P pointer, IWorldAccessType type);
}
