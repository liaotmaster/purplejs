package io.purplejs.core.internal.value;

import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.purplejs.core.value.ScriptValue;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

final class ObjectScriptValue
    extends AbstractScriptValue
{
    private final ScriptValueFactory factory;

    private final ScriptObjectMirror value;

    ObjectScriptValue( final ScriptValueFactory factory, final ScriptObjectMirror value )
    {
        this.factory = factory;
        this.value = value;
    }

    @Override
    public boolean isObject()
    {
        return true;
    }

    @Override
    public Set<String> getKeys()
    {
        return this.value.keySet();
    }

    @Override
    public boolean hasMember( final String key )
    {
        return this.value.hasMember( key );
    }

    @Override
    public ScriptValue getMember( final String key )
    {
        return this.factory.newValue( this.value.getMember( key ) );
    }

    @Override
    public JsonElement toJson()
    {
        final JsonObject json = new JsonObject();
        for ( final String key : this.value.keySet() )
        {
            json.add( key, getMember( key ).toJson() );
        }

        return json;
    }

    @Override
    public Object getRaw()
    {
        return this.value;
    }
}
