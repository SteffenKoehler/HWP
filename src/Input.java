import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Input {

	static List<KeyValue> befehlsListe = new LinkedList();
	static List<KeyValue> werteListe = new LinkedList();
	static List<KeyValue> rxEinzelListe = new LinkedList();

	public static void fillList() {
		befehlsListe.add(new KeyValue("NOP", 0));
		befehlsListe.add(new KeyValue("LOAD", 1));
		befehlsListe.add(new KeyValue("MOVE", 2));
		befehlsListe.add(new KeyValue("ADD", 3));
		befehlsListe.add(new KeyValue("SUB", 4));
		befehlsListe.add(new KeyValue("MUL", 5));
		befehlsListe.add(new KeyValue("DIV", 6));
		befehlsListe.add(new KeyValue("PUSH", 7));
		befehlsListe.add(new KeyValue("POP", 8));
		befehlsListe.add(new KeyValue("JMP", 9));
		befehlsListe.add(new KeyValue("JIZ", 10));
		befehlsListe.add(new KeyValue("JIH", 11));
		befehlsListe.add(new KeyValue("JSR", 12));
		befehlsListe.add(new KeyValue("RTS", 13));

		// ADD RX,
		for (int i = 0; i < 16; i++) {
			werteListe.add(new KeyValue(("R" + i + ","), i << 4));
		}
		// ADD (RX),
		for (int i = 0; i < 16; i++) {
			werteListe.add(new KeyValue("(R" + i + "),", i << 4));
		}
		// ADD ,RY
		for (int i = 0; i < 16; i++) {
			werteListe.add(new KeyValue((",R" + i), i << 8));
		}

		// ADD ,(RY)
		for (int i = 0; i < 16; i++) {
			werteListe.add(new KeyValue(",(R" + i + ")", i << 8));
		}

		// to Mem
		werteListe.add(new KeyValue(",(", 1 << 12));
		// from Mem
		werteListe.add(new KeyValue("),", 1 << 13));

		// ADD RX einzel
		for (int i = 0; i < 16; i++) {
			rxEinzelListe.add(new KeyValue("R" + i, i << 4));
		}
	}

	private static int sucheBefehl(String befehlsZeile) {
		for (KeyValue value : befehlsListe) {
			if (befehlsZeile.contains(value.getBefehl())) {
				return value.getWert();
			}
		}
		return -1;
	}

	private static int sucheWerte(String befehlsZeile) {
		int toReturn = 0;
		for (KeyValue value : werteListe) {
			if (befehlsZeile.contains(value.getBefehl())) {
				toReturn += value.getWert();
			}
		}
		return toReturn;
	}

	private static int sucheRxEinzelwert(String befehlsZeile) {
		int toReturn = 0;
		for (KeyValue value : rxEinzelListe) {
			if (befehlsZeile.contains(value.getBefehl())) {
				toReturn += value.getWert();
			}
		}
		return toReturn;
	}

	public static int leseBefehlsZeile(String befehlsZeile) {
		int toReturn = sucheBefehl(befehlsZeile);
		if (toReturn == 1 || (toReturn >= 9 && toReturn <= 12)) {
			String[] split = befehlsZeile.split(" ");
			toReturn += (Integer.parseInt(split[1]) << 4);
		} else {
			if (toReturn == 7 || toReturn == 8) {
				toReturn += sucheRxEinzelwert(befehlsZeile);
			} else {
				toReturn += sucheWerte(befehlsZeile);
			}
		}
		return toReturn;
	}

	public static String[] leseDatei(String pfad) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(pfad));
			String[] daten = new String[4096];
			int flag = 0;
			while ((daten[flag] = in.readLine()) != null) {
				flag++;
			}
			in.close();
			return daten;
		} catch (IOException e) {
		}
		return null;
	}
}
