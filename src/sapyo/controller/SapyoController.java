package sapyo.controller;

import sapyo.objects.Contents;
import sapyo.objects.Sapyo;
import sapyo.support.Result;
import easyjdbc.query.QueryExecuter;
import easymapping.annotation.Controller;
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
		int result = qe.insert(sapyo);
		if (contents != null) {
			Contents con = new Contents();
			con.setId(sapyo.getId());
			con.setContents(contents);
			result += qe.insert(con);
		}
		qe.close();
		if(result==0)
			return new Json(new Result(false, null));
		return new Json(new Result(true, null));
	}

}
