# Explore the modular JDK

To list all Java modules.
```
java --list-modules
```

You will get 2 kinds of modules:

- Modules starting with java must be provided by every jdk and offer the code functionality.
```
java.base@11.0.15
java.compiler@11.0.15
java.datatransfer@11.0.15
java.desktop@11.0.15
java.instrument@11.0.15
java.logging@11.0.15
```

- Modules starting with jdk are implementation dependent, different jdks may have different sets of them.
```
jdk.editpad@11.0.15
jdk.hotspot.agent@11.0.15
jdk.httpserver@11.0.15
jdk.internal.ed@11.0.15
jdk.internal.jvmstat@11.0.15
```

To inspect a module.
```
java --describe-module java.sql
```

We get an output like this.
```
java.sql@11.0.15
exports java.sql
exports javax.sql
requires java.xml transitive
requires java.logging transitive
requires java.transaction.xa transitive
requires java.base mandated
uses java.sql.Driver
```

The java.base module is automatically included in every module, that's why it is marked with mandated.
The others we can see they are declared as transitive dependencies and the last one is a service.
Transitive means that the dependency is also becomming automaticaly a dependency of another module that will use the module with the transitive dependency.

We can also see the module resolution of custom modules. This is good for finding errors.
```
java --show-module-resolution -p out -m mymodule
```

So try to compile them first.
```
javac -d out --module-source-path src -m mymodule,util
```

Then if you execute the module resolution command you can see a detailed resolution of all the dependencies.

In order to package this module into a jar file.
```
jar --create --file=example.jar -C out\ .
```

Then you can also inspect the module in the jar file.
```
jar --describe-module --file=example.jar
```

There is also a new packaging format used for internal modules of the jdk and it is called jmod. It's main purpose it to provide legal notices and link to native libraries and header files. These are not executable modules.