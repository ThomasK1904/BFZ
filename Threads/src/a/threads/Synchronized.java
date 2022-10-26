package a.threads;
/*
 * Wir haben bei Race Condition gesehen, dass beim gemeinsamen schreibende Zugriff
 * Probleme entstehen können.
 * Viele davon können wir durch Synchronisierung beheben
 */
public class Synchronized {
	//Eine gemeinsame Variable, auf die lesend und schreibend zugegriffen wird
	private static int c = 0;
	
	//Es soll zwei Methoden geben, die c hochzählen und runterzählen
	/*
	 * Damit nicht mitten in einem Methodenaufruf der andere Thread dazwischenfunken kann,
	 * kann ich eine Methode synchronisieren.
	 * So wird diese Methode am Stück ausgeführt
	 */
	public synchronized void increment() {
		System.out.println("increment "+c);
		c = c + 1;
		System.out.println("c ist jetzt "+c);
	}
	
	public synchronized void decrement() {
		System.out.println("decrement "+c);
		c = c - 1;
		System.out.println("c ist jetzt "+c);
	}
	
	//Jetzt sollen gleich mehrere Threads auf diese Methoden und damit auf das gemeinsame Feld zugreifen
	public static void main(String[] args) {
		Synchronized count = new Synchronized();
		//Ich mache eine anonyme innere Klasse, die die run()-Methode überschreibt :)
		Thread eins = new Thread() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					//Dieser Thread soll zehnmal hochzählen
					count.increment();
				}
			}
		};
		eins.start();
		
		Thread zwei = new Thread() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					//Dieser Thread soll zehnmal runterzählen
					count.decrement();
				}
			}
		};
		zwei.start();
	}
	/*
	 * Wenn eine Methode länger läuft, muss eventuell nicht die ganze Methode
	 * synchronisiert werden, damit in der Zeit die anderen Threads nicht alle untätig
	 * herumsitzen
	 * 
	 * Dazu kann ich Blöcke synchronisieren
	 */
	public int value() {
		/*
		 * Hier synchronisiere ich nicht die ganze Methode
		 * sondern nur einen Block (auch wenn die selber nicht groß ist)
		 */
		synchronized(this) {
			/*
			 * Jetzt ist nur das synchronisert was in diesem Block steht
			 * der Rest der Methode kann von anderen Threads unterbrochen werden
			 * 
			 * Synchronisierten Blöcken wird dabei ein sog. Monitor übergeben,
			 * letztendlich ein Schlüsselobjekt.
			 * Das ist typischerweise das Objekt auf das gemeinsam schreiben zugegriffen wird.
			 * Oftmals this
			 */
			return c;
		}
		/*
		 * Wenn ich mehrere synchronisierte Blöcke habe, kann ich mehrere Monitor-Objekte haben,
		 * dann stören sie sich nicht gegenseitig (wenn sie auf unterschiedliche gemeinsame
		 * Werte schreibend zugreifen)
		 */
	}
}
