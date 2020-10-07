import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Area extends JPanel implements ActionListener {
	
	private Timer timer;
	static Harta h = new Harta();
	static Player p = new Player();
	Bomb bomb = new Bomb();
	public static Graphics temp;
	private boolean enterFlag = false;

	public Area() {

		addKeyListener(new Al());
		setFocusable(true);
		requestFocusInWindow();
		requestFocus();
		grabFocus();

		timer = new Timer(25, this);
		timer.start();

	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	int finalX;
	int finalY;

	public void callback(int x, int y) {
		System.out.println("Function in area - callback");
		if (temp == null)
			System.out.println("temp is null");
		finalX = x;
		finalY = y;

		temp.drawImage(p.getPlayer(), finalX, finalY, null);
		temp.dispose();
		System.out.println("final x " + x + " final Y + " + y);

	}

	@SuppressWarnings("unused")
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!enterFlag) {
			enterFlag = true;
			temp = g;
			System.out.println("init temp");
		}

		for (int y = 0; y < 14; y++) {
			for (int x = 0; x < 13; x++) {
				if (h.getMap(x, y).equals("t")) {
					g.drawImage(h.getTile(), x * 32, y * 32, null);
				}

				if (h.getMap(x, y).equals("w")) {
					g.drawImage(h.getWall(), x * 32, y * 32, null);
				}
				
				if (h.getMap(x, y).equals("b")){
					g.drawImage(h.getBonus(), x*32, y*32, null);
				}
				if (h.getMap(x, y).equals("x")){
					g.drawImage(h.getTile(), x*32, y*32, null);
				}
				if (h.getMap(x, y).equals("P")){
					g.drawImage(h.getTrap(), x*32, y*32, null);
				}

			}
		}
		ImageIcon img = new ImageIcon("./mario.png");
		Image player = img.getImage();
		g.drawImage(p.getPlayer(), p.getTileX() * 32, p.getTileY() * 32, null);
		temp.dispose();
		repaint();

	}
	static int puncte = 0;
	static int vieti = 3;
	
	public String Titlu(){
		return "Punctaj: " + puncte + "  Vieti: " + vieti;		
	}

	public static class MouseEventDemo implements MouseListener {

		public void mousePressed(MouseEvent e) {
			System.out.println("getY " + e.getY() + "tileY " + p.getTileY() * 32 + "WALL: " + "getX " + e.getX()
					+ "tileX " + p.getTileX() * 32 + "WALL: ");
			// to right
			if ((e.getX() - p.getTileX() * 32) >= 32 && !h.getMap(p.getTileX() + 1, p.getTileY()).equals("w") && h.getMap(p.getTileX() + 1, p.getTileY()).equals("b") 
					&& ((e.getY() - p.getTileY()) > 32)) {
				p.setImage("./mario.png");
				System.out.println("bonus");

				p.move(1, 0);
			}
			else if ((e.getX() - p.getTileX() * 32) >= 32 && !h.getMap(p.getTileX() + 1, p.getTileY()).equals("w")
					&& ((e.getY() - p.getTileY()) > 32)) {
				p.setImage("./mario.png");
				puncte += 1;

				p.move(1, 0);

			}
			// to stanga
			else if ((e.getY() > p.getTileY() * 32) && !h.getMap(p.getTileX() - 1, p.getTileY()).equals("w")
					&& (e.getX() < p.getTileX() * 32)) {
				p.setImage("./mario.png");
				p.move(-1, 0);
			}
			// up
			else if ((e.getY() < p.getTileY() * 32) && !h.getMap(p.getTileX(), p.getTileY() - 1).equals("w")
					&& ((e.getX() - p.getTileX() * 32) < 32)) {
				p.setImage("./mario.png");
				p.move(0, -1);
			}
			// down
			else if ((e.getY() > p.getTileY() * 32) && !h.getMap(p.getTileX(), p.getTileY() + 1).equals("w")
					&& ((e.getX() > p.getTileX()))) {
				p.setImage("./mario.png");
				p.move(0, 1);
			

			}
		}

		public void mouseReleased(MouseEvent e) {
			saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
		}

		public void mouseEntered(MouseEvent e) {
			saySomething("Mouse entered", e);
		}

		public void mouseExited(MouseEvent e) {
			saySomething("Mouse exited", e);
		}

		public void mouseClicked(MouseEvent e) {
			saySomething("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
		}

		void saySomething(String eventDescription, MouseEvent e) {
			System.out.println(eventDescription);
		}
	}

	public static class Al extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
		
			int keycode = e.getKeyCode();
			if ( vieti == 0) keycode = '0';

			if (keycode == KeyEvent.VK_UP) {
				if (!h.getMap(p.getTileX(), p.getTileY() - 1).equals("w") && h.getMap(p.getTileX(), p.getTileY() - 1).equals("b") ){
					p.move(0, -1);
					puncte +=1;
					h.replaceBonus(p.getTileX(), p.getTileY());
					System.out.println("Punctaj: " + puncte);
					
				}
				else if (!h.getMap(p.getTileX(), p.getTileY() - 1).equals("w") && h.getMap(p.getTileX(), p.getTileY() - 1).equals("x") ){
					p.move(0, -1);
					h.replaceTrap(p.getTileX(), p.getTileY());
					vieti -=1;
					System.out.println("Mai ai " + vieti + " vieti");
					
				}
				else if (!h.getMap(p.getTileX(), p.getTileY() - 1).equals("w")  && !h.getMap(p.getTileX(), p.getTileY() - 1).equals("b"))
					{p.move(0, -1);}
				System.out.println(("W"));
			}
			
			if (keycode == KeyEvent.VK_DOWN) {
				if (!h.getMap(p.getTileX(), p.getTileY() + 1).equals("w") && h.getMap(p.getTileX(), p.getTileY() + 1).equals("b") ){
					puncte +=1;
					h.replaceBonus(p.getTileX(), p.getTileY() + 1);
					System.out.println("Punctaj: " + puncte);
					p.move(0, 1);
				}
				else if (!h.getMap(p.getTileX(), p.getTileY() + 1).equals("w") && h.getMap(p.getTileX(), p.getTileY() + 1).equals("x") ){
					h.replaceTrap(p.getTileX(), p.getTileY() + 1);
					vieti -=1;
					System.out.println("Mai ai " + vieti + " vieti");
					p.move(0, 1);
				}
				else if (!h.getMap(p.getTileX(), p.getTileY() + 1).equals("w") && !h.getMap(p.getTileX(), p.getTileY() - 1).equals("b"))
						{p.move(0, 1); }

			}

			if (keycode == KeyEvent.VK_LEFT) {
				if (!h.getMap(p.getTileX() - 1, p.getTileY()).equals("w") && h.getMap(p.getTileX() - 1, p.getTileY()).equals("b")) {
					p.setImage("./mario.png");
					puncte += 1;
					h.replaceBonus(p.getTileX() - 1, p.getTileY());

					System.out.println("Punctaj: " + puncte);
					p.move(-1, 0);
				}
				else if (!h.getMap(p.getTileX() - 1, p.getTileY()).equals("w") && h.getMap(p.getTileX() - 1, p.getTileY()).equals("x")) {
					p.setImage("./mario.png");
					h.replaceBonus(p.getTileX() - 1, p.getTileY());

					vieti -=1;
					System.out.println("Mai ai " + vieti+ " vieti");
					p.move(-1, 0);
				}
				else if (!h.getMap(p.getTileX() - 1, p.getTileY()).equals("w") && !h.getMap(p.getTileX() - 1, p.getTileY()).equals("b")){
					p.setImage("./mario.png");
					p.move(-1, 0);
				}

			}

			if (keycode == KeyEvent.VK_RIGHT) {
				if (!h.getMap(p.getTileX() + 1, p.getTileY()).equals("w") && h.getMap(p.getTileX() + 1, p.getTileY()).equals("b")) {
					p.setImage("./mario.png");
					puncte += 1;
					h.replaceBonus(p.getTileX() + 1, p.getTileY());

					System.out.println("Punctaj: " + puncte);
					p.move(1, 0);
				}
				
				else if (!h.getMap(p.getTileX() + 1, p.getTileY()).equals("w") && h.getMap(p.getTileX() + 1, p.getTileY()).equals("x")) {
					p.setImage("./mario.png");
					h.replaceTrap(p.getTileX() + 1, p.getTileY());

					vieti -=1;
					System.out.println("Mai ai" + vieti+ " vieti");
					p.move(1, 0);
				}
				else if(!h.getMap(p.getTileX() + 1, p.getTileY()).equals("w") && !h.getMap(p.getTileX() + 1, p.getTileY()).equals("b")){
					p.setImage("./mario.png");
					p.move(1, 0);
				}
			}

		}

		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}
	}

}
