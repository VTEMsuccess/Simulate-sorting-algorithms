package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setTitle("Hướng dẫn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
				home.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnTroVe.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnTroVe.setBounds(10, 10, 85, 21);
		contentPane.add(btnTroVe);
		
		JTextArea txtHuongDan = new JTextArea();
		txtHuongDan.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtHuongDan.setText("     Giới thiệu:\r\n\t- Phần mềm: Mô phỏng các thuật toán sắp xếp\r\n\t"
				+ "- Học phần: CT239 Niên luận cơ sở ngành KTPM\r\n\t- Học kỳ: 1 - Năm học: 2023 - 2024\r\n\t"
				+ "- GVHD: Trương Thị Thanh Tuyền\r\n\t- Nhóm: 01\r\n\t- Sinh viên thực hiện: Võ Thành Em B2012081\r\n     Hướng dẫn: \r\n\t"
				+ "- Nhập bằng tập tin:\r\n\t\t+ Người dùng có thể nhập tên file trực tiếp dạng đuôi *.txt ở thư mục //src//data// \r\n\t\t"
				+ "   để tìm.\r\n\t\t+ Có thể mở 1 file array.txt trên ứng dụng để nhập trực tiếp\r\n\t\t+ "
				+ "Có thể chọn 1 file dạng đuôi *.txt trên máy để mở\r\n\t\t+ Có thể xuất ra 1 file dạng *.txt. File xuất ở thư mục //src//data// \r\n\t\t"
				+ "*** Lưu ý: Các file được chọn, mở, xuất phải có dạng <tenfile>.txt\r\n\t- Nhập thủ công:\r\n\t\t+ Người dùng nhập lần lượng các giá trị của phần tử vào\r\n\t\t"
				+ "*** Lưu ý: Giá trị nhập vào từ 1 - 100. \r\n\t- Sinh ngẫu nhiên dữ liệu:\r\n\t\t+ Người dùng nhập số lượng phần tử muốn tạo ra, ứng dụng sẽ \r\n\t\t"
				+ "   khởi tạo giá trị ngẫu nhiên cho từng phần tử từ 1 - 100\r\n\t\t*** Lưu ý: Số lượng phần tử nhập vào phải nhỏ hơn 12.");
		txtHuongDan.setBounds(10, 37, 775, 468);
		contentPane.add(txtHuongDan);
	}
}
