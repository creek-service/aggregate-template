import org.creekservice.api.platform.metadata.ComponentDescriptor;

// ChangeMe: change the name of the module to something more appropriate
module example.services {
    requires transitive example.api;

    exports org.acme.example.services;

    provides ComponentDescriptor with
            org.acme.example.services.ExampleServiceDescriptor;
}
