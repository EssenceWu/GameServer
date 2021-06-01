package com.player.game.servers;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.PropertySession;
import com.player.framework.orm.OrmAsyncFactory;
import com.player.framework.util.ToolUtil;
import com.player.game.Config;
import com.player.game.Resonpose;
import com.player.game.cache.GuestKeyCache;
import com.player.game.cache.UnameCache;
import com.player.game.mappers.UserMapper;
import com.player.game.messages.login.AccountInfo;
import com.player.game.messages.login.ReqGuestLogin;
import com.player.game.messages.login.ReqUserLogin;
import com.player.game.messages.login.ResGuestLogin;
import com.player.game.messages.login.ResUserLogin;
import com.player.game.models.UserModel;

public class LoginServer {

	public static LoginServer loginServer = new LoginServer();

	public static LoginServer getInstance() {
		return loginServer;
	}

	public void reqGuestLogin(IdSession session, ReqGuestLogin request) {
		ResGuestLogin resGuestLogin = new ResGuestLogin();
		AccountInfo uinfo = new AccountInfo();
		if (request.guestKey == null) {
			resGuestLogin.status = Resonpose.InvalidParams;
			resGuestLogin.uinfo = null;
			MessageRouter.send(session, resGuestLogin);
			return;
		}
		UserModel user = GuestKeyCache.get(request.guestKey);
		if (user == null) {
			user = new UserModel();
			user.setId(ToolUtil.getId());
			user.setUname("сн©м" + ToolUtil.getRandom(1000, 9999));
			user.setUnick(Config.name[ToolUtil.getRandom(0, 9)]);
			user.setGuestKey(request.guestKey);
			user.setIsGuest(1);
			user.setStatus(1);
			int uuid = (int) session.getAttribute(PropertySession.UUID);
			OrmAsyncFactory.notify(uuid, UserMapper.class, "add", user);
			resGuestLogin.status = Resonpose.OK;
			uinfo.unick = user.getUnick();
			resGuestLogin.uinfo = uinfo;
		} else {
			if (user.getStatus() == 0) {
				resGuestLogin.status = Resonpose.UserIsFreeze;
				resGuestLogin.uinfo = null;
			} else if (user.getIsGuest() == 0) {
				resGuestLogin.status = Resonpose.UserIsNotGuest;
				resGuestLogin.uinfo = null;
			} else {
				resGuestLogin.status = Resonpose.OK;
				uinfo.unick = user.getUnick();
				resGuestLogin.uinfo = uinfo;
			}
		}
		session.setAttribute(PropertySession.UID, user.getId());
		MessageRouter.send(session, resGuestLogin);
	}

	public void reqUserLogin(IdSession session, ReqUserLogin request) {
		ResUserLogin resUserLogin = new ResUserLogin();
		AccountInfo uinfo = new AccountInfo();
		if (request.uname == null || request.upwd == null) {
			resUserLogin.status = Resonpose.InvalidParams;
			resUserLogin.uinfo = null;
			MessageRouter.send(session, resUserLogin);
			return;
		}
		UserModel user = UnameCache.get(request.uname);
		if (user == null || !request.upwd.equals(user.getUpwd())) {
			resUserLogin.status = Resonpose.UnameOrUpwdError;
			resUserLogin.uinfo = null;
		} else {
			if (user.getStatus() == 0) {
				resUserLogin.status = Resonpose.UserIsFreeze;
				resUserLogin.uinfo = null;
			} else if (user.getIsGuest() == 1) {
				resUserLogin.status = Resonpose.UserIsNotGuest;
				resUserLogin.uinfo = null;
			} else {
				resUserLogin.status = Resonpose.OK;
				uinfo.unick = user.getUnick();
				resUserLogin.uinfo = uinfo;
				session.setAttribute(PropertySession.UID, user.getId());
			}
		}
		MessageRouter.send(session, resUserLogin);
	}

}
