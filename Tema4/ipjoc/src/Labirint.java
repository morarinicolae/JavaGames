import java.util.Timer;
import java.awt.Image;

import javax.swing.JFrame;


@SuppressWarnings("unused")
public class Labirint {


	public static void main(String[] args) throws InterruptedException{
		new Labirint();
	};
	
	
	
	public Labirint() throws InterruptedException{
		
		JFrame f = new JFrame();
		f.setTitle("Labirint");
		
		f.setSize(450, 500);
		f.setLocationRelativeTo(null);
		f.add(a);
		a.setFocusable(true);
		a.requestFocusInWindow();
		a.requestFocus();
		a.grabFocus();
		f.setVisible(true);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		a.addKeyListener(new Area.Al());
		a.addMouseListener(new Area.MouseEventDemo());


			while(true){
			    long millis = System.currentTimeMillis();
				f.setTitle(a.Titlu());
				Thread.sleep(1000 - millis % 1000);
	}
	}
	
	Player p = new Player();
	Area a = new Area();

	public Image player;
	
}
