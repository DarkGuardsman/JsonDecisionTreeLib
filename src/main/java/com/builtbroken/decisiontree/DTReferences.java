package com.builtbroken.decisiontree;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class DTReferences
{

    public static final String LOADER = "builtbroken:ai.tree";
    public static final String PREFIX = LOADER + ".";

    public static final String JSON_ACTOR = PREFIX + "actor";

    //Actions
    public static final String JSON_ACTION = PREFIX + "action";
    public static final String JSON_ACTION_SET = JSON_ACTION + ".set";
    public static final String JSON_ACTION_BRANCH = JSON_ACTION + ".branch";
    public static final String JSON_ACTION_CHOICE = JSON_ACTION + ".choice";

    public static final String JSON_ACTION_PRINT_LINE = JSON_ACTION + ".print.line";
    public static final String JSON_ACTION_WAIT = JSON_ACTION + ".wait";

    //Choices
    public static final String JSON_CHOICE = PREFIX + "choice";
    public static final String JSON_MEMORY_HAS = JSON_CHOICE + ".memory.has";
    public static final String JSON_MEMORY_VALUE = JSON_CHOICE + ".memory.value";

    //Memory
    public static final String JSON_MEMORY = PREFIX + "memory";

    //Pipes
    public static final String PIPE_SORT = PREFIX + "action.sort";
}
