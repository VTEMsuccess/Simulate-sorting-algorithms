package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.InputFile;
import view.InputHands;
import view.InputRandom;

public class NumberPanel extends JPanel {

	
	private boolean isFinished = false;

	Number mNumber = new Number();

	// Khai báo mảng động ArrayList
	ArrayList<Number> arr = new ArrayList<>();
	
	// Hàm xuất file tại thư mục //data
	public void ExportFile() {
		String tenFile = JOptionPane.showInputDialog(this, "Nhập tên file", "Xuất file", JOptionPane.DEFAULT_OPTION);
		try {
			if (tenFile !="" && arr.size() > 0) {
				File file = new File("src//data//" + tenFile);
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(file);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
				;
				for (Number number : arr) {
					System.out.println(number.getNum());
					pw.println(number.getNum());
				}
				pw.close();
				JOptionPane.showConfirmDialog(this, "Lưu file hành công", "Thành công", JOptionPane.CLOSED_OPTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Kiểm tra trạng thái
	public boolean isFinished() {
		return isFinished;
	}
	// Thêm số vào mảng arr
	public void addNumber(Number number) {
		arr.add(number);
	}

	// Hàm thêm giá trị vào vị trí hình ở chức năng Random
	public void addNumbersRandomInput(int num) {
		arr.clear();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			Number number = new Number(random.nextInt(100), getGraphics());
			number.setX(i * Number.YSIZE);
			number.setY(getBounds().getHeight() / 2 - Number.YSIZE);
			addNumber(number);
		}
	}

	// Hàm thêm giá trị vào vị trí hình ở chức năng Nhập thủ công
	public void addNumberHandsInput(int position, int num) {

		Number number = new Number(num, getGraphics());
		number.setX( position * Number.YSIZE);
		number.setY(getBounds().getHeight() / 2 - Number.YSIZE);
		addNumber(number);
	}

	public void RemoveAllNumbers() {
		arr.clear();
	}
	
	// Hàm thêm giá trị vào vị trí hình ở chức năng Nhập bằng file
	public void addNumberFileInput(int viTri, int num) {
		Number number = new Number(num, getGraphics());
		number.setX(viTri * Number.YSIZE);
		number.setY(getBounds().getHeight() / 2 - Number.YSIZE);
		addNumber(number);
	}

	// Ghi số lên hình
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < arr.size(); i++) {
			Number number = arr.get(i);
			number.setGraph(g2);
			number.draw();
		}
	}

	// Tạo hàm xuất ra vị trí hiện tại của giá trị
	public void printInFor() {
//		for (MyNumber number : arr) {
//			System.out.println(number.getNum() + "( x: " + number.getX() + " ,y: " + number.getY() + " )");
//		}
	}

	// Hàm sắp xếp vị trí hình
	public synchronized void moveControl(Number number1, Number number2) {

		printInFor();
		// Khai báo vị trí của hình
		double x1 = number1.getX();
		double y1 = number1.getY();
		double x2 = number2.getX();
		double y2 = number2.getY();

		try {
			// Dùng vòng lặp cho hình đi lên
			for (int k = 0; k < 50; k++) {
				number1.setY(y1 + k);
				number2.setY(y2 - k);
				Thread.sleep(10);
				// Cập nhật lại vị trí của hình
				repaint();
			}

			// Dùng vòng lặp cho hình đi ngang
			int k = 0;
			while (number1.getX() <= x2 - 1) {
				k++;
				number1.setX(x1 + k);
				number2.setX(x2 - k);
				Thread.sleep(10);
				repaint();
			}

			// Dùng vuòng lặp cho hình đi xuống
			y1 = number1.getY();
			y2 = number2.getY();
			for (k = 1; k < 50; k++) {
				number1.setY(y1 - k);
				number2.setY(y2 + k);
				Thread.sleep(10);
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	// Thông báo số lần hoán vị khi thực hiện xong sắp xếp
	public void Notify(String name) {
		JOptionPane.showMessageDialog(numberPanel,
				"Đã sắp xếp xong thuật toán " + name + "\n" + "Tổng số lần hoán vị là: " + getCount(),
				"Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	// Xem số lần hoán vị của Nhập file & Thông báo khi sắp xếp xong thuật toán
	private int count = 0;

	public void IncreaseCount() {
		this.count++;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void DeleteCount() {
		this.count = 0;
	}

	private NumberPanel numberPanel = null;
	
	// Tên thuật toán sắp xếp
	private String name = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Thuật toán sắp xếp boBubbleSort()
	public synchronized void doBubbleSort() {
		System.out.println("Đang chạy thuật toán doBubbleSort() Tăng của class Random ");
		name = "BubbleSort tăng dần";
		try {
			isFinished = false;
			System.out.println("Giá trị isFinished trước khi sắp xếp là " +isFinished);
			// Dùng vòng lặp lòng nhau để kiểm tra giá trị
			for (int i = 0; i <= arr.size() - 2; i++) {
				for (int j = arr.size() - 1; j >= i + 1; j--) {
					Number number1 = arr.get(j);
					Number number2 = arr.get(j - 1);

					if (number1.getNum() < number2.getNum()) {
						// Gọi hàm moveControl để thay đổi vị trí của hình
						moveControl(number2, number1);
						// Đếm số lần hoán vị
						IncreaseCount();
						System.out.println("---- Hoán vị lần: " + getCount());
						Number nt1 = number1;
						number1 = number2;
						number2 = nt1;

						arr.set(j, number1);
						arr.set(j - 1, number2);
					}
				}
			}
			isFinished = true;
			System.out.println("---- Hoàn thành doBubbleSort()  ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
			System.out.println("Giá trị isFinished sau khi sắp xếp là " +isFinished);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Thuật toán sắp xếp boBubbleSort()
	public synchronized void doBubbleSortReduce() {
		System.out.println("Đang chạy thuật toán doBubbleSort_Giam() của class Random ");
		name = "BubbleSort giảm dần";
		try {
			isFinished = false;
			// Dùng vòng lặp lòng nhau để kiểm tra giá trị
			for (int i = 0; i <= arr.size() - 2; i++) {
				for (int j = arr.size() - 1; j >= i + 1; j--) {
					Number number1 = arr.get(j);
					Number number2 = arr.get(j - 1);

					if (number1.getNum() > number2.getNum()) {
						moveControl(number2, number1);
						// Đếm số lần hoán vị
						IncreaseCount();
						Number nt1 = number1;
						number1 = number2;
						number2 = nt1;

						arr.set(j, number1);
						arr.set(j - 1, number2);
					}
				}

			}
			isFinished = true;
			System.out.println("---- Hoàn thành doBubbleSort_Giam()  ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Thuật toán sắp xếp doSelectionSort()
	public synchronized void doSelectionSort() {
		System.out.println("Đang chạy thuật toán doSelectionSort_Tang() của class Random ");
		name = "SelectionSort tăng dần";
		try {
			isFinished = false;
			// Duyệt từ 1 đến size -1
			for (int i = 0; i < arr.size() - 1; i++) {
				// Set min tại vị trí ban đầu
				int min_id = i;
				// Lặp từ vị trí i+1 đến size
				for (int j = i + 1; j < arr.size(); j++) {
					// Kiểm tra nếu
					if (arr.get(min_id).getNum() > arr.get(j).getNum()) {
						min_id = j;
					}
				}
				// Nếu min_id khác i thì thay đổi vị trí của hình
				if (min_id != i) {
					Number myNumber1 = arr.get(min_id);
					Number myNumber2 = arr.get(i);

					moveControl(myNumber2, myNumber1);
					// Đếm số lần hoán vị
					IncreaseCount();
					Number nt1 = myNumber1;
					myNumber1 = myNumber2;
					myNumber2 = nt1;
					
					arr.set(min_id, myNumber1);
					arr.set(i, myNumber2);
				}
			}
			isFinished = true;
			System.out.println("---- Hoàn thành doSelectionSort_Plus() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void doSelectionSortReduce() {
		System.out.println("Đang chạy thuật toán doSelectionSort_Giam() của class Random ");
		name = "SelectionSort giảm dần";
		try {
			isFinished = false;
			// Duyệt từ 1 đến size -1
			for (int i = 0; i < arr.size() - 1; i++) {
				// Set min tại vị trí ban đầu
				int min_id = i;
				// Lặp từ vị trí i+1 đến size
				for (int j = i + 1; j < arr.size(); j++) {
					// Kiểm tra nếu
					if (arr.get(min_id).getNum() < arr.get(j).getNum()) {
						min_id = j;
					}
				}
				// Nếu min_id khác i thì thay đổi vị trí của hình
				if (min_id != i) {
					Number myNumber1 = arr.get(min_id);
					Number myNumber2 = arr.get(i);
					moveControl(myNumber2, myNumber1);
					
					IncreaseCount();
					Number nt1 = myNumber1;
					myNumber1 = myNumber2;
					myNumber2 = nt1;
					
					arr.set(min_id, myNumber1);
					arr.set(i, myNumber2);
				}
			}
			isFinished = true;
			System.out.println("---- Hoàn thành doSelectionSort_Giam() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Thuật toán sắp xếp doInsertSort()
	public synchronized void doInsertionSort() {
		System.out.println("Đang chạy thuật toán doInsertionSort_Tang() của class Random ");
		name = "InsertionSort tăng dần";
		try {
			isFinished = false;
			for (int i = 1; i < arr.size(); i++) {
				int j = i - 1;
				while (j >= 0 && arr.get(j).getNum() > arr.get(j + 1).getNum()) {
					Number myNumber1 = arr.get(j);
					Number myNumber2 = arr.get(j + 1);
					moveControl(myNumber1, myNumber2);
					IncreaseCount();
					Number nt1 = myNumber1;
					myNumber1 = myNumber2;
					myNumber2 = nt1;
					
					arr.set(j, myNumber1);
					arr.set(j + 1, myNumber2);
					j--;
				}
			}
			isFinished = true;
			System.out.println("---- Hoàn thành doInsertSort_Tang() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void doInsertionSortReduce() {
		System.out.println("Đang chạy thuật toán doInsertionSort_Giam() của class Random ");
		String name = "InsertionSort giảm dần";
		try {
			isFinished = false;
			for (int i = 1; i < arr.size(); i++) {
				int j = i - 1;
				while (j >= 0 && arr.get(j).getNum() < arr.get(j + 1).getNum()) {
					Number myNumber1 = arr.get(j);
					Number myNumber2 = arr.get(j + 1);
					moveControl(myNumber1, myNumber2);
					IncreaseCount();
					Number nt1 = myNumber1;
					myNumber1 = myNumber2;
					myNumber2 = nt1;
					arr.set(j, myNumber1);
					arr.set(j + 1, myNumber2);
					j--;
				}
			}
			isFinished = true;
			System.out.println("---- Hoàn thành doInsertSort_Giam() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void doQuickSort() {
		System.out.println("Đang chạy thuật toán doQuickSort() của class Random ");
		name = "QuickSort tăng dần";
		try {
			isFinished = false;
			quickSort(arr, 0, arr.size() - 1);

			isFinished = true;
			System.out.println("---- Hoàn thành doQuickSort() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void quickSort(ArrayList<Number> arr, int low, int high) {
		if (low < high) {
			int pi = partittion(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}

	}

	private synchronized int partittion(ArrayList<Number> arr, int low, int high) {
		Number pivot = arr.get(high);
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr.get(j).getNum() < pivot.getNum()) {
				i++;
				Number myNumber1 = arr.get(i);
				Number myNumber2 = arr.get(j);
				if (myNumber1.getNum() != myNumber2.getNum()) {
					System.out.println(myNumber1.getNum() + " <--> " + myNumber2.getNum());
					moveControl(myNumber1, myNumber2);
					IncreaseCount();
					
					arr.set(i, myNumber2);
					arr.set(j, myNumber1);
				}
			}
		}
		Number myNumber1 = arr.get(i + 1);
		Number myNumber2 = arr.get(high);
		if (myNumber1.getNum() != myNumber2.getNum()) {
			moveControl(myNumber1, myNumber2);
			IncreaseCount();
			
			arr.set(i + 1, myNumber2);
			arr.set(high, myNumber1);
		}
		return i + 1;
	}

	public synchronized void doQuickSortReduce() {
		System.out.println("Đang chạy thuật toán doQuickSort() của class Random ");
		name = "QuickSort giảm dần";
		try {
			isFinished = false;
			quickSortReduce(arr, 0, arr.size() - 1);

			isFinished = true;
			System.out.println("---- Hoàn thành doQuickSort() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void quickSortReduce(ArrayList<Number> arr, int low, int high) {
		if (low < high) {
			int pi = partittionReduce(arr, low, high);
			quickSortReduce(arr, low, pi - 1);
			quickSortReduce(arr, pi + 1, high);
		}

	}

	private synchronized int partittionReduce(ArrayList<Number> arr, int low, int high) {
		Number pivot = arr.get(high);
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr.get(j).getNum() > pivot.getNum()) {
				i++;
				Number myNumber1 = arr.get(i);
				Number myNumber2 = arr.get(j);
				if (myNumber1.getNum() != myNumber2.getNum()) {
					System.out.println(myNumber1.getNum() + " <--> " + myNumber2.getNum());
					moveControl(myNumber1, myNumber2);
					IncreaseCount();

					arr.set(i, myNumber2);
					arr.set(j, myNumber1);
				}
			}
		}
		Number myNumber1 = arr.get(i + 1);
		Number myNumber2 = arr.get(high);
		if (myNumber1.getNum() != myNumber2.getNum()) {
			moveControl(myNumber1, myNumber2);
			IncreaseCount();

			arr.set(i + 1, myNumber2);
			arr.set(high, myNumber1);
		}
		return i + 1;
	}

	// Thuật toán sắp xếp HeapSort()
	public synchronized void HeapSort() {
		System.out.println("Đang chạy thuật toán HeapSort() của class Random ");
		try {
			isFinished = false;
			System.out.println("Giá trị isFinished TRƯỚC khi sắp xếp là " +isFinished);
			name = "HeapSort tăng dần";
			
			// Kham khảo ở: https://niithanoi.edu.vn/heap-sort.html
			int n = arr.size();
			// Xây dựng heap sắp xếp lại mảng
			for (int i = n / 2 - 1; i >= 0; i--) {
				heapify(arr, n, i);
			}
			for (int i = n - 1; i > 0; i--) {
				Number myNumber1 = arr.get(0);
				Number myNumber2 = arr.get(i);
					moveControl(myNumber1, myNumber2);
					IncreaseCount();
					Number nt1 = myNumber1;
					myNumber1 = myNumber2;
					myNumber2 = nt1;
					arr.set(0, myNumber1);
					arr.set(i, myNumber2);
				// gọi max - heapify trên Heap đã sắp xếp
				heapify(arr, i, 0);
			}
			isFinished = true;
			System.out.println("Giá trị isFinished SAU khi sắp xếp là " +isFinished);
			System.out.println("---- Hoàn thành  HeapSort() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void heapify(ArrayList<Number> arr, int n, int i) {
		int largest = i; // Khởi tạo largest như root
		int l = 2 * i; // left = 2*i
		int r = 2 * i + 1; // right = 2*i + 1

		// Nếu nút con bên trái lớn hơn nút con của gốc
		if (l < n && arr.get(l).getNum() > arr.get(largest).getNum())
			largest = l;

		// Nếu nút con bên phải lớn hơn largest
		if (r < n && arr.get(r).getNum() > arr.get(largest).getNum())
			largest = r;

		// Nếu largest không phải là root
		if (largest != i) {
			Number myNumber1 = arr.get(i);
			Number myNumber2 = arr.get(largest);
			moveControl(myNumber1, myNumber2);
			IncreaseCount();
			Number nt1 = myNumber1;
			myNumber1 = myNumber2;
			myNumber2 = nt1;
			arr.set(i, myNumber1);
			arr.set(largest, myNumber2);
			// Vun đống lại các cây con bị ảnh hưởng
			heapify(arr, n, largest);
		}
	}

	// Thuật toán sắp xếp HeapSort_Giam()
	public synchronized void HeapSortReduce() {
		System.out.println("Đang chạy thuật toán HeapSort_Giam() của class Random ");
		try {
			isFinished = false;
			name = "HeapSort giảm dần";

			// https://niithanoi.edu.vn/heap-sort.html
			int n = arr.size();
			// Xây dựng heap sắp xếp lại mảng
			for (int i = n / 2 - 1; i >= 0; i--)
				heapifyReduce(arr, n, i);

			for (int i = n - 1; i > 0; i--) {
				Number myNumber1 = arr.get(0);
				Number myNumber2 = arr.get(i);
				moveControl(myNumber1, myNumber2);
				IncreaseCount();
				Number nt1 = myNumber1;
				myNumber1 = myNumber2;
				myNumber2 = nt1;
				arr.set(0, myNumber1);
				arr.set(i, myNumber2);
				// gọi max - heapify trên Heap đã sắp xếp
				heapifyReduce(arr, i, 0);
			}

			isFinished = true;
			System.out.println("---- Hoàn thành  HeapSort_Giam() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void heapifyReduce(ArrayList<Number> arr, int n, int i) {
		int largest = i; // Khởi tạo largest như root
		int l = 2 * i; // left = 2*i
		int r = 2 * i + 1; // right = 2*i + 1

		// Nếu nút con bên trái lớn hơn nút con của gốc
		if (l < n && arr.get(l).getNum() < arr.get(largest).getNum())
			largest = l;

		// Nếu nút con bên phải lớn hơn largest
		if (r < n && arr.get(r).getNum() < arr.get(largest).getNum())
			largest = r;

		// Nếu largest không phải là root
		if (largest != i) {
			Number myNumber1 = arr.get(i);
			Number myNumber2 = arr.get(largest);
			moveControl(myNumber1, myNumber2);
			IncreaseCount();
			Number nt1 = myNumber1;
			myNumber1 = myNumber2;
			myNumber2 = nt1;
			
			arr.set(i, myNumber1);
			arr.set(largest, myNumber2);
			// Vun đống lại các cây con bị ảnh hưởng
			heapifyReduce(arr, n, largest);
		}
	}

	public synchronized void InterchangeSort() {
		System.out.println("Đang chạy thuật toán InterchangeSort_Tang() của class Random ");
		try {
			isFinished = false;
			name = "InterchangeSort tăng dần";

			// Kham khảo ở: https://gochocit.com/ky-thuat-lap-trinh/
			for (int i = 0; i < arr.size() - 1; i++) {
				for (int j = i + 1; j < arr.size(); j++) {
					Number myNumber1 = arr.get(i);
					Number myNumber2 = arr.get(j);
					if (myNumber1.getNum() > myNumber2.getNum()) {
						moveControl(myNumber1, myNumber2);
						IncreaseCount();
						
						Number nt1 = myNumber1;
						myNumber1 = myNumber2;
						myNumber2 = nt1;
						
						arr.set(i, myNumber1);
						arr.set(j, myNumber2);
					}
				}
			}

			isFinished = true;
			System.out.println("---- Hoàn thành InterchangeSort() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void InterchangeSortReduce() {
		System.out.println("Đang chạy thuật toán InterchangeSort_Giam() của class Random ");
		try {
			isFinished = false;
			name = "InterchangeSort giảm dần";
			for (int i = 0; i < arr.size() - 1; i++) {
				for (int j = i + 1; j < arr.size(); j++) {
					Number myNumber1 = arr.get(i);
					Number myNumber2 = arr.get(j);
					if (myNumber1.getNum() < myNumber2.getNum()) {
						moveControl(myNumber1, myNumber2);
						IncreaseCount();
						Number nt1 = myNumber1;
						myNumber1 = myNumber2;
						myNumber2 = nt1;
						arr.set(i, myNumber1);
						arr.set(j, myNumber2);
					}
				}
			}

			isFinished = true;
			System.out.println("---- Hoàn thành InterchangeSort_Giam() ----");
			printInFor();
			Number.COLOR = Color.BLUE;
			repaint();
			Notify(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
