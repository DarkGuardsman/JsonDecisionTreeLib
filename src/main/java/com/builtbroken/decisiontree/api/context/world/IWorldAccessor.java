package com.builtbroken.decisiontree.api.context.world;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-07-09.
 */
public interface IWorldAccessor<T extends IWorldAccessType>
{
    IWorldAccessContext<T> getContext();

    IWorldPointer getPointer();
}
