package com.datmt.logging.config;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MongoLogAppender extends AppenderBase<ILoggingEvent> {

private String connectionString;
    private String databaseName;
    private String collectionName;
    private MongoCollection<org.bson.Document> collection;

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public void start() {
        super.start();
        try {
            var mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            collection = database.getCollection(collectionName);
        } catch (Exception e) {
            addError("Error connecting to MongoDB", e);
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (collection != null) {
            Document log = new Document("level", eventObject.getLevel().toString())
                    .append("logger", eventObject.getLoggerName())
                    .append("message", eventObject.getFormattedMessage())
                    .append("timestamp", eventObject.getTimeStamp());
            collection.insertOne(log);
        }
    }


}
