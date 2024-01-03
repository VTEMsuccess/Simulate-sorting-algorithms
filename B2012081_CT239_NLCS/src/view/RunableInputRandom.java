package view;

import javax.swing.JOptionPane;

import model.NumberPanel;

public class RunableInputRandom implements Runnable {

	private NumberPanel numberPanel = null;
	private boolean isFinished = false;

	public RunableInputRandom(NumberPanel pn) {
		this.numberPanel = pn;
	}
	@Override
	public void run() {
		try {
				while (!Thread.currentThread().isInterrupted() && isFinished == false) {
							switch (InputRandom.sortType) {
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
			JOptionPane.showMessageDialog(numberPanel, "Vui lòng nhập số lượng phần tử muốn random", null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
