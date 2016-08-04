package runner;

import products.Milk;

public class Runner {

	public static void main(String[] args) {

		Milk milk1 = new Milk("Falusi", 299, "2016-11-20", 1.5, 20.5);
		Milk milk2 = new Milk("Riska", 240, "2016-05-10", 1.5, 19.5);
		Milk milk3 = new Milk("Mizo", 320, "2016-06-15", 1.5, 5.5);
		Milk milk4 = new Milk("Spar", 199, "2016-08-20", 1.5, 10);
		Milk milk5 = new Milk("Milk", 209, "2016-12-20", 1.5, 2.5);

		System.out.println(milk1.toString() + "\n" + milk2.toString() + "\n" + milk3.toString() + "\n"
				+ milk4.toString() + "\n" + milk5.toString() + "\n");
	}
}
