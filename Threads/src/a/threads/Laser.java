package a.threads;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Laser extends JPanel {
	
	int x, y, xSpeed, ySpeed;
	
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		setBackground(new Color(0x4169e1));
		xSpeed = 8;
		ySpeed = 0;
		setSize(25, 5);
		
		
	}
	
	public void moveLaser() {
		x += xSpeed;
		y += ySpeed;
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

}	
