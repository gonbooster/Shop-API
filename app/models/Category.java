package models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.validation.Constraints.Required;
import play.libs.Json;
@Entity
public class Category extends Model{

	@Id
	private Long id;
	@Required
	private String name;
	@Required
	private String description;
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "category")
	private List<SubCategory> subcategory;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<SubCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

	public JsonNode toJson(){
		return Json.toJson(this);
	}
	
	public static JsonNode toJsonArray(List<Category> jsonArray){
		return Json.toJson(jsonArray);
	}
	private static final Find<Long,Category> find = new Find<Long,Category>(){};
	public static Find<Long, Category> getFind() {
		return find;
	}
	public static Category findById(Long id){
		return find.byId(id);
	}
	public static List<Category> findByName(String name){
		return find.where().eq("name", name).findList();
	}
	
	public static List<Category> findPage(Integer page, Integer count){
		return find.setFirstRow(page * count).setMaxRows(count).findList();
	}
	
}
