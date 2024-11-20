package org.example.mongodbdemo;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class QuickStart {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "test";
    private static final String COLLECTION_NAME = "test_collection";
    private static MongoClient mongoClient;

    static {
        mongoClient = MongoClients.create(URI);
    }

    public static MongoCollection<Document> getCollection() {
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }
    public static void addDocument(String name, int age, String city) {
        MongoCollection<Document> collection = getCollection();
        Document doc = new Document("name", name)
                .append("age", age)
                .append("city", city);
        collection.insertOne(doc);
    }

    public static Document readDocumentById(String id) {
        MongoCollection<Document> collection = getCollection();
        return collection.find(eq("_id", new ObjectId(id))).first();
    }

    public static Document readDocumentByName(String name) {
        MongoCollection<Document> collection = getCollection();
        return collection.find(eq("name", name)).first();
    }

    public static void updateDocument(String id, String name, int age, String city) {
        MongoCollection<Document> collection = getCollection();
        Document updatedDoc = new Document("name", name)
                .append("age", age)
                .append("city", city);
        collection.updateOne(eq("_id", new ObjectId(id)), new Document("$set", updatedDoc));
    }

    public static void deleteDocument(String id) {
        MongoCollection<Document> collection = getCollection();
        collection.deleteOne(eq("_id", new ObjectId(id)));
    }

    public static void shutdown() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}