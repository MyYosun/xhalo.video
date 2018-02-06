package net.xhalo.video.dao;

import net.xhalo.video.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	Integer addUser(User user);

	User validateUser(User user);

	Integer validateUsername(User user);

	User findByUsername(@Param("username") String username);

	void updateLoginTime(User user);
}
