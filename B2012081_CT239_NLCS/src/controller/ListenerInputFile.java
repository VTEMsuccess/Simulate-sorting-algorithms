package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.NumberPanel;
import view.InputFile;
import view.InputHands;
import view.InputRandom;

public class ListenerInputFile implements ActionListener {

	private InputFile nhapFile;

	public ListenerInputFile(InputFile nhapFile) {
		this.nhapFile = nhapFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		System.out.println("Bạn chọn nhấn nút: " + src);

		if (src.equals("Mở file")) {
			nhapFile.OpenFile();
		} else if (src.equals("Đọc file")) {
			nhapFile.RemoveAll();
			nhapFile.ReadFile();
		} else if (src.equals("Sắp xếp")){
			nhapFile.ResetSwap();
			nhapFile.sortControlFileInput();
		} else if (src.equals("Đặt lại")) {
			nhapFile.RemoveAll();
		}
		else if (src.equals("Nhập")) {
			nhapFile.RemoveAll();
			nhapFile.InputNameFile();
		}
		else if (src.equals("Chọn file")) {
			nhapFile.RemoveAll();
			nhapFile.ChooseFile();
		}
		else if(src.equals("Tăng dần")) {
			nhapFile.Increase();
		}
		else if(src.equals("Giảm dần")) {
			nhapFile.Reduce();
		}
		else if(src.equals("Xuất file")) {
			nhapFile.ExportFile();
		}
		else if(src.equals("Xem")) {
			nhapFile.getDataSwap();
		}
		else if(src.equals("Xóa tất cả")) {
			nhapFile.resetDataSwap();
		}
	}

}
