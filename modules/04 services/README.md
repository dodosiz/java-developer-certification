# Working with services

The services are made to solve the problem of strong coupling between modules.

The way it works is simple.

- Define a module with an interface as an api.
```
module some.api {
    exports some.api; // let's say in some.api there is a ExampleService
}
```

- Define a module or more that provide the implementation. ExampleService can be an interface, abstract or even concrete class, but the class following the with has to be a subclass or implementation (in case of interface).
```
module example.implementation {
    requires some.api;
    provides some.api.ExampleService with example.implementation.TheImplementation;
}

- The client needs only to require the api and define the service interface it uses.
```
module client.module {
    requires some.api;
    uses some.api.ExampleService;
}
```

We can also use import statement to make the uses shorter (the same applies to provides statements).
```
import some.api.ExampleService;
module client.module {
    requires some.api;
    uses ExampleService;
}
```

- The concrete implementation can be loaded with a ServiceLoader, that returns an Itarable of all the available services.
```
Iterable<MessageService> services = ServiceLoader.load(ExampleService.class);
```

Compile the example.
```
javac -d out --module-source-path src -m greeter.cli
```

And run.
```
java -p out -m greeter.cli/greeter.cli.Main
```

You will see no message is printed out, this is because we did not compile the implementation modules. To do so try to compile them and run again.
```
// only one will be available
javac -d out --module-source-path src -m greeter.cli,greeter.friendly
// or if you want both
javac -d out --module-source-path src -m greeter.cli,greeter.friendly,greeter.hello
```