# How to import export packages inside modules

To export a package inside a module.
```
module module.name {
    exports com.some.package;
}
```

Then we can use this package from another module if we require the first module
as a depandenncy.
```
module client.module {
    requires module.name;
}
```

Compile the two example modules.
```
javac -d out --module-source-path src -m greeter.cli,greeter.hello
```

We could also only compile the greeter.cli module, because the other one
is declared as a dependency and will be automatically compiled.
```
javac -d out --module-source-path src -m greeter.cli
```

And run the main class of the cli module.
```
java -p out -m greeter.cli/greeter.cli.Main
```

If we would like to offer reflective access (diring runntime) for other frameworks
into one of out packages, for example see Spring or Hibernate (frameworks that use
reflective access on runntime), then we could use the opens keyword.
```
module module.name {
    exports com.some.package;
    opens com.some.package.util;
}
```

This means that the following code works.
```
Class.forName("com.some.package.util.Service").newInstance();
```

The reflective access does not work with exports, for allowing reflective access for an
exported package we can use both declarations.
```
module module.name {
    exports com.some.package;
    opens com.some.package;
    opens com.some.package.util;
}
```

We can also open all the packages in the module.
```
open module module.name {
    exports com.some.package;
}
```

We can also restrict the modules that use exported packages with the qualified exports.
If we want to allow only mymodule to access the exported package we could write.
```
module module.name {
    exports com.some.package to mymodule;
}
```