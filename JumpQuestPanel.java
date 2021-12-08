import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;




public class JumpQuestPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static KeyListener key;

	private int StartX = 50;
	private int StartY = 250;
	protected int birdHeight = 30;
	protected int birdWidth = 55;
	private int yGen;
	protected Bird bird;
	protected Shields shield;
	private boolean flag;
	private boolean shieldTouch;
	private ArrayList<obstacles> obstclaesList;
	private int count;
	public javax.swing.Timer t;
	private boolean gameOverFlag;

	private JPanel panel;
//	private JumpQuestPanel panel;// for checks only 
	private JLabel Score;
	private JLabel gameOver1;
	private Image myImage7;
	private JButton newGame;

	private JPanel BackGroundPanel;
	private JLabel BackGroundPic;
	private Image myImage1;


	private final String BackGround2 = "Space evaders back.jpeg";
	private final String GameOverTag = "GameOverTag.png";



	private int score;
	public int secondReset;
	protected int degree;
	private int obstacleCount;
	private int random;// check if needed 
	private boolean shieldIsGenerated;

	private  JFrame StartScreen;
	private  Container con;
	private  JPanel titleName1;

	private  JPanel ClickMeButton;
	private  JLabel titleNameLabel1;
	private  JButton ClickMe;





	public static void main(String[] args) {
		new JumpQuestPanel();
	}




	/***
	 * this is the constructor with all the core attributes .
	 * @see about all the port stuff which u can delete 
	 * ***/
	public  JumpQuestPanel() {
		Font LabelText = new Font("Times New Roman", Font.PLAIN , 80);

		StartScreen = new JFrame();
		StartScreen.setSize(800, 600);
		StartScreen.setDefaultCloseOperation(StartScreen.EXIT_ON_CLOSE);
		StartScreen.getContentPane().setBackground(Color.black);
		StartScreen.setLayout(null);
		con = StartScreen.getContentPane();

		titleName1 = new JPanel();
		titleName1.setBounds(0, 100, 800, 150);
		titleName1.setBackground(Color.black);
		titleNameLabel1 = new JLabel("SPACE EVADERS");
		titleNameLabel1.setForeground(Color.white);
		titleNameLabel1.setFont(LabelText);

		ClickMeButton = new JPanel();
		ClickMeButton.setBounds(300, 400, 200, 100);
		ClickMeButton.setBackground(Color.black);

		ClickMe = new JButton("START");
		ClickMe.setBackground(Color.black);
		ClickMe.setForeground(Color.black);	
		ClickMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StartScreen.dispose();
					StartPanelScreen();
					

			}
		});
		System.out.println("nice ");

		titleName1.add(titleNameLabel1);
		ClickMeButton.add(ClickMe);
		con.add(ClickMeButton);
		con.add(titleName1);
		StartScreen.setVisible(true);



	}


		public void StartPanelScreen() {
	
			JumpQuestPanel panel = new JumpQuestPanel();
			JFrame frame = new JFrame();
			frame.setSize(800 , 600);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			panel.StartGame();
			frame.add(panel);
			frame.addKeyListener(key);
			frame.setVisible(true);
		
	
		}


	public void StartGame(){
		titleName1.setVisible(false);
		ClickMeButton.setVisible(false);


		try {
			BufferedImage image1 = ImageIO.read(new File(BackGround2));
			myImage1 = image1.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
			BackGroundPic = new JLabel(new ImageIcon(myImage1));
		}
		catch (IOException e) {


		}
		System.out.println("hello");


		obstacleCount = 0;
		shieldTouch = false;
		shieldIsGenerated = false;
		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setVisible(true);
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(gameOver1);
				reset();
				createObstacles();
				MoveObstacles();
				shieldGenerator();
				repaint();
			}
		});

		bird = new Bird(StartX , StartY);
		obstclaesList = new ArrayList<>();
		count = 0;
		score = 0;
		flag = false;
		gameOver1 = new JLabel();
		Score = new JLabel("Score : " + score);
		Score.setForeground(Color.PINK);
		yGen = (int)(Math.random() * 15) + 100;
		random = (int)(Math.random() * 10) + 5;
		shield = new Shields(0, yGen, false);
		add(Score);

		Score.setBounds(getX()/2,0, 10, 2);;
		/**
		 * this is the timer**/

		key = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					Down();
					System.out.println("Down");
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					Up();
					System.out.println("UP");
				}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Right();
					System.out.println("Right");
				}


			}
		};



		t = new javax.swing.Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(count == 5) {
					createObstacles();
				}
				if(random == 0 && count > 10) {
					if(shieldIsGenerated == false) {
						shieldIsGenerated = true;
						shieldGenerator();
					}
				}
				bird.step();
				boundries();
				count++;			
				MoveObstacles();
				if (bird.isRightJump()) {
					MoveObstacles();
					MoveObstacles();
				}
				repaint();
			}
		});
		t.start();

	}



	/***
	 * this is the paint component which is responsible for the shields and the bird .***/
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		g.drawImage(myImage1, 0 , 0 ,getWidth(), getHeight(),null);
		if(rectangleCollision(bird.GetX(), bird.GetY(), birdWidth, birdHeight, shield.getX(), shield.getY(), shield.getShieldSize(), shield.getShieldSize())) {
			flag = true;
			shieldIsGenerated = false;
			random = (int)(Math.random() * (20)) + 1; // *** return here *** 
			if(flag == true) {
				shield.setX(505);
				shield.setY(505);
			}
		}

		if(flag == true) {
			bird.drawAfter(g);
		}

		if(shieldIsGenerated == true) {
			shield.draw(g);
		}

		bird.draw(g);
		for(obstacles s : obstclaesList ) {
			s.draw(g);			
		}
		if(gameOverFlag == true) {
			g.drawImage(myImage7, getWidth()/2 - 100 , getHeight()/2 - 70 , null);
		}else {

		}


	}


	public void Up() {
		bird.setGravity(3);
	}
	public void Down() {

	}
	/***
	 * this function is in charge of the movement of the objects .
	 * ***/
	private void MoveObstacles() {
		gameOverFlag = false;
		if(obstclaesList.size() <= 0) return;
		obstacles my_obs = obstclaesList.get(0);
		if(obstclaesList.get(obstclaesList.size() - 1).getX() <= getWidth() / 2) {
			createObstacles();
		}	

		if(my_obs.getX() + my_obs.getObstcaleWidth() == StartX) {
			score++;
			if(random > 0)
				random--;
			Score.setText("Score: " + score);
		}

		if(my_obs.getX() == 0) {	
			obstacleCount++;
			System.out.println(random);
			System.out.println(gameOverFlag);
			obstclaesList.remove(0);
		}
		if(flag == false) {
			if (rectangleCollision(bird.GetX(), bird.GetY(), birdWidth, birdHeight,
					my_obs.getX(), 0 ,my_obs.getObstcaleWidth() ,my_obs.getFromHighY())
					|| rectangleCollision(bird.GetX(), bird.GetY(), birdWidth, birdHeight,
							my_obs.getX(), my_obs.getBottomY() , my_obs.getObstcaleWidth(), getHeight() - my_obs.getBottomY())) {
				gameOverFlag = true;
				gameOver();

			}
		}
		if(bird.GetY() > getHeight())
			gameOverFlag = true;
		//if shields are on -->
		if(flag == true) {
			if (rectangleCollision(bird.GetX(), bird.GetY(), birdWidth, birdHeight,
					my_obs.getX(), 0 ,my_obs.getObstcaleWidth() ,my_obs.getFromHighY())
					|| rectangleCollision(bird.GetX(), bird.GetY(), birdWidth, birdHeight,
							my_obs.getX(), my_obs.getBottomY() , my_obs.getObstcaleWidth(), getHeight() - my_obs.getBottomY())) {
				shieldTouch = true;
				if(shieldTouch == true && my_obs.getX() + 13 < bird.GetX()) {
					flag = false;
				}
			}
		}
		for(obstacles s : obstclaesList ) {
			s.setX(s.getX() - 1);
		}

		shield.setX(shield.getX() - 1);

	}


	private void shieldGenerator() {
		shield = new Shields(obstclaesList.get(obstclaesList.size() - 1).getX() - 10 , obstclaesList.get(obstclaesList.size() - 1 ).getMidPass() - 10, false);
	}


	private void createObstacles() {
		obstclaesList.add(new obstacles(getWidth() , birdHeight , getHeight() , 15 ));
	}

	private void reset() {
		if(obstclaesList.size() > 0) {
			bird = new Bird(StartX , StartY);
			score = 0;
			Score.setText("Score : " + score);
			gameOver1.setText("");
			obstacleCount = 0;
			obstclaesList.clear();
			t.restart();
			remove(newGame);
		}
	}
	private void boundries() {
		if(getHeight() == 0) {
			return;
		}
		if(bird.GetY() > getHeight()) {
			System.out.println("out of height");
			System.out.println(gameOverFlag);
			gameOver();
		}
		if(bird.GetY() == 0) {
			bird.setY(0);
		}
	}

	private void gameOver() {
		newGame.setBounds(getWidth()/2 - 30, getHeight()/2 + 50, 100, 20);
		newGame.setBackground(Color.black);
		add(newGame);
		try {
			BufferedImage image7 = ImageIO.read(new File(GameOverTag));
			myImage7 = image7.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
			gameOver1 = new JLabel(new ImageIcon(myImage7));
		}
		catch (IOException e) {


		}
		
		t.stop();
		System.out.println("game over 2");
	}

	private boolean rectangleCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
		return x1 < x2 + width2 && x1 + width1 > x2 &&
				y1 < y2 + height2 && y1 + height1 > y2;
	}

	public void Right() {
		bird.rightJump(20);
	}

}














