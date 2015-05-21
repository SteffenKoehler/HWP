import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;




public class Input {

	
	
	static List<Value> befehlsliste = new LinkedList();
	static List<Value> werteliste = new LinkedList();
	static List<Value> wertelistesolo = new LinkedList();
	
	public static void fillList(){
		befehlsliste.add(new Value("NOP", 0));
		befehlsliste.add(new Value("LOAD", 1));
		befehlsliste.add(new Value("MOVE", 2));
		befehlsliste.add(new Value("ADD", 3));
		befehlsliste.add(new Value("SUB", 4));
		befehlsliste.add(new Value("MUL", 5));
		befehlsliste.add(new Value("DIV", 6));
		befehlsliste.add(new Value("PUSH", 7));
		befehlsliste.add(new Value("POP", 8));
		befehlsliste.add(new Value("JMP", 9));
		befehlsliste.add(new Value("JIZ", 10));
		befehlsliste.add(new Value("JIH", 11));
		befehlsliste.add(new Value("JSR", 12));
		befehlsliste.add(new Value("RTS", 13));
		
		werteliste.add(new Value("R0,", 0 << 4));
		werteliste.add(new Value("R1,", 1 << 4));
		werteliste.add(new Value("R2,", 2 << 4));
		werteliste.add(new Value("R3,", 3 << 4));
		werteliste.add(new Value("R4,", 4 << 4));
		werteliste.add(new Value("R5,", 5 << 4));
		werteliste.add(new Value("R6,", 6 << 4));
		werteliste.add(new Value("R7,", 7 << 4));
		werteliste.add(new Value("R8,", 8 << 4));
		werteliste.add(new Value("R9,", 9 << 4));
		werteliste.add(new Value("R10,", 10 << 4));
		werteliste.add(new Value("R11,", 11 << 4));
		werteliste.add(new Value("R12,", 12 << 4));
		werteliste.add(new Value("R13,", 13 << 4));
		werteliste.add(new Value("R14,", 14 << 4));
		werteliste.add(new Value("R15,", 15 << 4));
		
		//Rx alleine
		wertelistesolo.add(new Value("R0", 0 << 4));
		wertelistesolo.add(new Value("R1", 1 << 4));
		wertelistesolo.add(new Value("R2", 2 << 4));
		wertelistesolo.add(new Value("R3", 3 << 4));
		wertelistesolo.add(new Value("R4", 4 << 4));
		wertelistesolo.add(new Value("R5", 5 << 4));
		wertelistesolo.add(new Value("R6", 6 << 4));
		wertelistesolo.add(new Value("R7", 7 << 4));
		wertelistesolo.add(new Value("R8", 8 << 4));
		wertelistesolo.add(new Value("R9", 9 << 4));
		wertelistesolo.add(new Value("R10", 10 << 4));
		wertelistesolo.add(new Value("R11", 11 << 4));
		wertelistesolo.add(new Value("R12", 12 << 4));
		wertelistesolo.add(new Value("R13", 13 << 4));
		wertelistesolo.add(new Value("R14", 14 << 4));
		wertelistesolo.add(new Value("R15", 15 << 4));
		
		//Ry nach dem Komma
		werteliste.add(new Value(",R0", 0 << 8));
		werteliste.add(new Value(",R1", 1 << 8));
		werteliste.add(new Value(",R2", 2 << 8));
		werteliste.add(new Value(",R3", 3 << 8));
		werteliste.add(new Value(",R4", 4 << 8));
		werteliste.add(new Value(",R5", 5 << 8));
		werteliste.add(new Value(",R6", 6 << 8));
		werteliste.add(new Value(",R7", 7 << 8));
		werteliste.add(new Value(",R8", 8 << 8));
		werteliste.add(new Value(",R9", 9 << 8));
		werteliste.add(new Value(",R10", 10 << 8));
		werteliste.add(new Value(",R11", 11 << 8));
		werteliste.add(new Value(",R12", 12 << 8));
		werteliste.add(new Value(",R13", 13 << 8));
		werteliste.add(new Value(",R14", 14 << 8));
		werteliste.add(new Value(",R15", 15 << 8));
		
		//from Mem
		werteliste.add(new Value("),", 1 << 13));
		
		//to Mem
		werteliste.add(new Value(",(", 1 << 12));
	}
	
	
	private static int leseBefehl(String befehlsZeile){
		for (Value value : befehlsliste) {
			if(befehlsZeile.contains(value.getBefehl())){
				return value.getWert(); 
			}
		}
		return -1;
	}
	
	private static int leseWert(String befehlsZeile){
		int toReturn = 0;
		for (Value value : werteliste) {
			if(befehlsZeile.contains(value.getBefehl())){
				toReturn += value.getWert(); 
			}
		}
		return toReturn;
	}
	
	private static int leseWertsolo(String befehlsZeile){
		int toReturn = 0;
		for (Value value : wertelistesolo) {
			if(befehlsZeile.contains(value.getBefehl())){
				toReturn += value.getWert(); 
			}
		}
		return toReturn;
	}

	public static int leseBefehlsZeile(String befehlsZeile){
		int toReturn = leseBefehl(befehlsZeile);
		if (toReturn == 1 || (toReturn >= 9 && toReturn <= 12)){
			String [] split = befehlsZeile.split(" ");
			toReturn += (Integer.parseInt(split[1]) << 4);
		}else{
			if(toReturn == 7 || toReturn == 8){
				toReturn += leseWertsolo(befehlsZeile);
			}else{
				toReturn += leseWert(befehlsZeile);
			}
		}
		
		return toReturn;
	}
	
	public static String [] leseDatei(String pfad){
		try{
		BufferedReader in = new BufferedReader(new FileReader(pfad));
		String [] daten = new String[4096];
		int flag = 0;
		while((daten[flag] = in.readLine()) != null){
			flag ++;
		}
		in.close();
		return daten;
		}catch(IOException e) {
			
		}
		return null;
	}
}
