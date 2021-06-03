package com.player.game.controllers;

import com.player.framework.annotation.message.Controller;
import com.player.framework.annotation.message.RequestMapping;
import com.player.framework.net.IdSession;
import com.player.game.messages.login.ReqGuestLogin;
import com.player.game.messages.login.ReqUserLogin;
import com.player.game.servers.LoginServer;

@Controller
public class LoginController {

	@RequestMapping
	public void reqGuestLogin(IdSession session, ReqGuestLogin request) {
		LoginServer.getInstance().reqGuestLogin(session, request);
	}

	@RequestMapping
	public void reqUserLogin(IdSession session, ReqUserLogin request) {
		LoginServer.getInstance().reqUserLogin(session, request);
	}

}
