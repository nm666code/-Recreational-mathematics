import java.util.*;
public class Library {
	public static boolean isPrime(int n) {
		if( n<=1 ) 
			return false;
		
		for (int i=2; i*i<=n; i++ )
			if (n % i == 0) 
				return false;
	    return true;
	}
	
	public static ArrayList<Integer> smallerPrime(int n){
		if(n<=1) 
			return null;
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		for(int i = 2; i <= n;i++ ) 
			ans.add(i);
		
		for(int i = 2; i*i <= n; i++ ) 
			if(isPrime(i))
				for(int j = 2; i*j <= n; j++ )
					if(ans.contains(i*j))
						//如果有質數的倍數就刪掉剩下來的就都會是質數
						ans.remove(ans.indexOf(i*j));
		return ans;
	}
	
	public static ArrayList<Integer> primeFactorization(int n) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int i = 0;
		do{
			//如果質數p能整除n就ans加入p之後n=n/p
			if(n % smallerPrime(n).get(i) == 0) {
				ans.add(smallerPrime(n).get(i));
				n=n/smallerPrime(n).get(i);
			}
			else 
				i++;
		}while(n!=1);
		return ans;
	}
	
	public static int gcd(int a,int b) {
		//if (a<b) a%b=a 相當於是交換
		return a%b!=0?gcd(b ,a%b):b; 
	}
	
	public static int gcd(int... numbers) {
		int ans=numbers[0];
		for(int i=1;i<numbers.length;i++) { 
			ans=gcd(ans,numbers[i]); 
		}
		return ans;
	}
	
    public static int lcm(int m, int n) {
    	return m * n / gcd(m, n);
    }
    
	public static int lcm(int... numbers) {
		int k=numbers[0];
		for(int i=1;i<numbers.length;i++) { 
			k=lcm(k,numbers[i]); 
		}
		return k;
	}
	
	public static int EulerTotientFunction(int n) {
		ArrayList<Integer> ans = new ArrayList<Integer>();		
		for(int i = 1; i <= n;i++ ) 
			ans.add(i);
		//用set過濾重複的質因數
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(primeFactorization(n));
		for(int prime:hashSet) 
			for(int j = 1; prime*j <= n; j++ )
				if(ans.contains(prime*j))
					ans.remove(ans.indexOf(prime*j));
		return ans.size();
	}
	
	public static boolean isdihedralPrime(int n) {
		if(!isPrime(n))
			return false;
		int symmetry = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 0); map.put(1, 1); map.put(2, 5); map.put(5, 2); map.put(8, 8);
		
		while(n>0){
			if(map.containsKey(n%10)){
				symmetry=symmetry*10 + map.get(n%10);
				n=n/10;
			}
			else
				return false;
		}
		
		if(isPrime(symmetry))
			return true;
		
		else
			return false;
	}
	
	public static boolean isPerfectnumber(int n) {
		int sum = 0;
		for(int i = 1; i < n; i++)
			if(n % i == 0)
				sum+=i;
		if(sum==n)
			return true;
		else
			return false;
	}
	
	public static ArrayList<Integer> findFactor(int n){
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++)
			if(n % i == 0)
				ans.add(i);
		return ans;
	}
	
	public static boolean isHappyNumber(int n) {
		int sum = 0;
		while(n>9){
			while(n>0) {
				sum+=Math.pow(n%10, 2);
				n=n/10;
			}
			n = sum;
			sum = 0;
		}
		if(n == 1 || n == 7 ) 
			return true;
		else
			return false;
	}
}
