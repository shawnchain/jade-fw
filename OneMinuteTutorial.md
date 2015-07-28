# One Minute Tutorial #
This wiki will show you how to use JADE-Core as an IoC container.

**NOTE** This wiki is outdated, please visit http://dev.nonsoft.com/projects/jade/wiki/quick_start for the latest documents.

# Environment Setup #
## Maven2 Setup ##
You need maven2 to run the sample codes. To verify your maven installation, use the following command:
```
C:\>mvn -v
Maven version: 2.0.6
```
For more advanced topics about maven configurations, Please reference this [wiki:dev/setup\_maven wiki]

## Eclipse Setup ##
You can use the following commands to generate an eclipse project file so that you can continue the tutorial with Eclipse.
```
C:\>cd jade-tutorial-1
C:\jade-tutorial-1>mvn eclipse:eclipse
```
If you have problem on setting up the classpath, please check out the doc here: http://maven.apache.org/plugins/maven-eclipse-plugin/overview.html

# Download the tutorial code #
Then please download the attached tutorial source code. [jade-tutorial-1.zip](http://jade-fw.googlecode.com/files/jade-tutorial-1.zip)

# Prepare the beans #
Now let's introduce some classes.
## The Kissable interface ##
```
package test.model;

public interface Kissable {

  public abstract void kiss(Object kisser);

}
```

## The Kissable Boy class ##
```
package test.model;

public class Boy implements Kissable {
  public void kiss(Object kisser) {
    System.out.println("I was kissed by " + kisser);
  }
}
```

## The Girl class that will kiss something kissable ##
```
package test.model;

public class Girl {
  private Kissable kissTarget;

  public Girl() {
  }
  
  public void kissSomeone() {
    kissTarget.kiss(this);
  }
  
  public Kissable getKissTarget(){
    return kissTarget;
  }
}
```

# Configuration #
Now we need to configure the girl class's dependency.
  1. By XML config
Now, create a xml file under your java source folder with name components.xml. Infact, you can create the file with the name whatever you want.
```
<container>
  <components>
    <component key="test.model.Kissable" class="test.model.Boy"/>
    <component key="test.model.Girl" class="test.model.Girl">
       <property name="kissTarget"/>
    </component>
  </components>
</container>
```
> 2. By Annotation[[br](br.md)]
> JADE Core support annotations with both java1.4 & java5.
    * For JDK1.4
```
package test.model;

public class Girl {

 /**
  *@InjectComponent() // Setter Injection
  */
  private Kissable kissTarget;

  public Girl() {
  }
  
 /**
  *@InjectComponent() // For Contructor Injection, duplicated here, just for demonstration.
  */
//  public Girl(Kissable target) {
//    this.kissTarget= target;
//  }

  public void kissSomeone() {
    kissTarget.kiss(this);
  }
  
  public Kissable getKissTarget(){
    return kissTarget;
  }
}
```
    * For JDK5.0+
```
package test.model;

public class Girl {
  @InjectComponent()
  private Kissable kissTarget;

  public Girl() {
  }
  
  public void kissSomeone() {
    kissTarget.kiss(this);
  }
  
  public Kissable getKissTarget(){
    return kissTarget;
  }
}
```
> 3. By API[[br](br.md)]
> JADE container is designed to be an embeddable container, so of course you can use API(the builder) to register/configure the components.
```
DefaultCoreContainer c = new DefaultCoreContainer();
c.registerComponent(Kissable.class,Boy.class);
c.registerComponent(
  new ComponentAdapterBuilder(Girl.class)
   .addProperty("boy")
   .getAdapter()
);
```

# Test It #
  * By config
```
IContainer container = ContainerFactory.getInstance("components.xml").getContainer();
Girl girl = (Girl)container.getComponentInstance(Girl.class);
assertNotNull(girl.getBoy());
```
  * By annotation & API
```
Girl girl = (Girl)c.getComponentInstance(Girl.class);
assertNotNull(girl.getBoy());
```

# Next Topic #
That's it! Now we can go one step further to learn more about JADE-Core -> [TwoMinuteTutorial](TwoMinuteTutorial.md)
