package a.threads;

/*
 * Threads sind eine Möglichkeit, in Java mehrere Dinge "gleichzeitig" zu tun.
 * 
 * Das einfachste ist, wenn meine Klasse sofort von Thread erbt
 */

public class ErstesBeispiel extends Thread{
	
	//Damit ich meine verschiedenen Thread-Instanzen unterscheiden kann
	private int id;
	
	//Damit die id auf jeden Fall gesetzt ist:
	public ErstesBeispiel(int id) {
		this.id = id;
		
		System.out.println("Thread "+id+" ist da");
	}
	
	
	public static void main(String[] args) {
		/*
		 * Jetzt kann ich Instanzen meiner Kindklasse von Thread erzeugen
		 */
		Thread eins = new ErstesBeispiel(1);
		//Welchen Zustand hat dieser Thread?
		System.out.println("Thread 1 ist " + eins.getState());
		
		/*
		 * Jetzt kann ich meinen Thread starten
		 */
		eins.start();
		System.out.println("Thread 1 wird gestartet");
		//Welchen Zustand jetzt?
		System.out.println("Thread 1 ist " + eins.getState());
		
		/*
		 * Noch ein Thread
		 */
		Thread zwei = new ErstesBeispiel(2);
		zwei.start();
		System.out.println("Thread 2 wird gestartet");
		
		//Kann ich einen Thread nicht noch mal starten?
//		zwei.start();
		/*
		 * Das geht nicht, weil Thread nicht mehr im Zustand NEW ist,
		 * nur dann kann ich ihn starten
		 * (vermutlich ist er gerade RUNNABLE oder vielleicht auch schon DEAD)
		 */
		
	}
	
	/*
	 * Um dem Thread etwas zu tun zu geben, überschreiben wir die Methode run()
	 * In der run()-Methode steht alles das, was der Thread tun soll
	 */
	@Override
	public void run() {
		/*
		 * Diese Methode wird gestartet sobald der Thread gestartet wird.
		 * Wir führen aber run() nicht selber aus, sondern machen das
		 * durch den Aufruf von start()
		 */
		System.out.println("Thread "+id+" meldet sich");
		System.out.println("Thread "+id+" ist "+getState());
		/*
		 * Threads können warten - typischerweise eine bestimmte Zeit
		 */
		try {
			sleep(100);
			//Hier gebe ich an, dass der Thread 100ms lang schlafen soll
		} catch(InterruptedException e) {
			/*
			 * Weil Thread.sleep() eine CheckedException werfen kann,
			 * muss ich einen try-catch-Block darum schreiben.
			 * 
			 * Die Exception wird ausgelöst wenn ein anderer Thread an diesem
			 * die Methode interrupt() aufruft.
			 * Den Mechanismus benutzt nur nie jemand :(
			 */
			e.printStackTrace();
		}
		System.out.println("Thread "+id+" ist wieder wach");
	}
	
}
