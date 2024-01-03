package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.swing.border.LineBorder;

import com.sun.tools.javac.Main;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("Trang chủ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 816, 45);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHeader = new JLabel("Mô phỏng các thuật toán sắp xếp");
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(lblHeader);
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setVerticalAlignment(SwingConstants.BOTTOM);
//thêm ảnh 
//		lblAvatar.setIcon(new ImageIcon("C:\\Users\\Thanh Em\\eclipse-workspace\\B2012081_CT239_NLCS\\src\\data\\avt2.png"));
		lblAvatar.setBounds(150, 66, 826, 261);  // avt 10, 56, 826, 261
		contentPane.add(lblAvatar);
		
		JLabel lblHeader_2 = new JLabel("Chọn phương thức nhập");
		lblHeader_2.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblHeader_2.setBounds(276, 327, 333, 45); 
		contentPane.add(lblHeader_2);
		
		JButton btnNhapFile = new JButton("Nhập bằng tập tin");
		btnNhapFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputFile nhapFile = new InputFile();
				nhapFile.setVisible(true);
				nhapFile.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNhapFile.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNhapFile.setBounds(169, 373, 230, 33);
		contentPane.add(btnNhapFile);
		
		JButton btnNhapTay = new JButton("Nhập thủ công");
		btnNhapTay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputHands nhapTay = new InputHands();
				nhapTay.setVisible(true);
				nhapTay.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNhapTay.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNhapTay.setBounds(169, 435, 230, 33);
		contentPane.add(btnNhapTay);
		
		JButton btnRandom = new JButton("Sinh ngẫu nhiên dữ liệu");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputRandom rd = new InputRandom();
				rd.setVisible(true);
				rd.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnRandom.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnRandom.setBounds(435, 373, 247, 33);
		contentPane.add(btnRandom);
		
		JButton btnHuongDan = new JButton("Hướng dẫn");
		btnHuongDan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					About hd = new About();
					hd.setVisible(true);
					hd.setLocationRelativeTo(null);
					dispose();
				}
		});
		btnHuongDan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnHuongDan.setBounds(435, 436, 247, 31);
		contentPane.add(btnHuongDan);
		
	}
}
