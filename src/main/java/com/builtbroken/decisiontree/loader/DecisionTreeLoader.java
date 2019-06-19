package com.builtbroken.decisiontree.loader;

import com.builtbroken.builder.ContentBuilderRefs;
import com.builtbroken.builder.loader.ContentLoader;
import com.builtbroken.builder.pipe.Pipe;
import com.builtbroken.decisiontree.DTReferences;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-06-19.
 */
public class DecisionTreeLoader extends ContentLoader
{

    public DecisionTreeLoader()
    {
        super(DTReferences.LOADER);
    }

    @Override
    protected void addPipes()
    {
        Pipe post = pipeLine.get(ContentBuilderRefs.PIPE_POST);
        post.addNode(new PipeSortActions(post));
    }
}
