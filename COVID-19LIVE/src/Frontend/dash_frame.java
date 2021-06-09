package Frontend;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Frontend.db_manager.db_manager;
import Frontend.graph.covid_graph;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class dash_frame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ImageIcon img;
	private JMenuItem mntmNewMenuItem;
	private ImageIcon img1;
	
	public dash_frame(String title) {
		db_manager db = new db_manager();
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 611);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("정보");
		mnNewMenu.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("학생 정보");
		mntmNewMenuItem.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JPanel panel_covid_map = new JPanel();
		panel_covid_map.setBounds(421, 206, 410, 334);
		contentPane.add(panel_covid_map);
		panel_covid_map.setLayout(null);
		
		img = new ImageIcon("image/map.jpg");
		JLabel lbl_map = new JLabel(img);
		
		
		lbl_map.setBounds(0, 0, 410, 334);
		panel_covid_map.add(lbl_map);
		
		JLabel map_gangwondo = new JLabel(db.select_sql_map(9));
		lbl_map.add(map_gangwondo);
		map_gangwondo.setBounds(245, 63, 57, 15);
		
		JLabel map_seoul = new JLabel(db.select_sql_map(0));
		lbl_map.add(map_seoul);
		map_seoul.setBounds(128, 71, 57, 15);
		
		JLabel map_gyeonggido = new JLabel(db.select_sql_map(8));
		lbl_map.add(map_gyeonggido);
		map_gyeonggido.setBounds(156, 96, 57, 15);
		
		JLabel map_incheon = new JLabel(db.select_sql_map(3));
		lbl_map.add(map_incheon);
		map_incheon.setBounds(59, 81, 57, 15);
		
		JLabel map_jeonbuk = new JLabel(db.select_sql_map(12));
		lbl_map.add(map_jeonbuk);
		map_jeonbuk.setBounds(128, 179, 57, 15);
		
		JLabel map_jeju = new JLabel(db.select_sql_map(16));
		lbl_map.add(map_jeju);
		map_jeju.setBounds(87, 288, 57, 15);
		
		JLabel map_gyeongbuk = new JLabel(db.select_sql_map(14));
		lbl_map.add(map_gyeongbuk);
		map_gyeongbuk.setBounds(277, 132, 57, 15);
		
		JLabel map_daejeon = new JLabel(db.select_sql_map(5));
		lbl_map.add(map_daejeon);
		map_daejeon.setBounds(171, 144, 57, 15);
		
		JLabel map_chungbuk = new JLabel(db.select_sql_map(10));
		lbl_map.add(map_chungbuk);
		map_chungbuk.setBounds(207, 117, 57, 15);
		
		JLabel map_chungnam = new JLabel(db.select_sql_map(11));
		lbl_map.add(map_chungnam);
		map_chungnam.setBounds(113, 130, 57, 15);
		
		JLabel map_gwangju = new JLabel(db.select_sql_map(4));
		lbl_map.add(map_gwangju);
		map_gwangju.setBounds(113, 210, 57, 15);
		
		JLabel map_gyeongnam = new JLabel(db.select_sql_map(15));
		lbl_map.add(map_gyeongnam);
		map_gyeongnam.setBounds(229, 210, 57, 15);
		
		JLabel map_ulsan = new JLabel(db.select_sql_map(6));
		lbl_map.add(map_ulsan);
		map_ulsan.setBounds(325, 191, 57, 15);
		
		JLabel map_daegu = new JLabel(db.select_sql_map(2));
		lbl_map.add(map_daegu);
		map_daegu.setBounds(271, 175, 57, 15);
		
		JLabel map_busan = new JLabel(db.select_sql_map(1));
		lbl_map.add(map_busan);
		map_busan.setBounds(312, 229, 57, 15);
		
		JLabel map_jeonnam = new JLabel(db.select_sql_map(13));
		lbl_map.add(map_jeonnam);
		map_jeonnam.setBounds(133, 239, 57, 15);
		
		JLabel map_sejong = new JLabel(db.select_sql_map(7));
		lbl_map.add(map_sejong);
		map_sejong.setBounds(156, 130, 57, 15);
		
		JPanel panel_covid_data = new JPanel();
		panel_covid_data.setBounds(12, 10, 819, 157);
		contentPane.add(panel_covid_data);
		panel_covid_data.setLayout(null);
		
		JLabel lbl_txt_recov = new JLabel("확진자");
		lbl_txt_recov.setForeground(new Color(0, 0, 0));
		lbl_txt_recov.setFont(new Font("HY견고딕", Font.BOLD, 30));
		lbl_txt_recov.setBounds(3, 5, 273, 82);
