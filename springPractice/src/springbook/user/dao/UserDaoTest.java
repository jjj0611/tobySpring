package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
	
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User(); 
		user.setId("jjj0611");
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
