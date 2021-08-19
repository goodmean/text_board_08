package com.sbs.example.textBorad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>(); 
		int lastArticleId = 0;
		
		while(true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();
			
			if(command.equals("article write")) {
				int id = lastArticleId + 1;
				String title;
				String body;
				
				System.out.println("== 게시글 생성 ==");
				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				
				articles.add(article);
				lastArticleId++;
				
				System.out.println(article);
			}
			else if(command.equals("article list")) {
				System.out.println("== 게시글 리스트 ==");
				
				if(articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호  /  제목");
				
				for(Article article : articles) {
					System.out.printf("%d    /  %s\n", article.id, article.title);
				}
			}
			
			else if(command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
		}	
	}

}