//		lbl_txt_recov.setBorder(new LineBorder(Color.BLUE,5,true));
		lbl_txt_recov.setBorder(new LineBorder(Color.white,2,true));
	
		panel_covid_data.add(lbl_txt_recov);
		lbl_txt_recov.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_txt_recov.setOpaque(true);
		lbl_txt_recov.setBackground(new Color(153, 204, 204));
		
		JLabel lbl_txt_comfirmed = new JLabel("완치자");
		lbl_txt_comfirmed.setForeground(new Color(0, 0, 0));
		lbl_txt_comfirmed.setFont(new Font("HY견고딕", Font.BOLD, 30));
		lbl_txt_comfirmed.setBounds(276, 5, 273, 82);
		panel_covid_data.add(lbl_txt_comfirmed);
		lbl_txt_comfirmed.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_txt_comfirmed.setOpaque(true);
		lbl_txt_comfirmed.setBackground(new Color(153, 153, 51));
		lbl_txt_comfirmed.setBorder(new LineBorder(Color.white,2,true));
		
		JLabel lbl_txt_death = new JLabel("사망자");
		lbl_txt_death.setForeground(new Color(0, 0, 0));
		lbl_txt_death.setFont(new Font("HY견고딕", Font.BOLD, 30));
		lbl_txt_death.setBounds(549, 5, 273, 82);
		panel_covid_data.add(lbl_txt_death);
		lbl_txt_death.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_txt_death.setOpaque(true);
		lbl_txt_death.setBackground(new Color(102, 153, 255));
		lbl_txt_death.setBorder(new LineBorder(Color.white,2,true));
		
		JLabel lbl_comfirmed = new JLabel(db.select_sql_label(2)+" 명");
		lbl_comfirmed.setFont(new Font("HY그래픽M", Font.PLAIN, 30));
		lbl_comfirmed.setBounds(3, 87, 273, 70);
		lbl_comfirmed.setForeground(Color.red);
		lbl_comfirmed.setBackground(new Color(	255 ,255 ,255));
		lbl_comfirmed.setOpaque(true);
		panel_covid_data.add(lbl_comfirmed);
		lbl_comfirmed.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl_recov = new JLabel(db.select_sql_label(3)+" 명");
		lbl_recov.setFont(new Font("HY그래픽M", Font.PLAIN, 30));
		lbl_recov.setBounds(276, 87, 273, 70);
		panel_covid_data.add(lbl_recov);
		lbl_recov.setBackground(new Color(	255 ,255 ,255));
		lbl_recov.setOpaque(true);
		lbl_recov.setForeground(Color.blue);
		lbl_recov.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl_death = new JLabel(db.select_sql_label(4)+" 명");
		lbl_death.setBounds(545, 87, 274, 70);
		panel_covid_data.add(lbl_death);
		lbl_death.setBackground(new Color(	255 ,255 ,255));
		lbl_death.setOpaque(true);
		lbl_death.setFont(new Font("HY그래픽M", Font.PLAIN, 30));
		lbl_death.setForeground(Color.MAGENTA);
		lbl_death.setHorizontalAlignment(SwingConstants.CENTER);
		
		covid_graph graph = new covid_graph();
		graph.setBounds(12, 206, 410, 334);
		
		contentPane.add(graph);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == mntmNewMenuItem ) {
			JOptionPane.showMessageDialog(this, "202045087_공윤재 \n200243151_김강준 \n202045071_공연성", "학생정보", JOptionPane.QUESTION_MESSAGE);
		}
	}
}
