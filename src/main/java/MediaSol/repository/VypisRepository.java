package MediaSol.repository;

import org.bson.*;
import com.google.gson.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import MediaSol.model.*;


public class VypisRepository {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection collection;
    private Gson gson;

    public VypisRepository() {

            this.mongoClient = new MongoClient("localhost",27017);
            this.database = mongoClient.getDatabase("MediaSol");
            this.collection = database.getCollection("Vypisy") ;
            this.gson = new Gson();
    }

    public void save (Vypis vypis){

        String json = gson.toJson(vypis);
        Document vypis_doc = new Document("Vypis", json );
        collection.insertOne(vypis_doc);
    }

}
