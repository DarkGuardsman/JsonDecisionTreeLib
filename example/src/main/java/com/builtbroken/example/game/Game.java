package com.builtbroken.example.game;

import com.builtbroken.decisiontree.logic.ActorLogic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Although the AI exists inside of the World it is a separated concept.
 * To help with this Game class handles all of the information about the world.
 *
 * Created by Robin Seifert on 3/2/2022.
 */
@Data
public class Game
{
    private final World world = new World();
    private final List<ActorLogic> actors = new ArrayList();

    public boolean isRunning = true; //TODO kill when AI is done

    public void tick(int tick, float delta)
    {
       world.tick(tick);
       actors.forEach(actorLogic -> actorLogic.update(tick, delta));
    }

    public void addActor(ActorLogic actor) {
        actor.setStage(world, null, false); //Eventually we will wrap this with an entity and memory
        actors.add(actor);
    }
}
