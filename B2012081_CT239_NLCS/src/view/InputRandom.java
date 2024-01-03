package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.ListenerInputRandom;
import model.Number;
import model.NumberPanel;
import view.InputRandom.SORTTYPE;

import javax.swing.JTextField;

public class InputRandom extends JFrame {

	NumberPanel myNumberPanel = new NumberPanel();
	private JPanel contentPane;
	private JTextField textField;

	enum SORTTYPE {
		BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, HEAP_SORT, INTERCHANGE_SORT, BUBBLE_SORT_REDUCE,
		SELECTION_SORT_REDUCE, INSERTION_SORT_REDUCE, QUICK_SORT_REDUCE, HEAP_SORT_REDUCE, INTERCHANGE_SORT_REDUCE
	};

	public static SORTTYPE sortType = SORTTYPE.BUBBLE_SORT;
	private JComboBox cboAlgorthm;
	private Number myNumber;

	JTextField txtNumber = null;
	NumberPanel pnCenter = null;
	TextArea textArea = null;

	static JRadioButton rdbtnTangDan;
	private JRadioButton rdbtnGiamDan;

	// Random
	public void drawControlRandom() {
		try {
			int num = Integer.parseInt(txtNumber.getText());
			if(num >=2 && num <=12) { 
				Number.COLOR = Color.BLACK;
				pnCenter.addNumbersRandomInput(num);
				pnCenter.repaint();
			}
			else {
				JOptionPane.showMessageDialog(pnCenter, "Vui lòng số phần tử từ 2 -> 12" , null, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(pnCenter, "Vui lòng nhập số nguyên dương giá trị từ 1 -> 12 !!" , null, JOptionPane.ERROR_MESSAGE);
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

	public void sortControlRandom() {
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
			RunableInputRandom run = new RunableInputRandom(pnCenter);
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
			RunableInputRandom run = new RunableInputRandom(pnCenter);
			Thread th = new Thread(run);
			th.start();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputRandom frame = new InputRandom();
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
	public InputRandom() {

		ActionListener acl = new ListenerInputRandom(this);

		setTitle("Random");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 429);
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
		panelNhapLieu.setBounds(10, 65, 238, 317);
		contentPane.add(panelNhapLieu);
		panelNhapLieu.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Nhập số lượng phần tử muốn tạo ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 87, 218, 31);
		panelNhapLieu.add(lblNewLabel_3);

		txtNumber = new JTextField();
		txtNumber.setBounds(96, 116, 42, 19);
		panelNhapLieu.add(txtNumber);
		txtNumber.setColumns(10);

		JButton btnVe = new JButton("Vẽ"); //xác nhận tạo mảng vẽ ra các phần tử
		btnVe.addActionListener(acl);
		btnVe.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnVe.setBounds(77, 145, 85, 21);
		panelNhapLieu.add(btnVe);

		JButton btnDatLai = new JButton("Đặt lại");
		btnDatLai.addActionListener(acl);
		btnDatLai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDatLai.setBounds(77, 176, 85, 21);
		panelNhapLieu.add(btnDatLai);

		JPanel panelMoPhong = new JPanel();
		panelMoPhong.setBorder(
				new TitledBorder(null, "Mô phỏng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMoPhong.setBounds(258, 65, 523, 317);
		contentPane.add(panelMoPhong);
		panelMoPhong.setLayout(new BorderLayout(0, 0));

		pnCenter = new NumberPanel();
		panelMoPhong.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		pnCenter.add(panel_3, BorderLayout.NORTH);

		JPanel panelTuyChon = new JPanel();
		panelTuyChon.setBorder(
				new TitledBorder(null, "Tùy chọn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTuyChon.setBounds(791, 65, 251, 317);
		contentPane.add(panelTuyChon);
		panelTuyChon.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thuật toán: ");
		lblNewLabel_1.setBounds(10, 31, 77, 28);
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
		lblNewLabel_2.setBounds(10, 82, 66, 21);
		panelTuyChon.add(lblNewLabel_2);

		rdbtnTangDan = new JRadioButton("Tăng dần");
		rdbtnTangDan.addActionListener(acl);
		System.out.println("Bạn vừa chọn Tăng dần ở random");

		rdbtnTangDan.setSelected(true);
		rdbtnTangDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnTangDan.setBounds(97, 82, 103, 21);
		panelTuyChon.add(rdbtnTangDan);

		rdbtnGiamDan = new JRadioButton("Giảm dần");
		rdbtnGiamDan.addActionListener(acl);
		rdbtnGiamDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnGiamDan.setBounds(97, 114, 103, 21);
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
		btnSapXep.setBounds(97, 164, 114, 21);
		panelTuyChon.add(btnSapXep);
	}

	public void RemoveAllNumbers() {
		pnCenter.RemoveAllNumbers();
		pnCenter.repaint();
		txtNumber.setText("");
		txtNumber.requestFocusInWindow(); // focus vào JTextField
	}

	public void Increase() {
		setIncrease(true);
		System.out.println("Giá trị của tangDan: " + isIncrease());
	}

	public void Reduce() {
		setIncrease(false);
		System.out.println("Giá trị của tangDan: " + isIncrease());
	}
	
	// Reset số lần hoán vị
	public void ResetSwap() {
		pnCenter.DeleteCount();
	}

}
