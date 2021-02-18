public class Main {

	public static void main(String[] args) {
		System.out.println(Library.isPrime(7));
		System.out.println(Library.smallerPrime(120));
		System.out.println(Library.primeFactorization(1440));
		System.out.println(Library.gcd(6, 9));
		System.out.println(Library.gcd(9, 15, 18));
		System.out.println(Library.lcm(6, 9));
		System.out.println(Library.lcm(9, 15, 18));
		System.out.println(Library.EulerTotientFunction(6));
		System.out.println(Library.isdihedralPrime(121151));
		KnightTour.printSolution();		
	}
}
