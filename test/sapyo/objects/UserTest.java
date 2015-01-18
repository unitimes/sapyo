package sapyo.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import easyjdbc.query.QueryExecuter;

public class UserTest {

	@Test
	public void registerTest() {
		User user = new User("zeroh", "aa", "박", "대리", "넥스트");
		QueryExecuter qe = new QueryExecuter();
		qe.insert(user);
		
	}
	
}
