package com.player.game.messages.player;

import com.player.framework.annotation.message.MessageMeta;
import com.player.framework.serializer.Message;
import com.player.game.Cmd;
import com.player.game.Module;

@MessageMeta(module = Module.PLAYER, cmd = Cmd.ReqRuckSack)
public class ReqRuckSack extends Message {

}
