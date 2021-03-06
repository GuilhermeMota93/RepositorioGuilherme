import static spark.Spark.*;
import java.util.ArrayList;

import spark.*;

public class Main {

	static String username;

	public static void main(String[] args) {

		final ArrayList<String> usernames = new ArrayList<String>();

		usernames.add("joao");
		usernames.add("luis");
		usernames.add("guilherme");
		usernames.add("joel");
		usernames.add("nuno");
		usernames.add("vitor");
		usernames.add("david");

		get(new Route("/users/:username") {
			@Override
			public Object handle(Request request, Response response) {

				username = request.params(":username");

				username = username.toLowerCase();

				if (usernames.contains(username))
					return "{\"status\":\"OK\"}";
				else
					return "{\"status\":\"ERROR, NOT FOUND\"}";
			}
		});

	}

}
