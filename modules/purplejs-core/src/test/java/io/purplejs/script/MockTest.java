package io.purplejs.script;

import org.junit.Test;

import io.purplejs.resource.ResourcePath;

public class MockTest
    extends AbstractScriptTest
{
    @Test
    public void resolve()
    {
        run( ResourcePath.from( "/mock/mock-test.js" ) );
    }
}
