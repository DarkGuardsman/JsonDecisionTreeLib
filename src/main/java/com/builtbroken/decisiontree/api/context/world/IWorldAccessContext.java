package com.builtbroken.decisiontree.api.context.world;

/**
 * Context information about an object contained inside of the world
 * <p>
 * Created by Dark(DarkGuardsman, Robert) on 2019-07-09.
 */
public interface IWorldAccessContext<T extends IWorldAccessType>
{

    T getType();
}
