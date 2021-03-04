package com.labs.lab1;
//Класс проверяет числа от 2 до 100 на простоту
public class Primes {
	public static void main(String[] args) {
		for (int i=2; i<=100;i++) {
			if (isPrime(i)==true) {
				System.out.println(i);
			}
		}
	}
	//Метод проверяет входное число на простоту
	public static boolean isPrime(int n)
	{
		int k=0;
		boolean  prime= false;
		for (int i=2; i<=n;i++) {
			if (n%i==0) {
				k++;
			}
		}
		if(k==1) {
			prime=true;
		}
		return prime;
		
	}
}
