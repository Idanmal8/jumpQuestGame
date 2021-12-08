import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class obstacles{
	private int x;
	private int y;
	private int birdHeight;
	private int FromHighY;
	private int bottomY;
	private int midPass;
	public JLabel Saber;
	public JLabel SaberTop;
	private int windowHeight;
	private int obstcaleWidth;
	private final String SABER_OBSTACLE_BOTTOM = "BottomSaber.png";
	private final String SABER_OBSTACLE_TOP = "SaberTop.png";
	private Image myImage3;
	private Image myImage5;
	
	

	public obstacles(int x , int birdHeight , int windowHieght, int obstcaleWidth) {
		this.windowHeight = windowHieght;
		this.x = x;
		this.y = 0;
		this.obstcaleWidth = 15;
		FromHighY = (int)(Math.random() * (windowHieght- (birdHeight * 3)));
		bottomY = FromHighY + (4 * birdHeight);
		this.birdHeight = birdHeight;
		this.midPass = (FromHighY + bottomY) / 2 ;
		
		try {
			BufferedImage image3 = ImageIO.read(new File(SABER_OBSTACLE_BOTTOM));
			myImage3 = image3.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
			Saber = new JLabel(new ImageIcon(myImage3));
			Saber.setBounds(x, y , 15, windowHieght - getBottomY());
		}
		catch (IOException e) {
			
		}
		try {
			BufferedImage image5 = ImageIO.read(new File(SABER_OBSTACLE_TOP));
			myImage5 = image5.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
			SaberTop = new JLabel(new ImageIcon(myImage5));
			SaberTop.setBounds(x, y , 15, windowHieght - getBottomY());
		}
		catch (IOException e) {
			
		}
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(myImage5, this.x, this.y, this.obstcaleWidth , this.FromHighY, null);
		g.drawImage(myImage3, this.x , this.bottomY ,this.obstcaleWidth , this.windowHeight, null);
	}
	
	
	public int getObstcaleWidth() {
		return obstcaleWidth;
	}
	
	public void setObWidth(int Obs_wid) {
		this.obstcaleWidth = Obs_wid;
	}

	public int getWindowH() {
		return windowHeight;
	}
	public void setWindowsH(int windowsH) {
		this.windowHeight = windowsH;
	}

	public int getMidPass() {
		return midPass;
	}

	public void setMidPass(int midPass) {
		this.midPass = midPass;
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
	public int getHeight() {
		return birdHeight;
	}
	public int getBirdHeight() {
		return birdHeight;
	}

	public int getFromHighY() {
		return FromHighY;
	}

	public int getBottomY() {
		return bottomY;
	}

	public void setHeight(int height) {
		this.birdHeight = height;
	}

}
