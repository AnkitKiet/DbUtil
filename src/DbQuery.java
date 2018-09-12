import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbQuery {

	private String insertData(Connection conn, String query) {
		String finalvalue = null;
		PreparedStatement prepstmt = null;
		ResultSet resutlSet = null;
		ResultSetMetaData resultmt = null;
		StringBuffer stringbuffer = null;

		try {
			prepstmt = conn.prepareStatement(query);
			resutlSet = prepstmt.executeQuery();
			System.out.println(resutlSet.getMetaData().getColumnCount());
			int iColumns = resutlSet.getMetaData().getColumnCount();
			resultmt = resutlSet.getMetaData();
			stringbuffer = new StringBuffer();
			while (resutlSet.next()) {
				stringbuffer.append("<Output>");
				for (int i = 1; i <= iColumns; i++) {
					if (null == resutlSet.getString(i) || resutlSet.getString(i).equalsIgnoreCase("--select--")
							|| resutlSet.getString(i).equalsIgnoreCase("null")
							|| resutlSet.getString(i).trim().equals("")) {
						finalvalue = "";
					} else {
						finalvalue = resutlSet.getString(i);
					}
					stringbuffer.append("<" + resultmt.getColumnName(i) + ">" + finalvalue + "</"
							+ resultmt.getColumnName(i) + ">");
				}
				stringbuffer.append("</Output>");
			}
			System.out.println(stringbuffer);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
					prepstmt = null;
				}

			} catch (Exception sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		return stringbuffer;
	}

	public static void main(String[] args) {
		Connection conn = DbConnect.INSTANCE.createConnection();
		DbQuery obj = new DbQuery();
		obj.insertData(conn, "select * from WFFilterTable");
	}
}
