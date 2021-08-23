package com.sbs.example.textBorad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

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

				Connection conn = null;
				PreparedStatement pstat = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					String sql = "INSERT INTO article";
					sql += " SET regDate = NOW()";
					sql += ", updateDate = NOW()";
					sql += ", title = \'" + title + "\'";
					sql += ", `body` = \'" + body + "\';";

					pstat = conn.prepareStatement(sql);
					int affectedRows = pstat.executeUpdate();

					System.out.println("affectedRows : " + affectedRows);

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (pstat != null && !pstat.isClosed()) {
							pstat.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				lastArticleId++;
				
			}
			else if(command.equals("article list")) {
				System.out.println("== 게시글 리스트 ==");
				
				List<Article> articles = new ArrayList<>();

				Connection conn = null;
				PreparedStatement pstat = null;
				ResultSet rs = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					String sql = "SELECT *";
					sql += " FROM article";
					sql += " ORDER BY id DESC;";

//					System.out.println(sql);

					pstat = conn.prepareStatement(sql);
					rs = pstat.executeQuery(sql);

					while (rs.next()) {
						int id = rs.getInt("id");
						String regDate = rs.getString("regDate");
						String updateDate = rs.getString("updateDate");
						String title = rs.getString("title");
						String body = rs.getString("body");

						Article article = new Article(id, regDate, updateDate, title, body);
						articles.add(article);
					}

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (pstat != null && !pstat.isClosed()) {
							pstat.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

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
