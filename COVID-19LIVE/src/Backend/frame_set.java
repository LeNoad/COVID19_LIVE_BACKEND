package Backend;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Time.dateTime;
import Backend.data_crawl.data_crawl;
import Backend.db_manager.db_manager;
import Frontend.dash_frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class frame_set extends JFrame implements ActionListener {

	private JPanel frm_main;
	
	private JLabel lbl_logoimage;
	private JLabel lbl_time_thread;
	private JLabel lbl_text_thread;
	private JLabel lbl_text;
	private JLabel lbl_text_1;
	private JLabel lbl_Refreshtime;
	
	private dateTime date;
	private Thread date_display;
	private ImageIcon img;
	
	
	private JButton btn_open_covid;
	private JButton btn_refresh_update_sql;
	private JButton btn_tray;
	
	private dash_frame dash;
	
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
		setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\git\\COVID19_LIVE_BACKEND\\COVID-19LIVE\\image\\icon.jpg"));
		date = new dateTime();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 161);
		setResizable(false);
		setTitle(title);
		
		frm_main = new JPanel();
		frm_main.setBackground(Color.GRAY);
		frm_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm_main.setLayout(null);
		setContentPane(frm_main);
		
		btn_open_covid = new JButton("Dash");
		btn_open_covid.setBackground(Color.BLACK);
		btn_open_covid.setForeground(Color.CYAN);
		btn_open_covid.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		btn_open_covid.setBounds(214, 11, 63, 22);
		btn_open_covid.addActionListener(this);
		frm_main.add(btn_open_covid);

		img = new ImageIcon("image/icon.jpg");
		lbl_logoimage = new JLabel(img);
		lbl_logoimage.setBounds(253, 68, 50, 50);
		frm_main.add(lbl_logoimage);
		
		lbl_text = new JLabel("현재시간");
		lbl_text.setForeground(Color.BLACK);
		lbl_text.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
		lbl_text.setBounds(12, 10, 87, 22);
		frm_main.add(lbl_text);
		
		lbl_text_1 = new JLabel("갱신시간");
		lbl_text_1.setForeground(Color.BLACK);
		lbl_text_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lbl_text_1.setBounds(12, 52, 87, 22);
		frm_main.add(lbl_text_1);
		
		lbl_Refreshtime = new JLabel(date.dateTime());
		lbl_Refreshtime.setForeground(Color.RED);
		lbl_Refreshtime.setBounds(12, 68, 205, 32);
		lbl_Refreshtime.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));
		frm_main.add(lbl_Refreshtime);
		
		btn_refresh_update_sql = new JButton("Refresh");
		btn_refresh_update_sql.setForeground(Color.CYAN);
		btn_refresh_update_sql.setBackground(Color.BLACK);
		btn_refresh_update_sql.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		btn_refresh_update_sql.setBounds(214, 36, 129, 22);
		btn_refresh_update_sql.addActionListener(this);
		frm_main.add(btn_refresh_update_sql);
		
		btn_tray = new JButton("Tray");
		btn_tray.setBackground(Color.BLACK);
		btn_tray.setForeground(Color.CYAN);
		btn_tray.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		btn_tray.setBounds(281, 11, 63, 22);
		btn_tray.addActionListener(this);
		frm_main.add(btn_tray);
		
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
					lbl_time_thread.setForeground(Color.ORANGE);
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
			dash = new dash_frame("COVID19-LIVE");
			System.out.println("열려있습니다.");
		}
		if(obj == btn_refresh_update_sql) {
			new db_manager().update_sql_covid_table();
			date = new dateTime();
			lbl_Refreshtime.setText(date.dateTime());
		}
		if(obj == btn_tray) {
			makeTray();
		}
	}
	
	public void makeTray() {
		setVisible(false);
		
	    MenuItem exititem = new MenuItem("Exit");
	    MenuItem openitem = new MenuItem("Open");
	    MenuItem aboutitem = new MenuItem("About me");
	    PopupMenu menu = new PopupMenu();
	    
	    menu.add(aboutitem);
	    aboutitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "202045087_공윤재 \n200243151_김강준 \n202045071_공연성");
			}
		});
	    menu.add(openitem);
	    openitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(true);
			}
		});
	    menu.add(exititem);
	    exititem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(1);
	        }
	    });
	    TrayIcon trayicon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("image/icon.jpg"), "COVID19-LIVE", menu);
	    trayicon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(true);
			}
		});
	    SystemTray systemtray = SystemTray.getSystemTray();
	    
	    try {
	        systemtray.add(trayicon);
	    } catch (AWTException e) {
	        System.out.println(e.getMessage());
	    }
	    trayicon.setImageAutoSize(true);
	}
}
