public class fibo {

	static int a[] = new int[21];
	public static void main(String[] args) {

		int i = 20;
		a[0] = 1;
		a[1] = 1;
		int answer = fibos(i);
		System.out.println(answer);
		for (int j = 0; j < a.length; j++) {
			System.out.println(a[j]);
		}
		

	}

	public static int fibos(int i) {
		if (i == 0) {
			return a[0];
		} else if (i == 1) {
			return a[1];
		} else {
			return a[i] = fibos(i - 2) + fibos(i - 1);
		}
	}
}
