import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bird{
	private int x;
	private int y;
	private float gravity;
	private int rightJumpTimer = 0;
	private boolean flag;
	private final String SPACE_SHIP = "Spaceship1.png";
	private Image myImage2;
	private final String SHIELD_AFTER_TAKEN = "17-174279_energy-shield-png-energy-shield-transparent.png";
	private Image myImage6;
	private JLabel shieldP;
	private JLabel SpaceShip;

	
	public Bird(int x, int y) {
		this.x = x;// return to float if needed 
		this.y = y;
		this.gravity = 0;
		this.flag = false;
		
		try {
			BufferedImage image2 = ImageIO.read(new File(SPACE_SHIP));
			myImage2 = image2.getScaledInstance(55, 30, Image.SCALE_SMOOTH);
			SpaceShip = new JLabel(new ImageIcon(myImage2));
			SpaceShip.setBounds(this.x, this.y, 55, 30);
		}
		catch (IOException e) {
			
		}
		
		try {
			BufferedImage image6 = ImageIO.read(new File(SHIELD_AFTER_TAKEN));
			myImage6 = image6.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
			shieldP = new JLabel(new ImageIcon(myImage6));
			shieldP.setBounds(this.x, this.y, 60, 40);
		}
		catch (IOException e) {
			
		}
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(myImage2, this.x, this.y, 55, 30, null);
	}
	
	public void drawAfter(Graphics g) {
		g.drawImage(myImage6, this.x, this.y, 60 , 40 ,  null);
	}

	public void step() {
		if(flag == false) {
		rightJumpTimer--;
		if (rightJumpTimer <= 0)
			gravity -= 0.1;
		y -= gravity; 
		}
		else {
			
		}
	}
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void rightJump(int time) {
		gravity = 0.8f;
		rightJumpTimer = time;
	}
	public boolean isRightJump() {
		return rightJumpTimer > 0;
	}
	public void setGravity(float grav) {
		gravity = grav;
	}
	
	public void setX( int x ) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int GetX() {
		return (int)x;
	}
	public int GetY() {
		return (int)y;
	}
	
	
	
	
	
	
}