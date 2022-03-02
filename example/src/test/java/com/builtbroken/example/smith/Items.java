package com.builtbroken.example.smith;

import com.builtbroken.builder.data.DataFileLoad;
import com.builtbroken.builder.handler.IJsonObjectHandler;
import com.builtbroken.builder.loader.MainContentLoader;
import com.builtbroken.example.smith.data.content.Item;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Robin Seifert on 6/16/2021.
 */
@Data
@NoArgsConstructor
public final class Items
{
    @Setter(value = AccessLevel.NONE)
    private Item fuel;

    @Setter(value = AccessLevel.NONE)
    private Item ore;

    @Setter(value = AccessLevel.NONE)
    private Item ingots;

    public void setup()
    {
        //Load data
        final MainContentLoader loader = new MainContentLoader();
        loader.registerObjectTemplate(Item.class);
        loader.addFileLocator(() -> Arrays.asList(
                newItemFile("item:fuel", 3),
                newItemFile("item:ore", 3),
                newItemFile("item:ingots", 5)
        ));
        loader.setup();
        loader.load();

        //Assign out to fields
        final IJsonObjectHandler itemRegistry = loader.jsonObjectHandlerRegistry.getHandler(Item.TEMPLATE_ID);
        fuel = (Item) itemRegistry.getObject("item:fuel");
        ore = (Item) itemRegistry.getObject("item:ore");
        ingots = (Item) itemRegistry.getObject("item:ingots");
    }

    private DataFileLoad newItemFile(String id, int stackSize)
    {
        return new DataFileLoad(new File("./" + id.replace(":", ".") + ".json"), newItem(id, stackSize));
    }

    private JsonObject newItem(String id, int stackSize)
    {
        final JsonObject jsonData = new JsonObject();
        jsonData.addProperty("type", Item.TEMPLATE_ID);
        jsonData.addProperty("id", id);
        jsonData.addProperty("maxStack", stackSize);
        return jsonData;
    }
}
