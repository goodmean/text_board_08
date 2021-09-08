package com.sbs.example.textBoard.session;

import com.sbs.example.textBoard.dto.Member;

public class Session {
	public int loginedMemberId;

	public Member loginedMember = null;

	public Session() {
		loginedMemberId = -1;
	}

	public boolean isLogined() {

		return loginedMemberId != -1;
	}

	public void logout() {
		loginedMemberId = -1;
		loginedMember = null;
		
		System.out.println("로그아웃 되었습니다.");
	}

	public void login(Member member) {
		loginedMemberId = member.id;
		loginedMember = member;
	}

}