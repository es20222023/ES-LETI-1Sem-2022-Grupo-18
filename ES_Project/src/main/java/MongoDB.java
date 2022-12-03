

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
       
       
//        BasicDBObject document = new BasicDBObject();
//        document.put("database", "dineshonjavaDB");
//        document.put("table", "employees");
//         
//        BasicDBObject documentDetail = new BasicDBObject();
//        documentDetail.put("empId", "10001");
//        documentDetail.put("empName", "Dinesh");
//        documentDetail.put("salary", "70000");
//         
//        document.put("detail", documentDetail);
         
        //collection.insert(document);
        
//        try {
//
//            //drop previous import
//            //collection.drop();
//
//            //Bulk Approach:
//            int count = 0;
//            int batch = 100;
//            List<InsertOneModel<Document>> docs = new ArrayList<>();
//
//            try (BufferedReader br = new BufferedReader(new FileReader("madalena.json"))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    docs.add(new InsertOneModel<>(Document.parse(line)));
//                    count++;
//                    if (count == batch) {
//                        collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
//                        docs.clear();
//                        count = 0;
//                    }
//                }
//            }
//
//            if (count > 0) {
//                com.mongodb.bulk.BulkWriteResult bulkWriteResult=  collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
//                System.out.println("Inserted" + bulkWriteResult);
//            }
//
//        } catch (MongoWriteException e) {
//            System.out.println("Error");
//        }
        
        
//        BufferedWriter writer = new BufferedWriter(new FileWriter("madalena.json"));
//
//        try {
//            JsonWriterSettings settings = JsonWriterSettings.builder().outputMode(JsonMode.EXTENDED).build();
//            for (Document doc : collection.find()) {
//                writer.write(doc.toJson(settings));
//                writer.newLine();
//            
//               
//            }
//            writer.close();
//        }catch (MongoWriteException e) {
//          System.out.println("Error");
//      }

        
    }
}