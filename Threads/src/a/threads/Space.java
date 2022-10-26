package a.threads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import javax.sound.midi.spi.SoundbankReader;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Space  {

	private JFrame frame;
	private JPanel space;
	private Raumschiff orion = new Raumschiff();
	private Laser pew = new Laser(orion.getX()+25, orion.getY()+17);
	ArrayList<Laser> ar = new ArrayList<>();

	private SoundEffekte sounds;

	
	
	public Space() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocation(200, 100);
		frame.setLayout(new BorderLayout());

		space = new JPanel();
		space.setBackground(new Color(0x000000));
		space.setLayout(null);//Absolute (oder null-)Layout erlaubt pixelgenaue Positionierung

		// Für Sterne 
		for(int i = 0; i < 100; i++) {
			JPanel stern = new JPanel();
			stern.setSize(2, 2);
			stern.setBackground(new Color(0xFFFFFF));
			Random r = new Random();
			stern.setLocation(r.nextInt(600), r.nextInt(600));
			
			space.add(stern);
		}

			

		
		space.add(orion);
		frame.add(space, BorderLayout.CENTER);
		space.setComponentZOrder(orion, 0);
		//Bewegung des Raumschiffes soll in einem eigenen Thread passieren
		createThread();

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_SPACE) {
					sounds = new SoundEffekte();
					sounds.load();
					sounds.play(SoundEffekte.sound);
					
					//					space.add(pew);
					//					pew.moveLaser();
					//					pew.setLocation(pew.getX(), pew.getY());
					//space.add(new Laser(orion.getX(), orion.getY()+17));
					ar.add(new Laser(orion.getX()+80, orion.getY()+17));
					for(Laser l : ar) {
						space.add(l);
					}
					
				}
			}
		});
		frame.setResizable(false);
		frame.setVisible(true);


	}

	private void createThread() {
		//Hier wird der Thread erzeugt, der sich um die Bewegung des Raumschiffs kümmert.
		Thread move = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						// Damit das Raumschiff nur 10 mal pro Sekunde bewegt wird
						Thread.sleep(10);
						orion.moveRaumschiff();
						orion.setLocation(orion.getX(), orion.getY());
						//							space.add(pew);
						//							pew.moveLaser();
						//							pew.setLocation(pew.getX(), pew.getY());
						for(Laser l : ar) {
							l.moveLaser();
							if(l.getX() > 650) {
								space.remove(l);
							}
							l.setLocation(l.getX(), l.getY());

						}


					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch(ConcurrentModificationException e) {
						e.printStackTrace();
					}

				}
			}
		});
		move.start();
	}

	public static void main(String[] args) {

		new Space();

	}

}
