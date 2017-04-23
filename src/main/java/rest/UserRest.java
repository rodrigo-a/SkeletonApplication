package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import bus.UserBus;
import model.User;

@Path("User")
public class UserRest {

	public UserRest() {
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String getList() {
		String rvalue = "";
		try {
			List<User> list = UserBus.findAll();

			Gson gson = new Gson();
			rvalue = gson.toJson(list);

			return rvalue;
		} catch (Exception e) {
			e.printStackTrace();
			rvalue = e.getMessage();
		}

		return rvalue;
	}

	@GET
	@Path("/read/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readUser(@PathParam("id") String id) {
		String rvalue = "";
		try {
			User user = UserBus.loadById(Integer.parseInt(id));

			Gson gson = new Gson();
			rvalue = gson.toJson(user);

			return rvalue;
		} catch (Exception e) {
			e.printStackTrace();
			rvalue = e.getMessage();
		}

		return rvalue;
	}

	@GET
	@Path("/create/{name}/{descrip}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@PathParam("name") String name, @PathParam("descrip") String desc) {
		String rvalue = "";
		try {
			User user = new User();
			user.setName(name);
			user.setDescription(desc);

			UserBus.save(user);

			Gson gson = new Gson();
			rvalue = gson.toJson(user);

			return rvalue;
		} catch (Exception e) {
			e.printStackTrace();
			rvalue = e.getMessage();
		}

		return rvalue;
	}

	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id") String id) {
		String rvalue = "";
		try {
			User user = UserBus.loadById(Integer.parseInt(id));
			if (user != null) {
				UserBus.delete(user);

				Gson gson = new Gson();
				rvalue = gson.toJson(user);
			}
			return rvalue;
		} catch (Exception e) {
			e.printStackTrace();
			rvalue = e.getMessage();
		}

		return rvalue;
	}

	@GET
	@Path("/update/{id}/{name}/{descrip}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@PathParam("id") String id, @PathParam("name") String name,
			@PathParam("descrip") String desc) {
		String rvalue = "";
		try {
			User user = UserBus.loadById(Integer.parseInt(id));
			if (user != null) {
				user.setName(name);
				user.setDescription(desc);

				UserBus.save(user);

				Gson gson = new Gson();
				rvalue = gson.toJson(user);
			}

			return rvalue;
		} catch (Exception e) {
			e.printStackTrace();
			rvalue = e.getMessage();
		}

		return rvalue;
	}
}
