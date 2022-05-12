# Supporting legacy classpath

To support the classpath with the module system (classpath will stay for a long time), the java module system uses some techniques. It puts all the compiled files of the classpath in an unnamed module. This unnamed module has access to all the methods of the java module system, even those who are not exported. It can also access reflective all the packages, even those not opened. The only difference is that if it tries to access a module that is not exported or opened, then on compile time a strong warning will be thrown of IllegalReflectiveAccess or this kind of exception, but the code will still run. In a next version of java, those warnings will become errors.

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