package supportclasses;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class ToArff {
	private String path;

	public ToArff(String path) {
		this.path = path;
	}
	
	public String getText() {
		String fileText = "";
		try {
		BufferedReader in = new BufferedReader(new FileReader(path));
		String line;
			while((line = in.readLine()) != null) {
			    fileText = ""+fileText + line;   
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileText;
	}

	public void convert() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter("output.arff", "UTF-8");
			out.println("@relation _directory_to_test_file\n\n"
					+ "@attribute text string\n"
					+ "@attribute @@class@@ {business,entertainment,politics,sport,tech}\n");
			out.print("@data\n\n\'");

			int c = 0;
			while((c = in.read()) != -1) {
				char character = (char) c;
				String s = Character.toString(character);

				if (s.matches("['\"%]")){
					out.print("\\" + character);
				}
				else if(s.matches("[\n\r]")) {
					out.print("\\n");
				}
				else{
					out.print(character);
				}
			}
			out.print("\',?");
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
