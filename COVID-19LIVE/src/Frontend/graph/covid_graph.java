package Frontend.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import Frontend.Time.dateTime;
import Frontend.db_manager.db_manager;


public class covid_graph extends JPanel {
	private int one_covid_graph, two_covid_graph, three_covid_graph, four_covid_graph, five_covid_graph;
	private dateTime date;
	public void paint(Graphics g) {
		 date = new dateTime();
		 g.clearRect(0,0,getWidth(),getHeight());
		 g.setFont(new Font("", Font.PLAIN, 15)); 
		 g.drawLine(50,250,350,250);
		 db_manager db = new db_manager();
		 for(int cnt = 1 ;cnt<11;cnt++)
		 {
			g.drawString(cnt *100 +"",15,255-20*cnt);
			g.drawLine(50, 250-20*cnt, 350,250-20*cnt);
		 }
		    g.drawLine(50,20,50,250);
		    
		    g.drawString(date.date(4).replaceAll("2021-", ""),50,270);
		    g.drawString(date.date(3).replaceAll("2021-", ""),115,270);
		    g.drawString(date.date(2).replaceAll("2021-", ""),175,270);
		    g.drawString(date.date(1).replaceAll("2021-", ""),230,270);
		    g.drawString(date.date(0).replaceAll("2021-", ""),295,270);
		    
		    g.setColor(Color.BLUE);
		    setScores(Integer.parseInt(db.select_sql_graph(4)),Integer.parseInt(db.select_sql_graph(3)), Integer.parseInt(db.select_sql_graph(2)),
		    		Integer.parseInt(db.select_sql_graph(1)), Integer.parseInt(db.select_sql_graph(0)));
		    
		    if(one_covid_graph>0) {
		    	g.setColor(randomColor());
		        g.fillRect(65,250-one_covid_graph/5,10,one_covid_graph/5);
		        g.setColor(Color.BLACK);
		    	g.drawString(db.select_sql_graph(4), 60, 240-one_covid_graph/5);
		    }
		    if(two_covid_graph>0) {
		    	g.setColor(randomColor());
		        g.fillRect(125,250-two_covid_graph/5,10,two_covid_graph/5);
		        g.setColor(Color.BLACK);
		    	g.drawString(db.select_sql_graph(3), 120, 240-two_covid_graph/5);
		    }
		    if(three_covid_graph>0) {
		    	g.setColor(randomColor());
		        g.fillRect(185,250-three_covid_graph/5,10,three_covid_graph/5);
		        g.setColor(Color.BLACK);
		    	g.drawString(db.select_sql_graph(2), 180, 240-three_covid_graph/5);
		    }
		    if(four_covid_graph>0) {
		    	g.setColor(randomColor());
		        g.fillRect(245,250-four_covid_graph/5,10,four_covid_graph/5);
		        g.setColor(Color.BLACK);
		    	g.drawString(db.select_sql_graph(1), 240, 240-four_covid_graph/5);
		    }
		    if(five_covid_graph>0) { 
		    	g.setColor(randomColor());
		        g.fillRect(305,250-five_covid_graph/5,10,five_covid_graph/5);
		        g.setColor(Color.BLACK);
		        g.drawString(db.select_sql_graph(0), 300, 240-five_covid_graph/5);
		    }
	}

	public void setScores(int one_covid_graph, int two_covid_graph, int three_covid_graph,int four_covid_graph ,int five_covid_graph) {
    		this.one_covid_graph=one_covid_graph;
    		this.two_covid_graph=two_covid_graph;
    		this.three_covid_graph=three_covid_graph;
    		this.four_covid_graph=four_covid_graph;
    		this.five_covid_graph=five_covid_graph;
	}
	
	public Color randomColor() {
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color randomColor = new Color(red,green,blue);
		return randomColor;
	}
}
