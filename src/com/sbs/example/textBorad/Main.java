package com.sbs.example.textBorad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>(); 
		
		while(true) {
			System.out.println("명령어) ");
			String command = sc.nextLine().trim();
			
			if(command.equals("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}	
	}

}
