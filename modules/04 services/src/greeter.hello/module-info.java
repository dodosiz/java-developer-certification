module greeter.hello {
    requires greeter.api;
    provides greeter.api.MessageService with greeter.hello.Hello;
}
