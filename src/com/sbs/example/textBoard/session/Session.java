package com.sbs.example.textBoard.session;

import com.sbs.example.textBoard.dto.Member;

public class Session {
	public int loginedMemberId;

	public Member loginedMember = null;

	public Session() {
		loginedMemberId = -1;
	}

}