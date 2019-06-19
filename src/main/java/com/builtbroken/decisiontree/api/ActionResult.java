package com.builtbroken.decisiontree.api;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public enum ActionResult
{
    /**
     * Action has started
     */
    START,
    /**
     * Action has completed fully, continue AI loop to next task
     */
    COMPLETE,
    /**
     * Action has completed fully, pause AI loop until next game tick
     */
    STEP,
    /**
     * Action still has more to do
     */
    RUNNING,
    /**
     * Action completed, end AI loop
     */
    END,
    /**
     * Action was passed without running logic
     */
    PASS,
    /**
     * Action errored
     */
    ERROR
}
