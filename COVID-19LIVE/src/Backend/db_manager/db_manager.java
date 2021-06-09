package Backend.db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Backend.Time.dateTime;
import Backend.data_crawl.data_crawl;

public class db_manager extends Thread{
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://ipaddress:3306/COVID19_LIVE?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
    private final String USER_NAME =""; //id
    private final String PASSWORD = ""; //pwd
    
    private dateTime date = new dateTime();
	private data_crawl data = new data_crawl();
	
	private String[] city_name = {"서울","부산","대구","인천","광주","대전","울산","세종",
			"경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"};
	
	//첫 데이터베이스에 입력되는 SQL문
	private String insert_sql_covid_data;
    private String insert_sql_covid_graph;
	private String insert_sql_covid_map_sql;
	
	//24시간마다 입력되는 SQL구문
	private String update_sql_covid_data;
	private String update_sql_covid_map;
	
    private Connection conn = null;
    //SQL UPDATE, INSERT 를 statement
    private Statement state = null;
	private Thread update_sql_thread;
	
	//Select 문을 위한 Statement
	private Statement state_select_covid_data = null;
	private Statement state_select_covid_map = null;
	private Statement state_select_covid_graph = null;
	
	//Select 문의 데이터값을 받기위한 ResultSet
	private ResultSet rs_sql_covid_data;
	private ResultSet rs_sql_covid_map;
	private ResultSet rs_sql_covid_graph;
	
	//select SQL문
	private String is_sql_covid_data;
	private String is_sql_covid_map;
	private String is_sql_covid_graph;
	
	//delete SQL문
	private String delete_sql_covid_data;
	private String delete_sql_covid_map;
	
	private String refresh_date_time;
	private Thread insert_sql_thread;
	
	
	
    public db_manager() {
    	try {
			Class.forName(JDBC_DRIVER); // 라이브러리 확인
			conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD); //
            System.out.println("[MySQL Connection]");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public void update_sql_covid_table() {
    	try {
			date = new dateTime();
			data = new data_crawl();
			
			state = conn.createStatement();
			update_sql_covid_data = "UPDATE `covid_data` SET `date`= '"+date.date()+"',`confirmed_p`='"+data.table_coviddata(3)+"'"
					+ ",`recov_p`='"+data.table_coviddata(5)+"',`death_p`='"+data.table_coviddata(6)+"' WHERE 1;";
			is_sql_covid_graph = "SELECT * FROM `covid_graph` WHERE `date`='"+date.date()+"';";
			
			System.out.println("[UPDATE SQL START Table : covid_map TIME : "+date.dateTime()+"]");
			for (int i = 0; i < city_name.length; i++) {
					update_sql_covid_map = "UPDATE `covid_map` SET `date`='"+date.date()+"',`confirmed_p`='"+data.table_covidmap(i)+
							"' WHERE city_name='"+city_name[i]+"'";		
					state.execute(update_sql_covid_map);
					System.out.println(update_sql_covid_map);
				}
				System.out.println("[UPDATE SQL FINISH Table : covid_map TIME : "+date.dateTime()+"]\n");		
				System.out.println("[UPDATE SQL START Table : covid_data TIME : "+date.dateTime()+"]");
				state.execute(update_sql_covid_data);
				System.out.println(update_sql_covid_data);
				System.out.println("[UPDATE SQL FINISH Table : covid_data TIME : "+date.dateTime()+"]\n");
				
				refresh_date_time = date.dateTime(); // 갱신시간 설정
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    public void insert_sql_covid_table() {
    	try {
    		date = new dateTime();
    		data = new data_crawl();
    		
    		delete_sql_covid_data = "delete from covid_data;";
    		delete_sql_covid_map =  "delete from covid_map;";
    		
    		insert_sql_covid_data = "insert into covid_data (date, confirmed_p, recov_p, death_p) Values "
    				+ "('"+date.date()+"', '"+data.table_coviddata(3)+"', '"+data.table_coviddata(5)+"', '"+data.table_coviddata(6)+"');";
    		insert_sql_covid_graph = "insert into covid_graph (date, confirmed_p) Values "+ "('"+date.date()+"', '"+data.table_covidgraph()+"');";
    		
    		is_sql_covid_data = "SELECT * FROM `covid_data` WHERE `date`='"+date.date()+"';";
    		is_sql_covid_map = "SELECT * FROM `covid_map` WHERE `date`='"+date.date()+"';";
    		is_sql_covid_graph = "SELECT * FROM `covid_graph` WHERE `date`='"+date.date()+"';";
    		
    		state_select_covid_data = conn.createStatement();
    		rs_sql_covid_data = state_select_covid_data.executeQuery(is_sql_covid_data);
    		System.out.println("[Table:covid_data Connection]");
    	
	    	state_select_covid_map = conn.createStatement();
	    	rs_sql_covid_map = state_select_covid_map.executeQuery(is_sql_covid_map);
	    	System.out.println("[Table:covid_map Connection]");
    	
	    	state_select_covid_graph = conn.createStatement();
	    	rs_sql_covid_graph = state_select_covid_graph.executeQuery(is_sql_covid_graph);
	    	System.out.println("[Table:covid_graph Connection]\n");
    	
	    	state = conn.createStatement();
	    	
	    	if(!rs_sql_covid_map.next()) {
	    		state.execute(delete_sql_covid_map);
	    		System.out.println("[INSERT SQL START Table : covid_map TIME : "+date.dateTime()+"]");
	    		for (int i = 0; i < city_name.length; i++) {
	    			insert_sql_covid_map_sql = "insert into covid_map (date, city_name, confirmed_p) Values "
	    					+ "('"+date.date()+"', '"+city_name[i]+"', '"+data.table_covidmap(i)+"');";
	    			state.execute(insert_sql_covid_map_sql);
	    			System.out.println(insert_sql_covid_map_sql);
	    		}
	    		System.out.println("[INSERT SQL FINISH Table : covid_data TIME : "+date.dateTime()+"]\n");
	    	}
    	
	    	if (!rs_sql_covid_data.next()) {
	    		System.out.println("[INSERT SQL START Table : covid_data TIME : "+date.dateTime()+"]");
	    		state.execute(delete_sql_covid_data);
	    		state.execute(insert_sql_covid_data);
	    		System.out.println(insert_sql_covid_data);
	    		System.out.println("[INSERT SQL FINISH Table : covid_data TIME : "+date.dateTime()+"]\n");
	    	}
    	
	    	if(!rs_sql_covid_graph.next()) {
	    		System.out.println("[INSERT SQL START Table : covid_graph TIME : "+date.dateTime()+"]");
	    		state.execute(insert_sql_covid_graph);
	    		System.out.println(date.dateTime()+" [Insert SQL Table : covid_graph] \n "+insert_sql_covid_graph);
				System.out.println("[INSERT SQL FINISH Table : covid_graph TIME :"+date.dateTime()+"]\n");
	    	}
	    		Thread.sleep(10000);
    		} catch (SQLException e) {
			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InterruptedException e) {
				// TODO: handle exception
			}
    	}

    public void thread_sql_covid_table() {
    	insert_sql_thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					insert_sql_covid_table();
					try {
						Thread.sleep(10000); // 갱신의 시간
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
    	insert_sql_thread.start();
    	update_sql_thread = new Thread(new Runnable() {
			
			@Override
				public void run() {
					while(true) {
						// TODO Auto-generated method stub
						try {
							update_sql_covid_table();
							Thread.sleep(10000); // 갱신의 시간
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				}
    		});
    	update_sql_thread.start();
    }
}
