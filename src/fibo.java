public class fibo {

	public static void main(String[] args) {

		int i = 20;
		int a[] = new int[20];
		a[0] = fibos(i);
		System.out.println(a[0]);

	}

	public static int fibos(int i) {
		if (i <= 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		} else {
			return fibos(i - 2) + fibos(i - 1);
		}
	}
}
