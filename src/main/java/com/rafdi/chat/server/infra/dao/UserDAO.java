package com.rafdi.chat.server.infra.dao;

import com.rafdi.chat.server.model.user.User;

public interface UserDAO {
	public void save(User user);

	public User get(Object userName);

}
