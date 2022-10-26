package GemeinsamesKonto;

public class Konto extends Thread {

	private static double konto = 500.00;
	
	
	public static void main(String[] args) {
		Thread Rainer = new Konto();
		Thread Monika = new Konto();
		
		Rainer.start();
		Monika.start();
		System.out.println(Rainer.getState());
	}

	public static void geldAbheben(double betrag) {
		konto -= betrag;
	}
	
	@Override
	public void run() {
		System.out.println("Kontostand: "+konto);
		
		geldAbheben(300);
		System.out.println(konto);
		
		
	}
}

