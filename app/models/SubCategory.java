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
public class SubCategory extends Model{

	@Id
	private Long id;
	@Required
	private String name;
	@Required
	private String description;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;
	@ManyToOne
	@JsonIgnore
	private Category category;
	
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

	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public JsonNode toJson(){
		return Json.toJson(this);
	}
	
	public static JsonNode toJsonArray(List<SubCategory> jsonArray){
		return Json.toJson(jsonArray);
	}
	private static final Find<Long,SubCategory> find = new Find<Long,SubCategory>(){};
	public static Find<Long, SubCategory> getFind() {
		return find;
	}
	public static SubCategory findById(Long id){
		return find.byId(id);
	}
	public static List<SubCategory> findByName(String name){
		return find.where().eq("name", name).findList();
	}
	
	public static List<SubCategory> findPage(Integer page, Integer count){
		return find.setFirstRow(page * count).setMaxRows(count).findList();
	}
	
}
