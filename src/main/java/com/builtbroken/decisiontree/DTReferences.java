package com.builtbroken.decisiontree;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class DTReferences
{
    public static final String LOADER = "builtbroken:decision.tree";
    public static final String PREFIX = LOADER + ".";

    public static final String JSON_ACTOR = PREFIX + "actor";

    //Actions
    public static final String JSON_ACTION = PREFIX + "action";
    public static final String JSON_ACTION_SET = JSON_ACTION  + ".set";
    public static final String JSON_ACTION_PRINT_LINE = JSON_ACTION  + ".print.line";

    //Pipes
    public static final String PIPE_SORT = PREFIX + "action.sort";
}
