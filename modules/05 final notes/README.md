# Supporting legacy classpath

To support the classpath with the module system (classpath will stay for a long time), the java module system uses some techniques. It puts all the compiled files of the classpath in an unnamed module. This unnamed module has access to all the methods of the java module system, even those who are not exported (in the runntime). It can also access reflective all the packages, even those not opened. The only difference is that if it tries to access a module that is not opened, then on runtime a strong warning will be thrown of IllegalReflectiveAccess, but the code will still run. In a next version of java, those warnings may become errors.

In order to make those warnings an error in java 11 use the flag:
```
--illegal-access=deny
```

This flag is by default false but on java 16 it will become by default true.

To allow reflective access for a module.
```
java --add-opens module.name/package.location=<module_to_allow>
```

And for the classpath, as it is an unnamed module.
```
java --add-opens module.name/package.location=ALL_UNNAMED
```

To proper migrate a class or even a whole jar file that uses private modules, we can use the jdeps command.
```
jdeps --jdkinternals out/com/example/Legacy.class
```

This will also suggest the alternative replacement for some modules.