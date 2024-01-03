package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Number {
	
	// Khai báo vị trí hiển thị của hình chức năng nhập thủ công
	public int localtion=0;
	
	public int getLocaltion() {
		return localtion;
	}

	public void setLocaltion(int localtion) {
		this.localtion = localtion;
	}
	
	public int IncreaseLocaltion() {
		return this.localtion++;
	}
	
	// Khai báo kích thước hình
	public static final int XSIZE = 40;
	public static final int YSIZE = 40;

	// Khai báo vị trí hình
	private int x = 0;
	private int y = 0;

	// Khai báo giá trị trên hình
	private int num = 0;

	// Graphics tạo giao diện đồ họa 2D
	private Graphics graph = null;

	public Number(int num, Graphics graph) {
		this.num = num;
		this.graph = graph;
	}
	
	public Number(int num) {
		this.num = num;
		
	}
	public Number() {
		this.localtion = 0;
		
	}

	// Get & Set
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = (int) x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = (int) y;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Graphics getGraph() {
		return graph;
	}

	public void setGraph(Graphics graph) {
		this.graph = graph;
	}

	public static int getXsize() {
		return XSIZE;
	}

	public static int getYsize() {
		return YSIZE;
	}

	// Khai báo màu cho hình
	public static Color COLOR = Color.BLACK;

	
	// Tạo hàm draw set màu và giá trị cho hình
		public void draw() {
			if(graph!=null) {
				Graphics2D g=(Graphics2D) graph;
				g.setColor(COLOR);
				g.drawRect(x+15, y+5, XSIZE, YSIZE);
				Font font=new Font("Arial", Font.ITALIC | Font.BOLD, 15);
				g.setFont(font);
				g.setColor(COLOR);
				// drawString vẽ chuổi đã cho
				g.drawString("  "+ num +" ", (float)x+18 , (float)y+29);
			}
		}
}
