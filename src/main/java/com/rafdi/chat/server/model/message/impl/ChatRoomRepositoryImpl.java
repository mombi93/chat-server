package com.rafdi.chat.server.model.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafdi.chat.server.infra.dao.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.Message;

@Component
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

	private ChatRoomDAO dao;
	private ChatRoomFactory chatRoomFactory;

	@Autowired
	public ChatRoomRepositoryImpl(ChatRoomFactory chatRoomFactory,
			ChatRoomDAO dao) {
		this.dao = dao;
		this.chatRoomFactory = chatRoomFactory;
	}

	@Override
	public void saveChatRoom(ChatRoom chatRoom) throws InvalidChatRoomException {
		if (chatRoom == null) {
			throw new InvalidChatRoomException("Chat room can't be null");
		} else if (chatRoom.getChatRoomName() == null) {
			throw new InvalidChatRoomException("Chat room name can't be null");
		}
		dao.saveChatRoom(chatRoom);
	}

	@Override
	public ChatRoom findChatRoomByName(String chatRoomName)
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		if (chatRoomName == null) {
			throw new InvalidChatRoomRepositoryException(
					"can't find a chat room with null chat room name!");
		}
		ChatRoom chatRoom = dao.getChatRoom(chatRoomName);
		if (chatRoom == null) {
			chatRoom = createChatRoom(chatRoomName);
		}
		return chatRoom;
	}

	public ChatRoom createChatRoom(String chatRoomName)
			throws InvalidChatRoomException {
		ChatRoom chatRoom;
		chatRoom = chatRoomFactory.createChatRoom(chatRoomName);
		dao.saveChatRoom(chatRoom);
		chatRoom = dao.getChatRoom(chatRoomName);
		return chatRoom;
	}

	@Override
	public void saveMessage(ChatRoom chatRoom, Message message) {
		dao.saveMessage(chatRoom, message);
	}

}
