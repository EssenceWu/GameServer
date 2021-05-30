package com.player.game.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.net.IdSession;
import com.player.framework.serializer.annotation.Controller;
import com.player.framework.serializer.annotation.RequestMapping;
import com.player.game.messages.login.ReqAccountLogin;
import com.player.game.messages.login.ReqGuestLogin;
import com.player.game.messages.login.ReqUserLogin;
import com.player.game.servers.LoginServer;

@Controller
public class UserLoginController {

	private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@RequestMapping
	public void reqAccountRes(IdSession session, ReqAccountLogin request) {
		logger.info("µÇÂ¼³É¹¦");
	}

	@RequestMapping
	public void reqGuestLogin(IdSession session, ReqGuestLogin request) {
		LoginServer.getInstance().reqGuestLogin(session, request);
	}
	
	@RequestMapping
	public void reqUserLogin(IdSession session, ReqUserLogin request) {
		LoginServer.getInstance().reqUserLogin(session, request);
	}

}
