package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.validation.Constraints.Required;
import play.libs.Json;
@Entity

public class Review extends Model {
	
	@Id
	private Long id;
	@Required
	
	private Double stars;
	@Required
	
	private String description;
	@Required
	
	@ManyToOne
	private User user;
	
	public Double getStars() {
		return stars;
	}
	
	public void setStars(Double stars) {
		this.stars = stars;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public JsonNode toJson(){
		return Json.toJson(this);
	}
	
	public static JsonNode toJsonArray(List<Review> jsonArray){
		return Json.toJson(jsonArray);
	}
	private static final Find<Long,Review> find = new Find<Long,Review>(){};
	public static Find<Long, Review> getFind() {
		return find;
	}
	public static Review findById(Long id){
		return find.byId(id);
	}

	public static List<Review> findPage(Integer page, Integer count){
		return find.setFirstRow(page * count).setMaxRows(count).findList();
	}
}
