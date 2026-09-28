module example.mod.example.service {
    requires example.mod.services;
    requires creek.service.context;
    requires creek.kafka.streams.extension;
    // Remove if not using JSON payloads:
    requires creek.kafka.serde.json.schema;
    requires org.apache.logging.log4j;
}
