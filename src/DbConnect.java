import java.sql.Connection;
import java.sql.DriverManager;

public enum DbConnect {
	INSTANCE;
	private Connection connection = null;
	public Connection createConnection() {
		try {
			if(connection == null){
			ReadJsonFile obj = new ReadJsonFile();
			Class.forName(obj.getKey("driverName"));
			String url = obj.getKey("url");
			String username = obj.getKey("UserName");
			String password = obj.getKey("Password");
			connection = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static DbConnect getDBConnection() {
		return INSTANCE;
	}

	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			con = null;
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = DbConnect.INSTANCE.createConnection();
		System.out.println(conn);
		DbConnect.INSTANCE.closeConnection(conn);
		System.out.println(conn);
	}
}
