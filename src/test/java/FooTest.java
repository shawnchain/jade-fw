import java.lang.reflect.Field;

import junit.framework.TestCase;

public class FooTest extends TestCase {
    public void testFoo() throws Exception{
        Field f = Bar1.class.getSuperclass().getDeclaredField("name");
        f.setAccessible(true);
        f.set(null, "bar1");
        
        f = Bar2.class.getSuperclass().getDeclaredField("name");
        f.setAccessible(true);
        f.set(null, "bar2");
        
        System.out.println(Bar1.name);
        
        System.out.println(Bar2.name);
    }

    public static class Foo {
        protected static String name;
    }

    public static class Bar1 extends Foo {

    }

    public static class Bar2 extends Foo {

    }
}
