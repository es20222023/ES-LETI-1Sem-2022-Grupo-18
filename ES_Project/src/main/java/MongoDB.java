

import java.io.*;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.util.JSON;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mongodb.DBObject;
import com.mongodb.MongoWriteException;
import org.apache.commons.io.IOUtils;
import org.bson.json.JsonMode;
import org.bson.json.JsonObject;
import org.bson.json.JsonWriterSettings;

import javax.naming.StringRefAddr;

public class MongoDB {

	public static void main(String[] args) throws IOException {

			//conectar mongoDB
        MongoClient client = MongoClients.create("mongodb+srv://testUser:BolaRebola@projesgrupo18.4wd7mpv.mongodb.net/?retryWrites=true&w=majority");

        MongoDatabase db = client.getDatabase("schedule");

        //MongoCollection<Document> collection = db.getCollection("week");
        MongoCollection<org.bson.Document> collection = db.getCollection("week");

//        Document sampleDoc = new Document("_id", "3").append("name", "John Smith");
//
//       collection.insertOne(sampleDoc);
       
        BufferedReader reader = new BufferedReader(new FileReader("madalena.json"));

        try {
            
            String json;
           json = reader.lines().collect(Collectors.joining());
           Document sampleDoc = new Document("_id", "1").append("name",json );
           collection.insertOne(sampleDoc);
            
       }catch(MongoWriteException e) {
         System.out.println(e.getMessage());
           }finally{
        	   reader.close();

           }
 

        
    }
}