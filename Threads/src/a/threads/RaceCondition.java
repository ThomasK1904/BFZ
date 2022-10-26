package a.threads;

public class RaceCondition implements Runnable{
	/*
	 * Solange meine Threads nur lesend auf geteilte Daten zugreifen,
	 * gibt es kein Problem. Wenn sie aber schreiben, dann kann es einen Unterschied machen 
	 */
	private static String text = "Ende";
	
	private String name;
	/*
	 * Ich muss nicht unbediengt von Thread erben, sondern kann auch 
	 * das Interface Runnable implementieren. Dann muss ich eine run()-Methode haben
	 */
	@Override
	public void run() {
		/*
		 * Der Name dieses Threads soll in die gemeinsame Variable geschrieben werden
		 */
		text = name;
		//Damit nicht immer Ringo das letzt Wort hat, schlafen die Threads ein bisschen
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		/*
		 * Ich möchte jetzt vier solche Threads haben, mit unterschiedlichen Namen
		 */
		RaceCondition eins = new RaceCondition();
		eins.name = "John";
		RaceCondition zwei = new RaceCondition();
		zwei.name = "George";
		RaceCondition drei = new RaceCondition();
		drei.name = "Ringo";
		RaceCondition vier = new RaceCondition();
		vier.name = "Paul";
		
		new Thread(eins).start();
		//Ich kann dem Thread-Konstruktor ein Runnable übergeben, das wird dann ausgeführt
		new Thread(zwei).start();
		new Thread(drei).start();
		new Thread(vier).start();
		
		System.out.println(RaceCondition.text);
		/*
		 * Die Ausgabe ist bei mir oft Ringo (bei Ihnen anders),
		 * abhängig von dem, was der Scheduler tut, der den Threads Zeit zuweist
		 * 
		 * Den Umstand, dass derjenig "gewinnt" der zuletzt darauf schreibt wird oftmals
		 * auch als "Race Condition" bezeichnet.
		 */
	}

	

}
