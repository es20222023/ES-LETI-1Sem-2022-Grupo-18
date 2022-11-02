import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.aspose.cells.*;
import java.io.File;
import java.io.IOException;
	
	
public class JsonToPdf {


	static public void testes(String content) {

		// read JSON file
		
		//String fileName = "formatted.json";
		//String content = "{'id':'331','nome':'Produto 1', valor':'11.98'},{'id':'331','nome':'Produto 2', valor':'11.98'},{'id':'331','nome':'Produto 3', valor':'11.98'}";
		
		// create a blank Workbook object
		Workbook workbook = new Workbook();
		// access default empty worksheet
		Worksheet worksheet = workbook.getWorksheets().get(0);


		// read JSON file
		//String fileName = "formatted.json";
			try {
				//content = new String(Files.readAllBytes(Paths.get(fileName)));
				
			//set JsonLayoutOptions for formatting
			JsonLayoutOptions l = new JsonLayoutOptions();
			l.setArrayAsTable(true);
			
			//import JSON data to default worksheet starting at cell A1	
				JsonUtility.importData(content, worksheet.getCells(), 0, 0, l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//convert imported JSON to PDF
			try {
				workbook.save("output.pdf", SaveFormat.AUTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("O PDF foi gerado");
			}
		}

