package com.builtbroken.decisiontree.api.action;

import com.builtbroken.decisiontree.api.context.IMemoryContext;
import com.builtbroken.decisiontree.api.context.IWorldContext;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-24.
 */
public interface IActionComponent<I extends IActionComponent>
{

    /**
     * Checks if this component can work with the world context and memory context provided
     * <p>
     * In some cases actions my require specific world context objects to function. This
     * happens when the action is built to use a direction access or wrapper version
     * of another object.
     *
     * @param worldContext
     * @param memoryContext
     * @return
     */
    boolean isCompatible(IWorldContext worldContext, IMemoryContext memoryContext);

    /**
     * Called to create a copy of the component.
     * <p>
     * Used when it duplicating logic trees for usage
     * in other places without worrying about
     * referenced being changed between trees by mistake.
     *
     * @return copy
     */
    I copy();
}
