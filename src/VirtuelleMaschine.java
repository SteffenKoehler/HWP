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

	public void druck() {
		for (int i = 0; i < 16; i++) {
			System.out.println(reg[i]);
		}
	}

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
				System.out.println("NOP");
				break;
			}

			case ADD: {
				System.out.print("ADD: R"+ idx + "(" + reg[idx] + ")" + " + " + "R" + idy + "(" + reg[idy] + ")");
				reg[idx] += reg[idy];
				progCnt++;
				System.out.println(" = R"+ idx + "(" + reg[idx] + ")" );
				break;
			}

			case DIV: {
				System.out.print("DIV: R"+ idx + "(" + reg[idx] + ")" + " / " + "R" + idy + "(" + reg[idy] + ")");
				reg[idx] /= reg[idy];
				progCnt++;
				System.out.println(" = R"+ idx + "(" + reg[idx] + ")" );
				break;
			}

			case JIH: {
				System.out.println("JIH: R0(" + reg[0] + ")" + "Wert: " + wert);
				if (reg[0] > 0) {
					progCnt = wert;
				}
				break;
			}

			case JIZ: {
				System.out.println("JIZ: R0(" + reg[0] + ")" + "Wert: " + wert);
				if (reg[0] == 0) {
					progCnt = wert;
				}
				break;
			}

			case JMP: {
				System.out.println("JMP: " + "Wert: " + wert);
				progCnt = wert;
				break;
			}

			case JSR: {
				System.out.println("JSR: jmpStack[jmpPointer]: " + jmpStack[jmpPointer]);
				jmpStack[jmpPointer] = progCnt;
				jmpPointer++;
				progCnt = wert;
				break;
			}

			case LOAD: {
				reg[0] = wert;
				System.out.println("LOAD: R0(" + reg[0] + ")");
				progCnt++;
				break;
			}

			case MOV: {
				if (tomem == 1 && frommem == 1) {
					System.out.println(frommem + " " + tomem);
					System.out.print("MOVE: MEM" + reg[idy] + "(" + mem[reg[idy]] + ")" + " -> MEM"+ reg[idx] + "(" + mem[reg[idx]] + ")");
					mem[reg[idx]] = mem[reg[idy]];
					System.out.println("MemX: " + mem[reg[idx]] + " MemY: "
							+ mem[reg[idy]]);
					progCnt++;
				} else if (tomem == 1) {
					System.out.println(frommem + " " + tomem);
					System.out.print("MOVE: R" + idy + "(" + reg[idy] + ")" + " -> MEM"+ reg[idx] + "(" + mem[reg[idx]] + ")");
					mem[reg[idx]] = reg[idy];
					System.out.println(" = MEM" + reg[idx] + "(" + mem[reg[idx]] + ")");
					progCnt++;
				} else if (frommem == 1) {
					System.out.println(frommem + " " + tomem);
					System.out.print("MOVE: MEM" + reg[idy] + "(" + mem[reg[idy]] + ")" + " -> R"+ idx + "(" + reg[idx] + ")");
					reg[idx] = mem[reg[idy]];
					System.out.println(" = R" + idx + "(" + reg[idx] + ")");
					progCnt++;
				} else {
					System.out.println(frommem + " " + tomem);
					System.out.print("MOVE: R" + idy + "(" + reg[idy] + ")" + " -> R"+ idx + "(" + reg[idx] + ")");
					reg[idx] = reg[idy];
					System.out.println(" = R" + idx + "("  + reg[idx] + ")");
					progCnt++;
				}
				break;
			}

			case MUL: {
				reg[idx] *= reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}

			case POP: {
				System.out.println("pop1:" + varStack[varPointer - 1]);
				reg[idx] = varStack[--varPointer];
				System.out.println("pop2:" + varStack[varPointer]);
				progCnt++;
				break;
			}

			case PUSH: {
				System.out.println("push1:" + varStack[varPointer]);
				varStack[varPointer] = reg[idx];
				System.out.println("push2:" + varStack[varPointer]);
				varPointer++;
				progCnt++;
				break;
			}

			case RTS: {
				if (jmpPointer == 0) {
					progCnt = 4096;
				} else {
					progCnt = jmpStack[--jmpPointer] + 1;
				}
				break;
			}

			case SUB: {
				reg[idx] -= reg[idy];
				progCnt++;
				System.out.println(reg[idx]);
				break;
			}

			default: {
				progCnt++;
				break;
			}

			}

		}
	}

}
