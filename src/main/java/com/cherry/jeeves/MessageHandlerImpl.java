package com.cherry.jeeves;

import com.cherry.jeeves.domain.shared.ChatRoomMember;
import com.cherry.jeeves.domain.shared.Contact;
import com.cherry.jeeves.domain.shared.Message;
import com.cherry.jeeves.domain.shared.RecommendInfo;
import com.cherry.jeeves.message.handler.ChatRoomMessageHandler;
import com.cherry.jeeves.message.handler.PrivateMessageHandler;
import com.cherry.jeeves.service.MessageHandler;
import com.cherry.jeeves.service.WechatHttpService;
import com.cherry.jeeves.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MessageHandlerImpl implements MessageHandler {

    @Autowired
    private WechatHttpService wechatHttpService;

    @Autowired
    private PrivateMessageHandler privateMessageHandler;

    @Resource
    private ChatRoomMessageHandler chatRoomMessageHandler;

    @Override
    public void onReceivingChatRoomTextMessage(Message message) {
        log.info("onReceivingChatRoomTextMessage");
        log.info("from chatroom: " + message.getFromUserName());
        log.info("from person: " + MessageUtils.getSenderOfChatRoomTextMessage(message.getContent()));
        log.info("to: " + message.getToUserName());
        log.info("content:" + MessageUtils.getChatRoomTextMessageContent(message.getContent()));
        try {
            chatRoomMessageHandler.handle(message);
        } catch (Exception e) {

        }
    }

    @Override
    public void onReceivingChatRoomImageMessage(Message message, String thumbImageUrl, String fullImageUrl) {
        log.info("onReceivingChatRoomImageMessage");
        log.info("thumbImageUrl:" + thumbImageUrl);
        log.info("fullImageUrl:" + fullImageUrl);
    }

    @Override
    public void onReceivingPrivateTextMessage(Message message) {
        log.info("onReceivingPrivateTextMessage");
        log.info("from: " + message.getFromUserName());
        log.info("to: " + message.getToUserName());
        log.info("content:" + message.getContent());

        try {
            privateMessageHandler.handle(message);
        } catch (Exception e) {

        }
//        将原文回复给对方
//        replyMessage(message);
    }

    @Override
    public void onReceivingPrivateImageMessage(Message message, String thumbImageUrl, String fullImageUrl) {
        log.info("onReceivingPrivateImageMessage");
        log.info("thumbImageUrl:" + thumbImageUrl);
        log.info("fullImageUrl:" + fullImageUrl);
//        将图片保存在本地
//        byte[] data = wechatHttpService.downloadImage(thumbImageUrl);
//        FileOutputStream fos = new FileOutputStream("thumb.jpg");
//        fos.write(data);
//        fos.close();
    }

    @Override
    public boolean onReceivingFriendInvitation(RecommendInfo info) {
        log.info("onReceivingFriendInvitation");
        log.info("recommendinfo content:" + info.getContent());
//        默认接收所有的邀请
        return true;
    }

    @Override
    public void postAcceptFriendInvitation(Message message) throws IOException {
        log.info("postAcceptFriendInvitation");
//        将该用户的微信号设置成他的昵称
//        String content = StringEscapeUtils.unescapeXml(message.getContent());
//        ObjectMapper xmlMapper = new XmlMapper();
//        FriendInvitationContent friendInvitationContent = xmlMapper.readValue(content, FriendInvitationContent.class);
//        wechatHttpService.setAlias(message.getRecommendInfo().getUserName(), friendInvitationContent.getFromusername());
    }

    @Override
    public void onChatRoomMembersChanged(Contact chatRoom, Set<ChatRoomMember> membersJoined, Set<ChatRoomMember> membersLeft) {
        log.info("onChatRoomMembersChanged");
        log.info("chatRoom:" + chatRoom.getUserName());
        if (membersJoined != null && membersJoined.size() > 0) {
            log.info("membersJoined:" + membersJoined.stream()
                    .map(ChatRoomMember::getNickName)
                    .collect(Collectors.joining(",")));
        }
        if (membersLeft != null && membersLeft.size() > 0) {
            log.info("membersLeft:" + membersLeft.stream()
                    .map(ChatRoomMember::getNickName)
                    .collect(Collectors.joining(",")));
        }
    }

    @Override
    public void onNewChatRoomsFound(Set<Contact> chatRooms) {
        log.info("onNewChatRoomsFound");
        chatRooms.forEach(x -> log.info(x.getUserName()));
    }

    @Override
    public void onChatRoomsDeleted(Set<Contact> chatRooms) {
        log.info("onChatRoomsDeleted");
        chatRooms.forEach(x -> log.info(x.getUserName()));
    }

    @Override
    public void onNewFriendsFound(Set<Contact> contacts) {
        log.info("onNewFriendsFound");
        contacts.forEach(x -> {
            log.info(x.getUserName());
            log.info(x.getNickName());
        });
    }

    @Override
    public void onFriendsDeleted(Set<Contact> contacts) {
        log.info("onFriendsDeleted");
        contacts.forEach(x -> {
            log.info(x.getUserName());
            log.info(x.getNickName());
        });
    }

    @Override
    public void onNewMediaPlatformsFound(Set<Contact> mps) {
        log.info("onNewMediaPlatformsFound");
    }

    @Override
    public void onMediaPlatformsDeleted(Set<Contact> mps) {
        log.info("onMediaPlatformsDeleted");
    }

    @Override
    public void onRedPacketReceived(Contact contact) {
        log.info("onRedPacketReceived");
        if (contact != null) {
            log.info("the red packet is from " + contact.getNickName());
        }
    }

    private void replyMessage(Message message) throws IOException {
        wechatHttpService.sendText(message.getFromUserName(), message.getContent());
    }
}
