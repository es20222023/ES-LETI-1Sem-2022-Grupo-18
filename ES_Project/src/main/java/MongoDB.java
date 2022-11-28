import java.io.File;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoDB {

	public static void main(String[] args) {

			//conectar mongoDB
        MongoClient client = MongoClients.create("mongodb+srv://testUser:BolaRebola@projesgrupo18.4wd7mpv.mongodb.net/?retryWrites=true&w=majority");

        MongoDatabase db = client.getDatabase("schedule");

        MongoCollection collection = db.getCollection("week");

        	//Inserir calendarios
        ArrayList<Document> listOfDocuments = new ArrayList<>();
//        File calendario = new File("HelloPdf.pdf");
        Document calendario = new Document("HelloPdf.pdf", "CALENDARIO");
        
        if (collection.find(calendario) != null) {
        	listOfDocuments.add(calendario);
        	collection.insertOne(calendario);
        	System.out.println("O Documento foi inserido com sucesso");
        }else {
        	System.out.println("O Documento já existe na base de Dados");
        }
    }
}