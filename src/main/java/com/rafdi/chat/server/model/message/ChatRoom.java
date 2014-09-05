package com.rafdi.chat.server.model.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rafdi.chat.server.model.user.User;

public class ChatRoom {
	private List<Message> messages;
	private String chatRoomName;
	private Set<User> users;

	public ChatRoom(String chatRoomName) {
		this.chatRoomName = chatRoomName;
		this.messages = new ArrayList<Message>();
		this.users = new HashSet<User>();
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addMessage(Message message) throws InvalidMessageException {
		if (message == null) {
			throw new InvalidMessageException("Message can't be null!");
		}
		messages.add(message);
	}
}