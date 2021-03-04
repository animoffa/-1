package com.labs.lab1;
//Класс проверяет является ли введенная строка палиндромом
public class Palindrome {
	public static void main(String[] args) {
		 
		for (int i = 0; i < args.length; i++) {
			 String s = args[i];
			 System.out.println(s);
			 System.out.println(isPalindrome(s));
			 System.out.println("\n");
			}
		 	
		}
//Метод переворачивает строку
	public static String reverseString(String s)
	{
		String str="";
		for (int i=s.length()-1; i>=0;i--) {
			str+=s.charAt(i);
		}
		return(str);
	}
	
	//Метод проверяет является ли строка палиндромом, с помощью метода reverseString
	public static boolean isPalindrome(String s)
	{
		return s.equals(reverseString(s));
	}
}
