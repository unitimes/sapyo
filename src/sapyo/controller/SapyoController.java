package sapyo.controller;


import java.util.List;

import sapyo.objects.Contents;
import sapyo.objects.Sapyo;
import sapyo.objects.SapyoWithContents;
import sapyo.support.Result;
import easyjdbc.query.QueryExecuter;
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
		Sapyo sapyo = http.getJsonObject(Sapyo.class, "sapyo");
		String contents = http.getParameter("contents");
		System.out.println(sapyo);
		QueryExecuter qe = new QueryExecuter();
		int result = qe.insertAndGetPrimaryKey(sapyo).intValue();
		if (contents != null) {
			Contents con = new Contents();
			con.setId(result);
			con.setContents(contents);
			result += qe.insert(con);
		}
		qe.close();
		if(result==0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}
	
	@Get("/sapyo/getlist.json")
	public Response getList(Http http){
		QueryExecuter qe = new QueryExecuter();
		List<SapyoWithContents> result= qe.getList(SapyoWithContents.class);
		return new Json(result);
	}

}
