import org.acme.example.api.ExampleAggregateDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module example.mod.api {
    requires transitive creek.kafka.metadata;

    // Remove following if not using JSON payloads:
    requires static com.fasterxml.jackson.annotation;
    requires static creek.base.annotation;
    requires static io.swagger.v3.oas.annotations;

    exports org.acme.example.api;
    exports org.acme.example.internal to
            example.mod.services;

    provides ComponentDescriptor with
            ExampleAggregateDescriptor;
}
