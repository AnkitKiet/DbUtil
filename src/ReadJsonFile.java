import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJsonFile {

	protected String getKey(String key) {
		JSONParser parser = new JSONParser();
		String value="";
		try {
			Object obj = parser.parse(new FileReader(".\\src\\config\\db.json"));
			JSONObject jsonObject = (JSONObject) obj;
			value = (String) jsonObject.get(key);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		}
		return value;
	}

}
