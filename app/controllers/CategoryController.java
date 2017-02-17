package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Category;
import models.Product;
import models.SubCategory;
import models.User;
import play.data.Form;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;


public class CategoryController extends Controller{

	public Result retrieveCategories(Integer page, Integer count) {
		 
		 List<Category> categoryList = Category.findPage(page, count);
		 
		 if (request().accepts("application/json")) {
				JsonNode node = Category.toJsonArray(categoryList);
				return ok(node);
		}	
		 
		 else if (request().accepts("application/xml")) {
				return ok(views.xml.categories.render(categoryList));
		}
		 			

			return Results.status(406);
	}	 
	
	public Result retrieveSubCategoriesByCategory(Long categoryId) {
		 
		Category category = Category.findById(categoryId);
		 List<SubCategory> subCategoryList = category.getSubcategory();
		 
		 if (request().accepts("application/json")) {
				JsonNode node = SubCategory.toJsonArray(subCategoryList);
				return ok(node);
		}	
		 
		 else if (request().accepts("application/xml")) {
				return ok(views.xml.subcategories.render(subCategoryList));
		}
		 			

			return Results.status(406);
	}	 
	
	public Result retrieveProductsBySubCategory(Long subcategoryId) {
		 
		SubCategory subcategory = SubCategory.findById(subcategoryId);
		 List<Product> productList = subcategory.getProducts();
		 
		 if (request().accepts("application/json")) {
				JsonNode node = Product.toJsonArray(productList);
				return ok(node);
		}	
		 
		 else if (request().accepts("application/xml")) {
				return ok(views.xml.products.render(productList));
		}
		 			

			return Results.status(406);
	}	

	
}
