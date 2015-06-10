
public class Main {

	public static void main(String[] args) {

		Input.fillList();

		String[] daten = Input.leseDatei("C:\\Users\\lenuy_000\\Documents\\Studium\\GitHub\\HWP\\src\\Hauptprogramm.txt");
		int i = 0;
		int mem[] = new int[4096];
		for (String string : daten) {
			if (string == null) {
			} else {
				mem[i] = Input.leseBefehlsZeile(string);
				i++;
			}
		}
		VirtuelleMaschine meineMaschine = new VirtuelleMaschine(mem);
		// meineMaschine.druck();
		meineMaschine.ausführen();
		meineMaschine.druckeRegister();
	}

}
