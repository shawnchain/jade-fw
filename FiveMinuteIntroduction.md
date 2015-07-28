# Five Minute Introduction #

JADE is more than a bare IoC/DI container. There're several extension components available and help it be a full-stack framework.
  * JADE-Config
> > We need this component to support XML config
  * JADE-Annotation-backport175
> > We need this component to support annotations under JDK1.4
  * JADE-Annotation-tigher
> > We need this component to support annotation under JDK5 or higher
  * JADE-Bootstrap
> > We need this component to maintenance hierarchal containers and also boot-up/mount containers from bundles(such as JAR files).
  * JADE-DAO
> > DAO component provides infrastructural support for EJB3 like persistence service. It currently includes:
    * Pluggable persistence backend (currently support Hibernate2/3 & iBatis).
    * Declarative Transaction support.
> > > Any method that is annotated with @Transactional attribute will have a transaction context ready before the method being invoked and the context will be close automatically when invocation completed. There is no magic, all this is done by the AOP framework. But the different here compare to other framework is that developer only need to annotate their business methods and no extra configurations are needed any more!

Other components that are in sandbox
  * JADE-Access

> > Security support component. Spring ACEGI is good enough I think, so the quick way to implement this is just migrate ACEGI codes to JADE-Core container.
  * JADE-Script
> > Support script languages. Groovy will be the first candidate. But if we supported groovy, will JADE be yet another GRails?
  * JADE-AOP
> > Currently we'v already had an embedded AOP framework based on CGLib. It's not enough. We're thinking of refactoring this component based on a more generic idea: Metadata Oriented Programming.

## Develop Application in Rails-Way ##
We believe that a good framework should be flexible and configurable. But without the configuration, the framework should also be working with some default conventions. In most cases we do not need to step into the configuration hell.
JADE is designed to be such a kind of framework.

// TODO

## Pluggable Application ##
// TODO

## Recruiting ##

If you're interested by the above ideas and has the confident to be the owner of one of them, just [let us know](http://groups.google.com/group/jade-fw).



