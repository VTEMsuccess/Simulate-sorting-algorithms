package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import controller.ListenerInputRandom;
import controller.ListenerInputHands;
import model.Number;
import model.NumberPanel;
import view.InputRandom.SORTTYPE;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class InputHands extends JFrame {

	private JPanel contentPane;
	
	enum SORTTYPE {
		BUBBLE_SORT, SELECTION_SORT, INSERTION_SORT, QUICK_SORT, HEAP_SORT, INTERCHANGE_SORT, BUBBLE_SORT_REDUCE,
		SELECTION_SORT_REDUCE, INSERTION_SORT_REDUCE, QUICK_SORT_REDUCE, HEAP_SORT_REDUCE, INTERCHANGE_SORT_REDUCE
	};

	public static SORTTYPE sortType = SORTTYPE.BUBBLE_SORT;
	private JComboBox cboAlgorthm;

	Number myNumber = new Number();

	JTextField txtNumber = null;
	NumberPanel pnCenter = null;

	// Vị trí nhập vào
	private int position = 0;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void IncreasePosition() {
		this.position++;
	}
	public void ResetPosition() {
		this.position = 0;
	}
	public void drawControlHandsInput() {

		try {
			int num = Integer.parseInt(txtNumber.getText());
			if(num>=0 && num<=100) {
				System.out.println("Vừa nhập số: " + num);
				Number.COLOR = Color.BLACK;

				pnCenter.addNumberHandsInput(position, num);
				pnCenter.repaint();

				IncreasePosition();

				System.out.println("Đã tăng vị trí phần tử, hiện tại vị trí = " + position);
			}
			else {
				JOptionPane.showMessageDialog(pnCenter, "Vui lòng nhập giá trị từ 0 -> 100 !!" , null, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(pnCenter, "Vui lòng nhập số nguyên dương  từ 0 -> 100 !!" , null, JOptionPane.ERROR_MESSAGE);
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

	public void sortControlHandsInput() {
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
			RunableInputHands run = new RunableInputHands(pnCenter);
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
			RunableInputHands run = new RunableInputHands(pnCenter);
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
					InputHands frame = new InputHands();
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

	NumberPanel myNumberPanel = new NumberPanel();

	public InputHands() {

		ActionListener acl = new ListenerInputHands(this);

		setTitle("Nhập thủ công");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 429);
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
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nh\u1EADp li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNhapLieu.setBounds(10, 65, 238, 317);
		contentPane.add(panelNhapLieu);
		panelNhapLieu.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Nhập giá trị của phần tử");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 75, 203, 32);
		panelNhapLieu.add(lblNewLabel_3);

		txtNumber = new JTextField();
		txtNumber.setBounds(90, 117, 48, 19);
		panelNhapLieu.add(txtNumber);
		txtNumber.setColumns(10);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(acl);
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnThem.setBounds(75, 146, 85, 21);
		panelNhapLieu.add(btnThem);

		JButton btnDatLai = new JButton("Đặt lại");
		btnDatLai.addActionListener(acl);
		btnDatLai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDatLai.setBounds(75, 177, 85, 21);
		panelNhapLieu.add(btnDatLai);

		JPanel panelMoPhong = new JPanel();
		panelMoPhong.setBorder(
				new TitledBorder(null, "Mô phỏng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMoPhong.setBounds(258, 65, 507, 317);
		contentPane.add(panelMoPhong);
		panelMoPhong.setLayout(new BorderLayout(0, 0));

		pnCenter = new NumberPanel();
		panelMoPhong.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		JPanel panelTuyChon = new JPanel();
		panelTuyChon.setBorder(
				new TitledBorder(null, "Tùy chọn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTuyChon.setBounds(775, 65, 251, 317);
		contentPane.add(panelTuyChon);
		panelTuyChon.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thuật toán: ");
		lblNewLabel_1.setBounds(10, 31, 77, 28);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelTuyChon.add(lblNewLabel_1);

		cboAlgorthm = new JComboBox();
		cboAlgorthm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cboAlgorthm = new JComboBox(new String[] {"Bubble sort", "Selection sort", "Insertion sort", "Quick sort", "Heap sort", "Interchange sort"});
		cboAlgorthm.setBounds(97, 36, 114, 21);
		panelTuyChon.add(cboAlgorthm);

		JLabel lblNewLabel_2 = new JLabel("Sắp xếp: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 82, 66, 21);
		panelTuyChon.add(lblNewLabel_2);

		JRadioButton rdbtnTangDan = new JRadioButton("Tăng dần");
		rdbtnTangDan.addActionListener(acl);
		System.out.println("Bạn vừa chọn Tăng dần ở nhập từ bàn phím");
		rdbtnTangDan.setSelected(true);
		rdbtnTangDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnTangDan.setBounds(97, 82, 103, 21);
		panelTuyChon.add(rdbtnTangDan);

		JRadioButton rdbtnGiamDan = new JRadioButton("Giảm dần");
		rdbtnGiamDan.addActionListener(acl);
		rdbtnGiamDan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnGiamDan.setBounds(97, 114, 103, 21);
		panelTuyChon.add(rdbtnGiamDan);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnTangDan);
		bg.add(rdbtnGiamDan);

		JButton btnSapXep = new JButton("Sắp xếp");
		btnSapXep.addActionListener(acl);
		btnSapXep.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSapXep.setBounds(97, 164, 114, 21);
		panelTuyChon.add(btnSapXep);
	}
	// Tăng vị trí khi nhập từng số nguyên
	public int tangViTri() {
		System.out.println("NhapTay Vị trí: " + myNumber.getLocaltion());
		return this.myNumber.IncreaseLocaltion();
	}
	// Focus
	public void focusJtextFiled() {
		txtNumber.setText("");
		txtNumber.requestFocusInWindow(); // focus vào JTextField

	}
	// Đặt lại
	public void removeALLNumbers() {
		pnCenter.RemoveAllNumbers();
		pnCenter.repaint();
		ResetPosition();
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
