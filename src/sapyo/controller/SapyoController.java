package sapyo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sapyo.objects.Reason;
import sapyo.objects.ReasonAgreement;
import sapyo.objects.Sapyo;
import sapyo.objects.User;
import sapyo.support.Result;
import easyjdbc.query.QueryExecuter;
import easyjdbc.query.raw.GetRecordQuery;
import easyjdbc.query.raw.GetRecordsQuery;
import easymapping.annotation.Controller;
import easymapping.annotation.Get;
import easymapping.annotation.Post;
import easymapping.mapping.Http;
import easymapping.response.Json;
import easymapping.response.Response;

@Controller
public class SapyoController {

	@Post("/sapyo/submit.json")
	public Response submit(Http http) {
		User user = http.getSessionAttribute(User.class, "user");
		if (user == null)
			return null;
		user.setEmail("zerohouse@korea.com");
		Sapyo sapyo = new Sapyo();
		sapyo.setSubmittime(new Date());
		sapyo.setEmail(user.getEmail());
		String reasons = http.getParameter("reason");
		QueryExecuter qe = new QueryExecuter();
		int result = qe.insertAndGetPrimaryKey(sapyo).intValue();
		if (reasons != null) {
			Reason reason = new Reason();
			reason.setId(result);
			reason.setReason(reasons);
			result += qe.insert(reason);
		}
		qe.close();
		if (result == 0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}

	@Get("/sapyo/getmylist.json")
	public Response getMyList(Http http) {
		User user = http.getSessionAttribute(User.class, "user");
		if (user == null)
			return null;
		QueryExecuter qe = new QueryExecuter();
		String sql = "select sapyo.id,sapyo.email,sapyo.submittime,reason.reason,user.company,user.familyname, user.class from sapyo inner join reason inner join user on sapyo.id = reason.id and user.email = sapyo.email where user.email=?";
		GetRecordsQuery query = new GetRecordsQuery(7, sql, user.getEmail());
		List<Map<String, Object>> result = query.getMapList(qe.getConn());
		qe.close();
		return new Json(result);
	}

	@Get("/sapyo/getlist.json")
	public Response getList(Http http) {
		QueryExecuter qe = new QueryExecuter();
		String sql = "select sapyo.id,sapyo.email,sapyo.submittime,reason.reason,user.company,user.familyname, user.class from sapyo inner join reason inner join user on sapyo.id = reason.id and user.email = sapyo.email order by sapyo.id desc limit 0,7";
		GetRecordsQuery query = new GetRecordsQuery(7, sql);
		List<Map<String, Object>> result = query.getMapList(qe.getConn());
		qe.close();
		return new Json(result);
	}

	@Get("/sapyo/wheather.json")
	public Response getTodayCount(Http http) {
		User user = http.getSessionAttribute(User.class, "user");
		user = new User();
		user.setCompany("삼성");
		user.setEmail("zerohouse@korea.com");
		QueryExecuter qe = new QueryExecuter();
		Date now = new Date();
		String format1 = new SimpleDateFormat("yyyy-MM-dd").format(now);
		long allcount = qe.getCount(Sapyo.class, "submittime BETWEEN '" + format1 + " 00:00:00' AND '" + format1 + " 23:59:59'");
		GetRecordQuery query = new GetRecordQuery(1,
				"select count(sapyo.id) from sapyo inner join user on sapyo.email=user.email where submittime BETWEEN '" + format1
						+ " 00:00:00' AND '" + format1 + " 23:59:59' and user.company=?", user.getCompany());
		long companycount = (long) qe.execute(query).get(0);
		long users = qe.getCount(User.class);
		long userinmycompany = qe.getCount(User.class, "company=?", user.getCompany());
		HashMap<String, Long> result = new HashMap<String, Long>();
		result.put("all", allcount);
		result.put("my", companycount);
		result.put("allusers", users);
		result.put("myusers", userinmycompany);
		qe.close();
		return new Json(result);
	}

	@Post("/reason/submit.json")
	public Response accept(Http http) {
		User user = http.getSessionAttribute(User.class, "user");
		if (user == null)
			return null;
		ReasonAgreement ra = new ReasonAgreement();
		ra.setEmail(user.getEmail());
		ra.setId(Integer.parseInt(http.getParameter("id")));
		ra.setType(1);
		if (http.getParameter("decline") == null)
			ra.setType(0);
		QueryExecuter qe = new QueryExecuter();
		int result = qe.insert(ra);
		qe.close();
		if (result == 0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}

}
