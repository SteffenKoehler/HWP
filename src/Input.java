import java.util.HashMap;
import java.util.Map;


public class Input {
	
	
	
	Map <String, Integer> befehlsMap = new HashMap<String, Integer>();
	
	public Input(){
		befehlsMap.put("NOP", 0);
		befehlsMap.put("LOAD", 1);
		befehlsMap.put("MOVE", 2);
		befehlsMap.put("ADD", 3);
		befehlsMap.put("SUB", 4);
		befehlsMap.put("MUL", 5);
		befehlsMap.put("DIV", 6);
		befehlsMap.put("PUSH", 7);
		befehlsMap.put("POP", 8);
		befehlsMap.put("JMP", 9);
		befehlsMap.put("JIZ", 10);
		befehlsMap.put("JIH", 11);
		befehlsMap.put("JSR", 12);
		befehlsMap.put("RTS", 13);
		
		befehlsMap.put("R0,", 0 << 4);
		befehlsMap.put("R1,", 1 << 4);
		befehlsMap.put("R2,", 2 << 4);
		befehlsMap.put("R3,", 3 << 4);
		befehlsMap.put("R0,", 0 << 4);
		befehlsMap.put("R0,", 0 << 4);
	}

}
