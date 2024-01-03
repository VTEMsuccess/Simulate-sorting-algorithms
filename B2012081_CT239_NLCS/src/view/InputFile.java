package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ListenerInputFile;
import model.Number;
import model.NumberPanel;
import view.InputFile.SORTTYPE;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class InputFile extends JFrame {
	private JPanel contentPane;

	enum SORTTYPE {
		BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, HEAP_SORT, INTERCHANGE_SORT, BUBBLE_SORT_REDUCE,
		SELECTION_SORT_REDUCE, INSERTION_SORT_REDUCE, QUICK_SORT_REDUCE, HEAP_SORT_REDUCE, INTERCHANGE_SORT_REDUCE
	};

	public static SORTTYPE sortType = SORTTYPE.BUBBLE_SORT;
	private JComboBox cboAlgorthm;

	NumberPanel pnCenter = null;

	private JTextArea textSoSanhHoanVi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputFile frame = new InputFile();
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
	public InputFile() {

		ActionListener acl = new ListenerInputFile(this);

		setTitle("Nhập File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeader = new JLabel("Mô phỏng các thuật toán sắp xếp");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeader.setBounds(258, 10, 507, 27);
		contentPane.add(lblHeader);

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

		JPanel panelNhapLieu = new JPanel();
		panelNhapLieu.setBorder(
				new TitledBorder(null, "Nhập liệu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNhapLieu.setBounds(10, 65, 238, 558);
		contentPane.add(panelNhapLieu);
		panelNhapLieu.setLayout(null);

		JButton btnMoFile = new JButton("Mở file");
		btnMoFile.addActionListener(acl);
		btnMoFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnMoFile.setBounds(62, 149, 103, 19);
		panelNhapLieu.add(btnMoFile);

		JButton btnDocFile = new JButton("Đọc file");

		btnDocFile.addActionListener(acl);
		btnDocFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDocFile.setBounds(62, 178, 103, 19);
		panelNhapLieu.add(btnDocFile);

		JButton btnDatLai = new JButton("Đặt lại");

		btnDatLai.addActionListener(acl);
		btnDatLai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDatLai.setBounds(62, 207, 103, 19);
		panelNhapLieu.add(btnDatLai);

		JLabel lblNhapTenFile = new JLabel("Nhập tên file");
		lblNhapTenFile.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNhapTenFile.setBounds(75, 25, 76, 27);
		panelNhapLieu.add(lblNhapTenFile);

		txtTenFile = new JTextField();
		txtTenFile.setBounds(62, 55, 103, 19);
		panelNhapLieu.add(txtTenFile);
		txtTenFile.setColumns(10);

		JButton btnNhap = new JButton("Nhập");

		btnNhap.addActionListener(acl);
		btnNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNhap.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNhap.setBounds(62, 84, 103, 21);
		panelNhapLieu.add(btnNhap);

		JLabel lblNewLabel_4 = new JLabel("Mở file nhập trực tiếp");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(51, 115, 129, 27);
		panelNhapLieu.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Chọn file trên máy");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(55, 236, 110, 19);
		panelNhapLieu.add(lblNewLabel_5);

		JButton btnChonFile = new JButton("Chọn file");
		btnChonFile.addActionListener(acl);

		btnChonFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnChonFile.setBounds(62, 265, 103, 21);
		panelNhapLieu.add(btnChonFile);

		JPanel panelMoPhong = new JPanel();
		panelMoPhong.setBorder(
				new TitledBorder(null, "Mô phỏng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMoPhong.setBounds(258, 65, 507, 317);
		contentPane.add(panelMoPhong);
		panelMoPhong.setLayout(new BorderLayout(0, 0));

		// Thêm hình
		pnCenter = new NumberPanel();
		panelMoPhong.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		JPanel panelTuyChon = new JPanel();
		panelTuyChon.setBorder(
				new TitledBorder(null, "Tùy chọn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTuyChon.setBounds(775, 65, 251, 554);
		contentPane.add(panelTuyChon);
		panelTuyChon.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thuật toán: ");
		lblNewLabel_1.setBounds(11, 23, 71, 18);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelTuyChon.add(lblNewLabel_1);

		cboAlgorthm = new JComboBox();
		cboAlgorthm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboAlgorthm = new JComboBox(new String[] { "Bubble sort", "Selection sort", "Insertion sort", "Quick sort",
				"Heap sort", "Interchange sort" });
		cboAlgorthm.setBounds(97, 36, 114, 21);
		panelTuyChon.add(cboAlgorthm);

		JLabel lblNewLabel_2 = new JLabel("Sắp xếp: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(11, 67, 56, 18);
		panelTuyChon.add(lblNewLabel_2);

		JRadioButton rdbtnTangDan = new JRadioButton("Tăng dần");
		System.out.println("Mặt định chọn tăng dần");
		rdbtnTangDan.addActionListener(acl);
		rdbtnTangDan.setSelected(true);
		rdbtnTangDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnTangDan.setBounds(107, 63, 81, 27);
		panelTuyChon.add(rdbtnTangDan);

		JRadioButton rdbtnGiamDan = new JRadioButton("Giảm dần");
		rdbtnGiamDan.addActionListener(acl);
		rdbtnGiamDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnGiamDan.setBounds(107, 96, 83, 27);
		panelTuyChon.add(rdbtnGiamDan);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTangDan);
		bg.add(rdbtnGiamDan);

		JButton btnSapXep = new JButton("Sắp xếp");
		btnSapXep.addActionListener(acl);
		btnSapXep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSapXep.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSapXep.setBounds(107, 140, 112, 27);
		panelTuyChon.add(btnSapXep);

		JButton btnXuatFile = new JButton("Xuất file");
		btnXuatFile.addActionListener(acl);
		btnXuatFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXuatFile.setBounds(107, 207, 112, 27);
		panelTuyChon.add(btnXuatFile);

		JLabel lblNewLabel = new JLabel("Xuất dữ liệu trong khung mô phỏng");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(11, 177, 230, 19);
		panelTuyChon.add(lblNewLabel);

		JPanel panelSoSanh = new JPanel();
		panelSoSanh.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Xem s\u1ED1 l\u1EA7n ho\u00E1n v\u1ECB", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSoSanh.setBounds(258, 380, 507, 243);
		contentPane.add(panelSoSanh);
		panelSoSanh.setLayout(null);

		JButton btnXemHoanVi = new JButton("Xem");
		btnXemHoanVi.addActionListener(acl);
		btnXemHoanVi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnXemHoanVi.setBounds(10, 200, 69, 21);
		panelSoSanh.add(btnXemHoanVi);

		// new hiển thị số lần hoán vị 22/11/2023
		textSoSanhHoanVi = new JTextArea();
		textSoSanhHoanVi.setEditable(false);
		textSoSanhHoanVi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textSoSanhHoanVi.setBounds(10, 22, 487, 168);
		panelSoSanh.add(textSoSanhHoanVi);

		JButton btnXoaAllSoLanHoanVi = new JButton("Xóa tất cả");
		btnXoaAllSoLanHoanVi.addActionListener(acl);
		btnXoaAllSoLanHoanVi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXoaAllSoLanHoanVi.setBounds(384, 200, 103, 21);
		panelSoSanh.add(btnXoaAllSoLanHoanVi);

	}

	// Vị trí phần tử trong tập tin cách nhau bởi xuống hàng
	private int position = 0;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		position = position;
	}

	public void IncreasePosition() {
		this.position++;
	}

	public void ResetPosition() {
		this.position = 0;
	}

// mở file mặc định là array.txt
	
	private File file = new File("src//data//array.txt");
	public void OpenFile() {
		try {
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			desktop.open(file);
		} catch (IOException ioe) {
			// file isn't existed
			ioe.printStackTrace();
		}
	}

	// Mảng dùng để đọc file
	private int[] array;
	
	public void ReadFile() {
		try {
			Scanner in = new Scanner(file);
			// New 21/11/2023
			array = new int[12]; // Khởi tạo mảng với 12 phần tử
			int pos = 0;
			while (in.hasNextLine()) {
				array[pos] = Integer.parseInt(in.nextLine());

				Number.COLOR = Color.BLACK;
				pnCenter.addNumberFileInput(position, array[pos]);
				pnCenter.repaint();

				System.out.println("array[pos]: " + array[pos]);
				IncreasePosition();
				pos++;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTextField txtTenFile;
	private String ten = new String();
	private File tenFileNhap;

	public void InputNameFile() {
		ten = txtTenFile.getText();
		System.out.println("Vừa nhâp file: " + ten);
		tenFileNhap = new File("src//data//" + ten);
		ReadChooseFile(tenFileNhap);
	}

	public void RemoveAll() {
		pnCenter.RemoveAllNumbers();
		pnCenter.repaint();
		ResetPosition();
	}

	public void ReadChooseFile(File f) {
		try {
			Scanner in = new Scanner(f);
			array = new int[12]; // giới hạn tối 12 phần tử
			int pos = 0;
			while (in.hasNextLine()) {
				array[pos] = Integer.parseInt(in.nextLine());

				Number.COLOR = Color.BLACK;
				pnCenter.addNumberFileInput(position, array[pos]);
				pnCenter.repaint();

				System.out.println("array[pos]: " + array[pos]);
				IncreasePosition();
				pos++;
			}
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(pnCenter, "Vui lòng nhập đúng định dạng <tenfile.txt> ở thư mục //src//data !!" , null, JOptionPane.ERROR_MESSAGE);
		}
	}
	// Chọn file để mở
	public void ChooseFile() {
		JFileChooser jFileChooser = new JFileChooser();
		FileNameExtensionFilter filetxt = new FileNameExtensionFilter("File đuôi .txt", "txt"); // Lọc file .txt
		jFileChooser.setFileFilter(filetxt);
		jFileChooser.setMultiSelectionEnabled(false); // Cho phép chọn 1 file hay nhiều file cùng lúc

		int x = jFileChooser.showDialog(this, "Chọn file"); // Hiển hị cho ng dùng chọn
		if (x == jFileChooser.APPROVE_OPTION) { // APPROVE_OPTION -> Ng dùng đã chọn file
			File f = jFileChooser.getSelectedFile();
			ReadChooseFile(f);
		}
	}

	// Lựa chọn tăng dần mặt định
	private boolean increase = true;

	public boolean isIncrease() {
		return increase;
	}

	public void setIncrease(boolean increase) {
		this.increase = increase;
	}

	public void sortControlFileInput() {
		if (isIncrease() == true) {
			int n = cboAlgorthm.getSelectedIndex();
			switch (n) {
			case 0: {
				sortType = SORTTYPE.BUBBLE_SORT;
				break;
			}
			case 1: {
				sortType = SORTTYPE.SELECTION_SORT;
				break;
			}
			case 2: {
				sortType = SORTTYPE.INSERTION_SORT;
				break;
			}
			case 3: {
				sortType = SORTTYPE.QUICK_SORT;
				break;
			}
			case 4: {
				sortType = SORTTYPE.HEAP_SORT;
				break;
			}
			case 5: {
				sortType = SORTTYPE.INTERCHANGE_SORT;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + n);
			}

			System.out.println("Đã chọn thuật toán: " + n + " Tăng dần");
			RunableInputFile run = new RunableInputFile(pnCenter);
			Thread th = new Thread(run);
			th.start();
		} else if (isIncrease() == false) {
			System.out.println("Đang chạy GIẢM DẦN");
			int n = cboAlgorthm.getSelectedIndex();
			switch (n) {
			case 0: {
				sortType = SORTTYPE.BUBBLE_SORT_REDUCE;
				break;
			}
			case 1: {
				sortType = SORTTYPE.SELECTION_SORT_REDUCE;
				break;
			}
			case 2: {
				sortType = SORTTYPE.INSERTION_SORT_REDUCE;
				break;
			}
			case 3: {
				sortType = SORTTYPE.QUICK_SORT_REDUCE;
				break;
			}
			case 4: {
				sortType = SORTTYPE.HEAP_SORT_REDUCE;
				break;
			}
			case 5: {
				sortType = SORTTYPE.INTERCHANGE_SORT_REDUCE;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + n);
			}

			System.out.println("Đã chọn thuật toán: " + n + " Giảm dần");
			RunableInputFile run = new RunableInputFile(pnCenter);
			Thread th = new Thread(run);
			th.start();
		}
	}

	public void Increase() {
		setIncrease(true);
		System.out.println("Giá trị của tangDan: " + isIncrease());
	}

	public void Reduce() {
		setIncrease(false);
		System.out.println("Giá trị của tangDan: " + isIncrease());
	}
	
	// Hàm xuất file khi nhấn nút "Xuất file"
	public void ExportFile() {
		pnCenter.ExportFile();
	}
	
	// Reset số lần hoán vị
	public void ResetSwap() {
		pnCenter.DeleteCount();
	}

	public void getNameAlgorithm() {
		if (pnCenter.isFinished() == true) {
			textSoSanhHoanVi.append(" - " + pnCenter.getName() + " số lần hoán vị là: " + pnCenter.getCount() + "\n");
		}
	}

	public void getDataSwap() {
		getNameAlgorithm();
	}
	
	public void resetDataSwap() {
		textSoSanhHoanVi.setText("");
	}
}
