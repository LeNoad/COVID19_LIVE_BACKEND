package Backend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Time.dateTime;
import Backend.data_crawl.data_crawl;
import Backend.db_manager.db_manager;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

public class frame_set extends JFrame implements ActionListener {

	private JPanel frm_main;
	private JButton btn_open_covid;
	private JLabel lbl_logoimage;
	private JLabel lbl_time_thread;
	private dateTime date;
	private Thread date_display;
	private ImageIcon img;
	private JLabel lbl_text_thread;
	private JLabel lbl_text;
	private JLabel lbl_text_1;
	private JLabel lbl_Refreshtime;
	private JButton btn_refresh_update_sql;
	
	public static void main(String[] args) {
		db_manager db = new db_manager();
		db.thread_sql_covid_table();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame_set frame = new frame_set("COVID-19 LIVE");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public frame_set(String title) {
		date = new dateTime();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 161);
		setResizable(false);
		setTitle(title);
		
		frm_main = new JPanel();
		frm_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm_main.setLayout(null);
		setContentPane(frm_main);
		
		btn_open_covid = new JButton("상황판");
		btn_open_covid.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btn_open_covid.setBounds(229, 10, 105, 22);
		btn_open_covid.addActionListener(this);
		frm_main.add(btn_open_covid);

		img = new ImageIcon("image/open_01.jpg");
		lbl_logoimage = new JLabel(img);
		lbl_logoimage.setBounds(229, 68, 105, 38);
		frm_main.add(lbl_logoimage);
		
		lbl_text = new JLabel("현재시간");
		lbl_text.setForeground(Color.PINK);
		lbl_text.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
		lbl_text.setBounds(12, 10, 87, 22);
		frm_main.add(lbl_text);
		
		lbl_text_1 = new JLabel("갱신시간");
		lbl_text_1.setForeground(Color.PINK);
		lbl_text_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lbl_text_1.setBounds(12, 52, 87, 22);
		frm_main.add(lbl_text_1);
		
		lbl_Refreshtime = new JLabel(date.dateTime());
		lbl_Refreshtime.setBounds(12, 68, 205, 32);
		lbl_Refreshtime.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
		frm_main.add(lbl_Refreshtime);
		
		btn_refresh_update_sql = new JButton("갱신");
		btn_refresh_update_sql.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btn_refresh_update_sql.setBounds(229, 36, 105, 22);
		btn_refresh_update_sql.addActionListener(this);
		frm_main.add(btn_refresh_update_sql);
		
		lbl_time_thread = new JLabel();
		date_display = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					date = new dateTime(); // 클래스로 제작해둔 시간 함수를 가져온다.
					lbl_time_thread.setText(date.dateTime()); // 시간 
					lbl_time_thread.setBounds(14, 34, 204, 15);
					lbl_time_thread.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
					frm_main.add(lbl_time_thread);
				
					try {
						lbl_time_thread.repaint();
						Thread.sleep(1000); // 1초단위 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		});
		date_display.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == btn_open_covid) {
			JOptionPane.showMessageDialog(null, "상황판은 아직 미구현입니다.");
			dispose();
		}
		if(obj == btn_refresh_update_sql) {
			new db_manager().update_sql_covid_table();
			date = new dateTime();
			lbl_Refreshtime.setText(date.dateTime());
		}
	}
}
