package springbook.user.dao;

import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDaoTest {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ConnectionMaker connectionMaker = new DConnectionMaker();
		
		UserDao dao = new UserDao(connectionMaker);
		
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