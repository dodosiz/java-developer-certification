module greeter.friendly {
    requires greeter.api;
    provides greeter.api.MessageService with greeter.friendly.FriendlyHello;
}
