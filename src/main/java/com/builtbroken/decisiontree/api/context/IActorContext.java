package com.builtbroken.decisiontree.api.context;

import javax.annotation.Nonnull;

/**
 * Context information about the Ai running actions
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public interface IActorContext
{

    /**
     * Unique name of the AI for save/load
     * as well references in storage or linking
     * to other systems.
     *
     * @return
     */
    @Nonnull
    String getUniqueName();

    /**
     * Name used to display to other actors, users, etc
     *
     * @return
     */
    @Nonnull
    String getDisplayName();

    /**
     * int based ID of the actor. Generated
     * at runtime as a fast look up for connecting
     * information about this actor to other systems.
     * <p>
     * Example is storing memory of the AI's actions.
     *
     * @return
     */
    int getInstanceID();
}
