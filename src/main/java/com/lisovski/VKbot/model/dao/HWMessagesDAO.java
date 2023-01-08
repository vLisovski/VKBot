package com.lisovski.VKbot.model.dao;

import com.lisovski.VKbot.model.entities.HWMessage;

import java.util.List;

public interface HWMessagesDAO {
     List<HWMessage> getByPeerId(int peerId);
     void addNew(HWMessage hwMessage);
     void updateById(HWMessage hwMessage);
}
