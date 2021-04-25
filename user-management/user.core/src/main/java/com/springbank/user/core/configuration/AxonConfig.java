package com.springbank.user.core.configuration;

import com.mongodb.*;
import com.mongodb.client.*;
import org.axonframework.eventhandling.tokenstore.*;
import org.axonframework.eventsourcing.eventstore.*;
import org.axonframework.extensions.mongo.*;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.*;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.*;
import org.axonframework.serialization.*;
import org.axonframework.spring.config.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongodb.host:127.0.0.1}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port:27017}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database:user}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongo() {
        var mongoFactory = new MongoFactory();
        var mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder().mongoDatabase(mongo(), mongoDatabase).build();
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return MongoTokenStore.builder().mongoTemplate(axonMongoTemplate()).serializer(serializer).build();
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build();
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine,
                                         AxonConfiguration configuration) {
        return EmbeddedEventStore.builder().storageEngine(storageEngine).messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore")).build();
    }
}
