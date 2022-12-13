
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

	static MongoCollection<org.bson.Document> collection = db.getCollection("week");

	public static void main(String[] args) throws IOException {
		importData("madalena.json");
		outputData("madalena");
	}


	public static void importData(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		try {

			String[] n = filename.split("\\.");

			String json;
			json = reader.lines().collect(Collectors.joining("\n"));


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
	public static File outputData(String name) throws IOException {
		FindIterable<Document> iterobj = collection.find();

		// Print the documents using iterators
		MongoCursor<Document> itr = iterobj.iterator();
		String json=null;
		while (itr.hasNext()) {
			
			Document doc = itr.next();
			String s = doc.toString();

			if (s.contains("_id=" + name)) {
				json=s;
				System.out.println(json);
			} 
		}
		
		FileWriter fw= new FileWriter(name+".json");
		fw.write(json);
		fw.close();
		File f= new File(name+".json");
		return f;
	}
	
}