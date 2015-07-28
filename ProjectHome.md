JADE is a full-stack framework for Java application development.

1. The IoC/DI Core Container:

  * Seamlessly integrated with with my own AOP framework.
  * Enhanced dependency injection. Not only support inject components, but also support inject variables form any kind of context. Support expressive injection instruction. Provide navigating language to walk through the dependency graph.
  * Support annotations to markup the properties/setters/ctors for DI, under both java1.4 or 1.5.
  * Extended the PicoContainer interface and compatible with former PicoContainers.
  * Simplified XML configuration with XSD schema.

2. The Web Framework

  * Rails like MVC web development coding by convention
  * Seamlessly integrated with core container. Actions are managed by DI container as well so we can use dependency injection, [wiki:rt/action\_ioc\_support runtime data injection] and apply standard AOP-alliance interceptors on actions transparently.
  * Support pluggable template language, currently velocity & freemarker is supported.
  * Integrated with the latest jakarta commons validator framework to validate user inputs.
  * Multi-module support and will support mounting OSGI bundle soon.
  * An eclipse plug-in is available that helps developer to manage/develop/debug JADE-Web powered projects with ease.
  * ...

3. Extension components such as DAO, Access Control...

4. Maven plugins support

5. [Eclipse plugins support](http://jade.nonsoft.com/trac/wiki/EclipsePlugin)
