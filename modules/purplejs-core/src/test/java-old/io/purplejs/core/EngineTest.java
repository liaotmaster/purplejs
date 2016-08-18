package io.purplejs.core;

import java.util.function.Consumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import io.purplejs.core.resource.ResourceLoader;
import io.purplejs.core.resource.ResourceLoaderBuilder;

import static org.junit.Assert.*;

public class EngineTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void getConfig()
    {
        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.config( "key", "value" ) ).
            build();

        assertNotNull( engine.getConfig() );
        assertEquals( 1, engine.getConfig().size() );
    }

    @Test
    public void devSourceDir()
    {
        EngineBuilder.newBuilder().
            devSourceDir( this.temporaryFolder.getRoot() ).
            build();
    }

    @Test
    public void getClassLoader()
    {
        final Engine engine1 = EngineBuilder.newBuilder().
            build();

        assertSame( getClass().getClassLoader(), engine1.getClassLoader() );

        final ClassLoader cl = Mockito.mock( ClassLoader.class );
        final Engine engine2 = EngineBuilder.newBuilder().
            classLoader( cl ).
            build();

        assertSame( cl, engine2.getClassLoader() );
    }

    @Test
    public void getInstance()
    {
        final String value = "hello";

        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.instance( String.class, value ) ).
            build();

        assertSame( value, engine.getInstance( String.class ) );
    }

    @Test
    public void getSupplier()
    {
        final String value = "hello";

        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.provider( String.class, () -> value ) ).
            build();

        assertSame( value, engine.getProvider( String.class ).get() );
    }

    @Test
    public void getOptional()
    {
        final String value = "hello";

        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.instance( String.class, value ) ).
            build();

        assertSame( value, engine.getOptional( String.class ).orElse( null ) );
    }

    @Test
    public void initializer()
    {
        Consumer<Engine> initializer = engineMockCallback();

        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.initializer( initializer ) ).
            build();

        assertNotNull( engine );
        Mockito.verify( initializer, Mockito.times( 1 ) ).accept( engine );
    }

    @Test
    public void disposer()
    {
        Consumer<Engine> disposer = engineMockCallback();

        final Engine engine = EngineBuilder.newBuilder().
            module( binder -> binder.disposer( disposer ) ).
            build();

        Mockito.verify( disposer, Mockito.times( 0 ) ).accept( engine );
        engine.dispose();
        Mockito.verify( disposer, Mockito.times( 1 ) ).accept( engine );
    }

    @Test
    public void getResourceLoader()
    {
        final ResourceLoader loader = ResourceLoaderBuilder.newBuilder().build();

        final Engine engine = EngineBuilder.newBuilder().
            resourceLoader( loader ).
            build();

        assertSame( loader, engine.getResourceLoader() );
    }

    @SuppressWarnings("unchecked")
    private Consumer<Engine> engineMockCallback()
    {
        return Mockito.mock( Consumer.class );
    }
}