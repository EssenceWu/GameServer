package com.player.game.servers;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.PropertySession;
import com.player.game.Resonpose;
import com.player.game.cache.GuestKeyCache;
import com.player.game.cache.UnameCache;
import com.player.game.messages.login.AccountInfo;
import com.player.game.messages.login.ReqGuestLogin;
import com.player.game.messages.login.ReqUserLogin;
import com.player.game.messages.login.ResGuestLogin;
import com.player.game.messages.login.ResUserLogin;
import com.player.game.models.User;

public class LoginServer {

	public static LoginServer loginServer = new LoginServer();

	public static LoginServer getInstance() {
		return loginServer;
	}

	public void reqGuestLogin(IdSession session, ReqGuestLogin request) {
		ResGuestLogin res = new ResGuestLogin();
		AccountInfo uinfo = new AccountInfo();
		if (request.guestKey == null) {
			res.status = Resonpose.InvalidParams;
			res.uinfo = null;
			MessageRouter.send(session, res);
			return;
		}
		User user = GuestKeyCache.get(request.guestKey);
		if (user == null) {
			user = GuestKeyCache.add(session, request.guestKey);
			session.setAttribute(PropertySession.UID, user.getId());
			res.status = Resonpose.OK;
			uinfo.unick = user.getUnick();
			res.uinfo = uinfo;
			MessageRouter.send(session, res);
			return;
		}
		if (user.getStatus() == 0) {
			res.status = Resonpose.UserIsFreeze;
			res.uinfo = null;
		} else if (user.getIsGuest() == 0) {
			res.status = Resonpose.UserIsNotGuest;
			res.uinfo = null;
		} else {
			session.setAttribute(PropertySession.UID, user.getId());
			res.status = Resonpose.OK;
			uinfo.unick = user.getUnick();
			res.uinfo = uinfo;
		}
		MessageRouter.send(session, res);
	}

	public void reqUserLogin(IdSession session, ReqUserLogin request) {
		ResUserLogin res = new ResUserLogin();
		AccountInfo uinfo = new AccountInfo();
		if (request.uname == null || request.upwd == null) {
			res.status = Resonpose.InvalidParams;
			res.uinfo = null;
			MessageRouter.send(session, res);
			return;
		}
		User user = UnameCache.get(request.uname);
		if (user == null || !request.upwd.equals(user.getUpwd())) {
			res.status = Resonpose.UnameOrUpwdError;
			res.uinfo = null;
			MessageRouter.send(session, res);
			return;
		}
		if (user.getStatus() == 0) {
			res.status = Resonpose.UserIsFreeze;
			res.uinfo = null;
		} else if (user.getIsGuest() == 1) {
			res.status = Resonpose.UserIsNotGuest;
			res.uinfo = null;
		} else {
			res.status = Resonpose.OK;
			uinfo.unick = user.getUnick();
			res.uinfo = uinfo;
			session.setAttribute(PropertySession.UID, user.getId());
		}
		MessageRouter.send(session, res);
	}

}
