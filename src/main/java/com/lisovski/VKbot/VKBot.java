package com.lisovski.VKbot;


import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import com.lisovski.VKbot.model.dao.HWMessagesDAO;
import com.lisovski.VKbot.model.dao.HWMessagesDAOImpl;
import com.lisovski.VKbot.model.entities.HWMessage;

import java.util.List;

public class VKBot extends LongPollBot {

    @Override
    public void onMessageNew(MessageNew messageNewEvent) {

        Message message = messageNewEvent.getMessage();

        if (message.getText().toLowerCase().equals("/sethw") && message.hasReplyMessage()) {
            try {

                HWMessagesDAO HWMessagesDAOI = new HWMessagesDAOImpl();

                HWMessage HWMessage = new HWMessage().builder()
                        .text(message.getReplyMessage().getText())
                        .peer_id(message.getPeerId())
                        .build();
                try{
                    HWMessagesDAOI.addNew(HWMessage);
                }catch (Exception e){

                }

                List<HWMessage> listHWMessages = HWMessagesDAOI.getByPeerId(message.getPeerId());
                HWMessage HWMessage1 = listHWMessages.get(0);
                HWMessage.setId(HWMessage1.getId());

                try{
                    HWMessagesDAOI.updateById(HWMessage);
                }catch (Exception e){

                }

                vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage("Текущее домашнее задание обновлено.")
                        .execute();
            } catch (VkApiException e) {
                e.printStackTrace();
            }
        } else if (message.getText().toLowerCase().equals("/hw")) {
            try {

                HWMessagesDAOImpl dzMessagesDAOImpl = new HWMessagesDAOImpl();
                HWMessage HWMessage = dzMessagesDAOImpl.getByPeerId(message.getPeerId()).get(0);

                vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage("Текущее домашнее задание:\n" + HWMessage.getText())
                        .execute();

            } catch (VkApiException e) {
                e.printStackTrace();
            }
        } else if (message.getText().toLowerCase().equals("/inf")) {
            try {
                vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage("Комманды бота:\n/sethw - запомнить текст пересланного сообщения как текущее домашнее задание.\n/hw - прислать текст текущего домашнего задания.")
                        .execute();
            } catch (VkApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getAccessToken() {
        return "";
    }

    public static void main(String[] args) throws VkApiException {
        new VKBot().startPolling();
    }
}

