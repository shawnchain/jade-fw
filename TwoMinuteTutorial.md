# Two Minute Tutorial #
This page will show you how to enjoy the AOP service provied by JADE-Core

## Before Start ##
You're recommanded to read the [wiki:quick\_start/1\_min\_tutorial One minute tutorial] before starting this topic

# Download the tutorial code #
Please download the attached tutorial source code. attachment:"jade-tutorial-2.zip"

# Adding Interceptor class #
''Note'': JADE-Core currently only supports AOPAlliance interceptor
## The KissInterceptor class ##
```
package test.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import test.model.Kissable;

public class KissInterceptor implements MethodInterceptor {
  public Object invoke(MethodInvocation invocation) throws Throwable {
      Kissable _k = (Kissable) invocation.getThis(); // See pointcut definition
      
      Object _kissTarget = invocation.getArguments()[0];
      System.err.println("[KissInterceptor]: GEEE.... " + _k + " is going to kiss " + _kissTarget);
    return invocation.proceed();
  }
}
```

# Configuration #

## Register interceptor ##
Adding interceptor registration entry into the components.xml.After that, the config file should look like this:
```
<?xml version="1.0" encoding="UTF-8"?>
<container xmlns="http://www.nonsoft.com/JADE/1.0" name="/core" abortOnError="true">
	<components>
		<component key="test.model.Kissable" class="test.model.Boy" />
		<component key="test.model.Girl" class="test.model.Girl">
			<property name="kissTarget" />
		</component>
	</components>
	<advices>
		<interceptor class="test.interceptor.KissInterceptor" key="kissInterceptor" />
	</advices>
</container>
```
You can see now that the interceptor has been registered as component. Actually, they are treated the same as components in JADE-Core, so dependency injection service is also available to the interceptors.

## Adding pointcut definition ##
Pointcut definitions is just like the glue that tell JADE-Core how should components and aspects work together
Change the config file to:
```
<?xml version="1.0" encoding="UTF-8"?>
<container xmlns="http://www.nonsoft.com/JADE/1.0" name="/core" abortOnError="true">
	<components>
		<component key="test.model.Kissable" class="test.model.Boy" />
		<component key="test.model.Girl" class="test.model.Girl">
			<property name="kissTarget" />
		</component>
	</components>
	
	<aspects>
		<pointcut interface="test.model.Kissable" methodPattern="kiss"><interceptor-ref>kissInterceptor</interceptor-ref></pointcut>
	</aspects>
	
	<advices>
		<interceptor class="test.interceptor.KissInterceptor" key="kissInterceptor" />
	</advices>
</container>
```
'''Note''': you can define pointcuts by class/method name pattern or by annotation, please see documents for more details.

# Test It #
```
IContainer container = ContainerFactory.getInstance("components.xml").getContainer();
Girl girl = (Girl)container.getComponentInstance(Girl.class);
girl.kiss();
```

## Output ##
```
[KissInterceptor]: GEEE.... test.model.Boy$$EnhancerByCGLIB$$cb5ac629@1a786c3 is going to kiss test.model.Girl@116ab4e
I was kissed by test.model.Girl@116ab4e
```

# What's next? #
Now, I hope you have got some idea about JADE-Core. In fact, the above examples are so trivial that could not show you everything. In the next tutorial, We will discuss something advanced about JADE Core.

You can:
  * Take a rest
  * Check AdvancedComponentConfig
  * Read more about JADE Framework - FiveMinuteIntroduction
  * Project Server - http://jade.nonsoft.com/trac/

Also, there is a demo site available at http://jade.nonsoft.com/demo/ , which is powered by JADE Framework