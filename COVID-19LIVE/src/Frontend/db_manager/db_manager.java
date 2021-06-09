package Frontend.db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import Frontend.Time.dateTime;


public class db_manager {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL="jdbc:mysql://182.215.62.205:3306/COVID19_LIVE?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final String DB_ID="root";
	private final String DB_PWD="as153462";
	
	private Connection conn = null;
	private Statement stmt_graph = null;
	private Statement stmt_map = null;
	private Statement stmt_label = null;
	private ResultSet rs_graph = null;
	private ResultSet rs_map = null;
	private ResultSet rs_label = null;
	
	private dateTime date;
	private String[] graph_string;
	private String[] map_string;
	private String[] label_string;
	private String confirmed;
	private String[] city_name = {"서울","부산","대구","인천","광주","대전","울산","세종",
            "경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"};
	
	public db_manager() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,DB_ID,DB_PWD);
			System.out.println("[MySQL CONNECT]");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String select_sql_graph(int i) {
		try {
			stmt_graph = conn.createStatement();
			graph_string = new String[5];
			for(int j=0; j<5; j++) {
				date = new dateTime();
				String graph_sql = "SELECT * FROM `covid_graph` WHERE date='"+date.date(j)+"';";
				rs_graph = stmt_graph.executeQuery(graph_sql);
					while(rs_graph.next()) {
						confirmed = rs_graph.getString(3);
						graph_string[j] = confirmed;
					}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graph_string[i];
	}
	public String select_sql_map(int i) {
		try {
			stmt_map = conn.createStatement();
			map_string = new String[17];
			for(int j=0; j<17; j++) {
				String map_sql = "SELECT * FROM `covid_map` WHERE city_name='"+city_name[j]+"';";
				rs_map = stmt_map.executeQuery(map_sql);
				while(rs_map.next()) {
					confirmed = rs_map.getString(3);
					map_string[j] = confirmed;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map_string[i];
	}
	public String select_sql_label(int i) {
		try {
			stmt_label = conn.createStatement();
			label_string = new String[5];
			date = new dateTime();
			String label_sql = "SELECT * FROM `covid_data` WHERE date='"+date.date(0)+"';";
			rs_label = stmt_label.executeQuery(label_sql);
				while(rs_label.next()) {
					confirmed = rs_label.getString(i);
					label_string[i] = confirmed;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return label_string[i];
	}
}
