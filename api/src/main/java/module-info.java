import org.acme.example.api.ExampleAggregateDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module example.mod.api {
    requires transitive creek.kafka.metadata;

    exports org.acme.example.api;
    exports org.acme.example.internal to
            example.mod.services;

    provides ComponentDescriptor with
            ExampleAggregateDescriptor;
}
