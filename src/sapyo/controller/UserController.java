package sapyo.controller;

import sapyo.objects.User;
import sapyo.support.Result;
import easyjdbc.query.QueryExecuter;
import easymapping.annotation.Controller;
import easymapping.annotation.Post;
import easymapping.mapping.Http;
import easymapping.response.Json;
import easymapping.response.Response;

@Controller
public class UserController {

	@Post("/user/register.json")
	public Response register(Http http){
		User user = http.getJsonObject(User.class, "user");
		QueryExecuter qe = new QueryExecuter();
		int result = qe.insert(user);
		qe.close();
		if(result==0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}
	
	@Post("/user/modify.json")
	public Response modify(Http http){
		User user = http.getJsonObject(User.class, "user");
		QueryExecuter qe = new QueryExecuter();
		int result = qe.update(user);
		qe.close();
		if(result==0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}
	
	@Post("/user/login.json")
	public Response login(Http http){
		User user = http.getJsonObject(User.class, "user");
		System.out.println(user);
		QueryExecuter qe = new QueryExecuter();
		User fromDB = qe.get(User.class, user.getEmail());
		System.out.println(fromDB);
		qe.close();
		if(!fromDB.matchPassword(user.getPassword()))
			return new Json(new Result(false, null));
		http.setSessionAttribute("user", fromDB);
		return new Json(new Result(true, null));
	}
	
	@Post("/myinfo/getlist.json")
	public Response getlist(Http http){
		//세션 로직
		return null;
	}
	
	
}
