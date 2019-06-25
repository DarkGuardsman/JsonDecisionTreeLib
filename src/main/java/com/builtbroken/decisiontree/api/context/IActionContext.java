package com.builtbroken.decisiontree.api.context;

import javax.annotation.Nonnull;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IActionContext
{

    /**
     * Actor that owns the context. Each
     * actor should have a unique context
     * instance for use even if it is
     * just a wrapper to other systems.
     *
     * @return actor context
     */
    @Nonnull
    IActorContext getContextOwner();

    /**
     * Called only once during setup to load
     * the actor into the world context.
     *
     * @param context
     */
    void init(@Nonnull IActorContext context);
}
