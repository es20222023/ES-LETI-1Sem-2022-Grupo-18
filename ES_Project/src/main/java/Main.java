public class Main {
	public static void main(String [] args) {
		System.out.println("123");
		String a = "{'id':'331','nome':'Produto 1', valor':'11.98'},{'id':'331','nome':'Produto 2', valor':'11.98'},{'id':'331','nome':'Produto 3', valor':'11.98'}";
		JsonToPdf.testes(a);
	}
}