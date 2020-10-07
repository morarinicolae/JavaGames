import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Bondari  {
  
  public Bondari() {
   
     Bondar bondar=new Bondar();
     Thread thread=new Thread(bondar);
     thread.start();
       
    }
  /* Clasa cu  interfata Runnable pentru un fir de executie
     cu interfata grafica
  */
  class Bondar extends JFrame implements Runnable {
    
    Fereastra fereastra;
    Thread fir;
    boolean esteViu;

    public Bondar() {
      
      fereastra=new Fereastra();
      fereastra.setSize(400,400);
      fereastra.setBackground(Color.white);
      fereastra.culoare=Color.red;
      Box box=Box.createVerticalBox(); 
           
      box.add(fereastra);
      getContentPane().add(box);
      addWindowListener(new SfarsitBondar(this));
      setSize(400,400);
      setVisible(true);
    }

    /* Definirea metodei run() a interfetei Runnable */
    public void run() {
   
     esteViu=true;
     while(esteViu) {
      fereastra.ampl=100;
      fereastra.repaint();       
      try {
        fir.sleep(30);
      }
      catch(Exception e) {
        System.out.println(e);
      }
     }
    }
    
   
    
    
  } /* Sfarsitul clasei Bondar */

  /* Fereastra in care evolueaza "bondarul" */
  class Fereastra extends Canvas {
    int x, y, ampl;
    Color culoare;

//incarcarea imaginii
BufferedImage im = null;

    public void paint(Graphics g) {
    	
    	try {
    im = ImageIO.read(new File("bee.jpg"));
             } catch (IOException e) {
                   }
    	
     Rectangle r=getBounds();
     x+=(int)(ampl*(Math.random()-0.5));
     if(x<0) x=0;
     else if(x>=r.width) x=r.width-5;
     y+=(int)(ampl*(Math.random()-0.5));
     if(y<0) y=0;
     else if(y>=r.height) y=r.height-5;
     
    //desenarea imaginiilet cuvinte = ['piersica', 'maimuta', 'clatita', 'cascada','apartament','laborator'];
let cuvant = cuvinte[Math.floor(Math.random()* cuvinte.length)];
let vector_raspuns = [];
for ( let i=0; i<cuvinte.length;i++){
    vector_raspuns[i]= '_'
}
let litere_ramase = cuvant.length;
while(litere_ramase > 0 ){
//progresull jucatorului
alert(vector_raspuns.join(' '));
//alegerea literei
let litera = promt('Alege o litera sau apasa CANCEL  pentru a termina jocul');
if (litera == null) {
break;
}else if (litera.length !==1){
alert('Te rog sa introduci DOAR o singura litera!');
} else {
// update vector_raspuns si update litere_ramase
for (let j=0; j<cuvant.length; j++){
if (cuvant[j] === litera) {
vector_raspuns[j] = litera;
litere_ramase --;
}
}
     g.drawImage(im, x,y, 50,50, null); 
    }
  } /* Sfarsitul clasei Fereastra */

  /* Incheierea executarii aplicatiei */
  class Iesire extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }
  } /* Sfarsitul clasei Iesire */

  /* Crearea si lansarea unui nou fir de executie */
  class CreareBondar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int x,y;
      x=(int)(Math.random()*400);
      y=(int)(Math.random()*200);
      Bondar bondar=new Bondar();
      bondar.setLocation(new Point(x,y));
      Thread thread=new Thread(bondar);
      thread.start();
    }
  } /* Sfarsitul clasei CreareBondar */

  /* Incheierea executarii unui fir de executie */
  class SfarsitBondar extends WindowAdapter {
    Bondar bondar;
    SfarsitBondar(Bondar bondar) {
      this.bondar=bondar;
    }

    public void vindowClosing(WindowEvent e) {
      bondar.esteViu=false;
    }
  } /* Sfarsitul clasei SfarsitBondar */

  /* Metoda  principala a aplicatiei */
  public static void main(String args[]) {
    Bondari b=new Bondari();
  }
}