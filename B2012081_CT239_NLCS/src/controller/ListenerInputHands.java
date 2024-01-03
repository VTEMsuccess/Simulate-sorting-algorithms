package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.NumberPanel;
import view.InputHands;
import view.InputRandom;

public class ListenerInputHands implements ActionListener{
	
	private InputHands nhapTay;

	public ListenerInputHands(InputHands nhapTay) {
		this.nhapTay = nhapTay;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		System.out.println("Bạn chọn nhấn nút: " +src);
		
		if(src.equals("Thêm")) {
			nhapTay.drawControlHandsInput();
			nhapTay.focusJtextFiled();
		}
		else if(src.equals("Đặt lại")) {
			nhapTay.removeALLNumbers();
		}
		else if(src.equals("Sắp xếp")) {
			nhapTay.sortControlHandsInput();
			nhapTay.ResetSwap();
		}
		else if(src.equals("Tăng dần")) {
			nhapTay.Increase();
		}
		else if(src.equals("Giảm dần")) {
			nhapTay.Reduce();
		}
	}

}
