public class VirtuelleMaschine {
	
	
	public VirtuelleMaschine(int mem[]) {
		this.mem = mem; 
	}

	int mem[];
	int progCnt = 0;
	int reg[] = new int[16];
	int varStack[] = new int[20];
	int varPointer = 0;
	int jmpStack[] = new int[20];
	int jmpPointer = 0;

	public void ausführen() {
		while (progCnt < 4096) {
			int cmd = mem[progCnt] & 15;
			int wert = mem[progCnt] >> 4;
			int idx = (mem[progCnt] >> 4) & 15;
			int idy = (mem[progCnt] >> 8) & 15;
			int frommem = (mem[progCnt] >> 12) & 15;
			int tomem = (mem[progCnt] >> 13) & 15;

			switch (Befehl.values()[cmd]) {
			case NOP: {
				progCnt++;
				//System.out.println(progCnt);
				break;
			}
			
			case ADD:{
				reg[idx] += reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}
			
			case DIV:{
				reg[idx] /= reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}
			
			case JIH:{
				if(reg[0] > 0){
					progCnt = wert;
				}
				break;
			}
			
			case JIZ:{
				if(reg[0] == 0){
					progCnt = wert;
				}
				break;
			}
			
			case JMP:{
				progCnt = wert;
				break;
			}
			
			case JSR:{
				jmpStack[jmpPointer] = progCnt;
				jmpPointer++;
				progCnt = wert;
				break;
			}
			
			case LOAD:{
				reg[0] = wert;
				progCnt++;	
				System.out.println("Reg0:  " + reg[0]);
				break;
			}
			
			case MOV: {
				if (tomem == 1 && frommem == 1) {
					System.out.println("MemX: " + mem[reg[idx]] + " MemY: "
							+ mem[reg[idy]]);
					mem[reg[idx]] = mem[reg[idy]];
					System.out.println("MemX: " + mem[reg[idx]] + " MemY: "
							+ mem[reg[idy]]);
					progCnt++;
				} else if (tomem == 1) {
					System.out.println("MemX: " + mem[reg[idx]] + " RegY: "
							+ reg[idy]);
					mem[reg[idx]] = reg[idy];
					System.out.println("MemX: " + mem[reg[idx]] + " RegY: "
							+ reg[idy]);
					progCnt++;
				} else if (frommem == 1) {
					System.out.println("RegX: " + reg[idx] + " MemY: "
							+ mem[reg[idy]]);
					reg[idx] = mem[reg[idy]];
					System.out.println("RegX: " + reg[idx] + " MemY: "
							+ mem[reg[idy]]);
					progCnt++;
				} else {
					System.out.println("RegX: " + reg[idx] + " RegY: "
							+ reg[idy]);
					reg[idx] = reg[idy];
					System.out.println("RegX: " + reg[idx] + " RegY: "
							+ reg[idy]);
					progCnt++;
				}
				break;
			}
			
			case MUL:{
				reg[idx] *= reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}
			
			case POP:{
				reg[idx] = varStack[--varPointer];
				progCnt++;
				break;
			}
			
			case PUSH:{
				varStack[varPointer] = reg[idx];
				varPointer++;
				progCnt++;
				break;
			}
			
			case RTS:{
				if(jmpPointer == 0){
					progCnt = 4096;
				}
				else{
					progCnt = jmpStack[--jmpPointer] + 1;
				}
				break;
			}
			
			case SUB:{
				reg[idx] -= reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}
			
			default:{
				progCnt++;
				break;
			}

			}

		}
	}

}


