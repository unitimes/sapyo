package easyjdbc.query;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import sapyo.objects.Reason;
import sapyo.objects.Sapyo;
import sapyo.objects.User;

public class QueryExecuterTest {

	@Test
	public void test() {
		QueryExecuter qe = new QueryExecuter();
		Date now = new Date();
		String format1 = new SimpleDateFormat("yyyy-MM-dd").format(now);
		long count = qe.getCount(Sapyo.class, "submittime BETWEEN '" + format1 + " 00:00:00' AND '" + format1 + " 23:59:59'");
		System.out.println(count);
	}

	@Test
	public void testwhere() {
User		user =new User();
		//if (user == null)
			//return null;
		user.setEmail("zerohouse@korea.com");
		Sapyo sapyo = new Sapyo();
		sapyo.setSubmittime(new Date());
		sapyo.setEmail(user.getEmail());
		QueryExecuter qe = new QueryExecuter();
		int result = qe.insertAndGetPrimaryKey(sapyo).intValue();
			Reason reason = new Reason();
			reason.setId(result);
			reason.setReason("wer");
			result += qe.insert(reason);
		qe.close();
	}

}
