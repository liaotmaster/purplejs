package io.purplejs.testing.junit;

import java.util.List;

import io.purplejs.core.Engine;
import io.purplejs.core.EngineBuilder;
import io.purplejs.core.EngineModule;

public interface ScriptTestSuite
    extends EngineModule
{
    List<String> getTestFiles();

    void initialize( Engine engine );

    void configure( EngineBuilder builder );

    void setUp()
        throws Exception;

    void tearDown()
        throws Exception;
}