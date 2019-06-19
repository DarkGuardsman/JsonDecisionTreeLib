package com.builtbroken.decisiontree.api.context;

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
    IActorContext getContextOwner();
}
