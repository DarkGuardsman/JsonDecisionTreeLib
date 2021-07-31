package com.builtbroken.example.smith;

import com.builtbroken.builder.data.IJsonGeneratedObject;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robin Seifert on 7/1/2021.
 */
public class ObjectBuilder
{
    public static <I extends IJsonGeneratedObject> I buildObject(Class<I> iClass, Object... fields) {
        final Map<String, Object> map = new HashMap();
        for(int i = 0; i < fields.length; i += 2) {
            map.put((String) fields[0], fields[1]);
        }
        return buildObject(iClass, map);
    }

    public static <I extends IJsonGeneratedObject> I buildObject(Class<I> iClass, HashMap<String, Object> fields) {

        try
        {
            final I instance = iClass.newInstance();

            fields.forEach((fieldName, value) -> {
                Field field;

                //Find filed
                try
                {
                    field = iClass.getDeclaredField(fieldName);
                }
                catch (NoSuchFieldException noSuchFieldException1)
                {
                    try
                    {
                        field = iClass.getField(fieldName);
                    }
                    catch (NoSuchFieldException noSuchFieldException2)
                    {
                        Assertions.fail("Failed to locate field: " + fieldName, noSuchFieldException2);
                        return;
                    }
                }

                //Ensure we can edit
                field.setAccessible(true);

                //Apply value
                try
                {
                    field.set(instance, value);
                }
                catch (IllegalAccessException e)
                {
                    Assertions.fail("Failed to access field: " + fieldName, e);
                }
            });

            return instance;
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            Assertions.fail("Failed to create object from class: " + iClass, e);
        }
        return null;
    }
}
