package com.sbs.example.textBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.sbs.example.textBoard.controller.ArticleController;
import com.sbs.example.textBoard.controller.MemberController;

public class App {
	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			// DB 연결 시작
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.err.println("예외 : MySQL 드라이버 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			try {
				conn = DriverManager.getConnection(url, "root", "");

				int actionResult = action(conn, sc, command);

				if (actionResult == -1) {
					break;
				}

			} catch (SQLException e1) {
				System.err.println("예외 : DB에 연결할 수 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// DB 연결 끝

		sc.close();

	}

	private int action(Connection conn, Scanner sc, String command) {

		MemberController memberController = new MemberController(conn, sc);

		ArticleController articleController = new ArticleController(conn, sc);

		if (command.equals("member join")) {
			memberController.join(command);
		} else if (command.equals("article write")) {
			articleController.write(command);
		} else if (command.startsWith("article delete ")) {
			articleController.delete(command);
		} else if (command.startsWith("article detail ")) {
			articleController.showDetail(command);
		} else if (command.startsWith("article modify ")) {
			articleController.modify(command);
		} else if (command.equals("article list")) {
			articleController.showList(command);
		} else if (command.equals("system exit")) {
			System.out.println("== 프로그램 종료 ==");
			return -1;
		}

		return 0;
	}
}