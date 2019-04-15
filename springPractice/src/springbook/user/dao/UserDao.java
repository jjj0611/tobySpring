package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public abstract class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();

		PreparedStatement ps = c.prepareStatement(
			"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}


	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}
	
	// 공통된 기능을 담당하는 메소드로 중복된 코드를 뽑아내는 것을 리팩토링에서는 메서드 추출기법(extract method)이라고 부른다.
	abstract public Connection getConnection() throws ClassNotFoundException, SQLException;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new NUserDao();

		User user = new User();
		user.setId("jj0611");
		user.setName("Jaeju,Jang");
		user.setPassword("notmarried");

		dao.add(user);
			
		System.out.println(user.getId() + " 등록성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " 조회 성공");
	}

}
