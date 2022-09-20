// ChangeMe: change the name of the module to something more appropriate
module example.api {
    requires transitive creek.kafka.metadata;

    exports org.acme.example.api;
    exports org.acme.example.internal to
            example.services,
            example.service;
}
