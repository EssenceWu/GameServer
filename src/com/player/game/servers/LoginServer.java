package com.player.game.servers;

import org.apache.ibatis.session.SqlSession;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.SessionProperty;
import com.player.framework.orm.OrmFactory;
import com.player.framework.util.ToolUtil;
import com.player.game.Config;
import com.player.game.Resonpose;
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
		SqlSession sqlSession = OrmFactory.INSTANCE.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserModel user = userMapper.getUserbyGuestKey(request.guestKey);
		sqlSession.commit();
		sqlSession.close();
		if (user == null) {
			user = new UserModel();
			user.setUname("сн©м" + ToolUtil.getRandom(1000, 9999));
			user.setUnick(Config.name[ToolUtil.getRandom(0, 9)]);
			user.setGuestKey(request.guestKey);
			user.setIsGuest(1);
			user.setStatus(1);
			userMapper.addUser(user);
			session.setAttribute(SessionProperty.UID, user.getId());
			uinfo.unick = user.getUnick();
			resGuestLogin.uinfo = uinfo;
		}else {
			if (user.getStatus() == 0) {
				resGuestLogin.status = Resonpose.UserIsFreeze;
				resGuestLogin.uinfo = null;
			}else if (user.getIsGuest() != 1) {
				resGuestLogin.status = Resonpose.UserIsNotGuest;
				resGuestLogin.uinfo = null;
			} else {
				resGuestLogin.status = Resonpose.OK;
				uinfo.unick = user.getUnick();
				resGuestLogin.uinfo = uinfo;
			}
		}
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
		SqlSession sqlSession = OrmFactory.INSTANCE.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserModel user = userMapper.getUserByUname(request.uname);
		sqlSession.close();
		if (user == null || !request.upwd.equals(user.getUpwd())) {
			resUserLogin.status = Resonpose.UnameOrUpwdError;
			resUserLogin.uinfo = null;
		}else {
			if (user.getStatus() == 0) {
				resUserLogin.status = Resonpose.UserIsFreeze;
				resUserLogin.uinfo = null;
			}else if (user.getIsGuest() == 1) {
				resUserLogin.status = Resonpose.UserIsNotGuest;
				resUserLogin.uinfo = null;
			}else {
				resUserLogin.status = Resonpose.OK;
				uinfo.unick = user.getUnick();
				resUserLogin.uinfo = uinfo;
				session.setAttribute(SessionProperty.UID, user.getId());
			}
		}
		MessageRouter.send(session, resUserLogin);
	}

}
