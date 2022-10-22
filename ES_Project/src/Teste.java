import java.nio.file.Files;
import java.nio.file.Paths;


import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.aspose.cells.*;

import java.io.File;
import java.io.IOException;

public class Teste {
	
	static public void testes() {

	// create a blank Workbook object
	Workbook workbook = new Workbook();

	// access default empty worksheet
	Worksheet worksheet = workbook.getWorksheets().get(0);
	

	// read JSON file
	String fileName = "mmrtj.json";
	String content;
	
	try {
		content = new String(Files.readAllBytes(Paths.get(fileName)));
	
	

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
	System.out.println("feito");
	}
}
