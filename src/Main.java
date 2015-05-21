

public class Main {

	public static void main(String[] args) {

		int mem[] = new int [4096];
//		mem[0] = Befehl.NOP.ordinal();
//		//Lädt 1000 in R0
//		mem[1] = Befehl.LOAD.ordinal() + (1000 << 4);
//		//MOV 1000 von R0 -> R1
//		mem[2] = Befehl.MOV.ordinal() + ((1 << 4) + (0 << 8) + (0 << 12) + (0 << 13));
//		//Load 5
//		mem[3] = Befehl.LOAD.ordinal() + (5 << 4);
//		//MOV 5 von R0 -> R2
//		mem[4] = Befehl.MOV.ordinal() + ((2 << 4) + (0 << 8) + (0 << 12) + (0 << 13));
//		//MOV 5 to mem R2 -> addR1
//		mem[5] = Befehl.MOV.ordinal() + ((1 << 4) + (2 << 8) + (0 << 12) + (1 << 13));
//		//Load 1001
//		mem[6] = Befehl.LOAD.ordinal() + (1001 << 4);
//		//MOV 1001 R0 -> R3
//		mem[7] = Befehl.MOV.ordinal() + ((3 << 4) + (0 << 8) + (0 << 12) + (0 << 13));
//		//MOV 1000 R3 -> MEM 1001 
//		mem[8] = Befehl.MOV.ordinal() + ((3 << 4) + (1 << 8) + (1 << 12) + (1 << 13));
//		//MOV MEM 1000 -> R4
//		mem[9] = Befehl.MOV.ordinal() + ((4 << 4) + (3 << 8) + (1 << 12) + (0 << 13));
		
		
		
//		VirtuelleMaschine meineMaschine = new VirtuelleMaschine(mem);
//		System.out.println(mem[1]);
//		System.out.println(Befehl.LOAD.ordinal());
//		meineMaschine.ausführen();
//		
//		System.out.println("Mem1000: " + mem[1000]);
//		System.out.println("Mem1001: " + mem[1001]);
		
		System.out.println("AB HIER GEHTS LOS!!!!!!");

		
		Input.fillList();
		String [] daten = Input.leseDatei("C:\\Users\\Steffen\\Desktop\\test.txt");
		int i = 0;
		for (String string : daten) {
			if(string == null){
			}else{
				mem[i] = Input.leseBefehlsZeile(string);
				i++;
			}
		}
		VirtuelleMaschine meineMaschine = new VirtuelleMaschine(mem);
		System.out.println(mem[0]);
		System.out.println(mem[1]);
		System.out.println(mem[2]);
		meineMaschine.druck();
		meineMaschine.ausführen();
	}

}
