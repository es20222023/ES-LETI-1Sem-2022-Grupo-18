
import java.io.*;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.util.JSON;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoWriteException;
import org.apache.commons.io.IOUtils;
import org.bson.json.JsonMode;
import org.bson.json.JsonObject;
import org.bson.json.JsonWriterSettings;

import javax.naming.StringRefAddr;

public class MongoDB {

	// conectar mongoDB
	static MongoClient client = MongoClients
			.create("mongodb+srv://testUser:BolaRebola@projesgrupo18.4wd7mpv.mongodb.net/?retryWrites=true&w=majority");

	static MongoDatabase db = client.getDatabase("schedule");

	// MongoCollection<Document> collection = db.getCollection("week");
	static MongoCollection<org.bson.Document> collection = db.getCollection("week");

	public static void main(String[] args) throws IOException {
		importData("testejson.json");

	}

//        Document sampleDoc = new Document("_id", "3").append("name", "John Smith");
//       collection.insertOne(sampleDoc);

	public static void importData(String filename) throws IOException {
//        String name=filename.getName();
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		try {

			String[] n = filename.split("\\.");
			System.out.println("MOSTRA ISTO " + n[0]);

			String json;
			json = reader.lines().collect(Collectors.joining());


			Document sampleDoc = new Document("_id", n[0]).append("name", json);

			FindIterable<Document> iterobj = collection.find();

			// Print the documents using iterators
			MongoCursor<Document> itr = iterobj.iterator();
			while (itr.hasNext()) {
				
				Document doc = itr.next();
				String s = doc.toString();

				if (s.contains("_id=" + n[0])) {
					Bson filter = Filters.eq("_id", n[0]);
					collection.findOneAndReplace(filter, sampleDoc);
					System.out.println("Documento substituido");
				} else if (!itr.hasNext()) {			
					collection.insertOne(sampleDoc);
					System.out.println("Documento inserido");
				}
			}

		} catch (MongoWriteException e) {
			System.out.println(e.getMessage());
		} finally {
			reader.close();
		}

	}
}