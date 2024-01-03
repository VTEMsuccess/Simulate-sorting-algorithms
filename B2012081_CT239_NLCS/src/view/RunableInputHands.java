package view;

import javax.swing.JOptionPane;

import model.NumberPanel;

public class RunableInputHands implements Runnable {

	private NumberPanel numberPanel = null;
	private boolean isFinished = false;

	public RunableInputHands(NumberPanel pn) {
		this.numberPanel = pn;
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted() && isFinished == false) {
				switch (InputHands.sortType) {
					case BUBBLE_SORT:
						numberPanel.doBubbleSort();
						break;
					case SELECTION_SORT:
						numberPanel.doSelectionSort();
						break;
					case INSERTION_SORT:
						numberPanel.doInsertionSort();
						break;
					case QUICK_SORT:
						numberPanel.doQuickSort();
						break;
					case HEAP_SORT:
						numberPanel.HeapSort();
						break;
					case INTERCHANGE_SORT:
						numberPanel.InterchangeSort();
						break;
					case BUBBLE_SORT_REDUCE:
						numberPanel.doBubbleSortReduce();
						break;
					case SELECTION_SORT_REDUCE:
						numberPanel.doSelectionSortReduce();
						break;
					case INSERTION_SORT_REDUCE:
						numberPanel.doInsertionSortReduce();
						break;
					case QUICK_SORT_REDUCE:
						numberPanel.doQuickSortReduce();
						break;
					case HEAP_SORT_REDUCE:
						numberPanel.HeapSortReduce();
						break;
					case INTERCHANGE_SORT_REDUCE:
						numberPanel.InterchangeSortReduce();
						break;
				}
				Thread.sleep(1000);
				isFinished = numberPanel.isFinished();
	}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(numberPanel, "Vui lòng nhập giá trị phần tử", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
