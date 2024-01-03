package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.NumberPanel;
import view.InputHands;
import view.InputRandom;

public class ListenerInputRandom implements ActionListener {

	private InputRandom random;

	public ListenerInputRandom(InputRandom random) {
		this.random = random;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		System.out.println("Bạn chọn nhấn nút: " + src);

		if (src.equals("Vẽ")) {
			random.drawControlRandom();
		} else if (src.equals("Sắp xếp")) {
			random.sortControlRandom();
			random.ResetSwap();
			
		} else if (src.equals("Đặt lại")) {
			random.RemoveAllNumbers();
		}
		else if(src.equals("Tăng dần")) {
			random.Increase();
		}
		else if(src.equals("Giảm dần")) {
			random.Reduce();
		}
	}

}
