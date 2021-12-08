import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Shields {
	private int x;
	private int y;
	private boolean isToched = false;
	private JLabel shieldP;
	private final String SHIELD = "red-white-shield-with-pixel-art-style_475147-494.png";
	private Image myImage4;	
	private final int shieldSize = 30;
	
	public Shields(int x , int y , boolean isToched) {
		this.x = x;
		this.y = y;
		this.isToched = isToched;
		
		try {
			BufferedImage image4 = ImageIO.read(new File(SHIELD));
			myImage4 = image4.getScaledInstance(shieldSize, shieldSize, Image.SCALE_SMOOTH);
			shieldP = new JLabel(new ImageIcon(myImage4));
			shieldP.setBounds(this.x, this.y, shieldSize, shieldSize);
		}
		catch (IOException e) {
			
		}
		
		
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(myImage4, this.x, this.y, shieldSize , shieldSize ,  null);
		
	}
	

	public int getShieldSize() {
		return shieldSize;
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

	public boolean isToched() {
		return isToched;
	}

	public void setToched(boolean isToched) {
		this.isToched = isToched;
	}
	
	
	
	
	

}
