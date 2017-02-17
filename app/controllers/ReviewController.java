package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import helpers.JsonHelper;
import models.Password;
import models.Product;
import models.Review;
import models.User;
import play.cache.CacheApi;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import views.html.*;

public class ReviewController extends Controller{
	
	@Inject
	private FormFactory formFactory;
	
	@Inject
	private CacheApi cache;

    public Result retrieveReview(Long id) {
		
        Review review = cache.get("review-" + id);
        if (review == null) {
            review = review.findById(id);
            cache.set("review-" + id, review);
        }

        if (review == null) {
            return notFound();
        }

        else if (request().accepts("application/json")) {
            JsonNode node = cache.get("review-" + id + "-json");
            if (node == null) {
                node = review.toJson();
                cache.set("review-" + id + "-json", node, 60);
            }
            return ok(node);
        }

        return Results.status(406);
    }
    
	@Transactional
	public Result createReview(Long userId) {
		
		Form<Review> r = formFactory.form(Review.class).bindFromRequest();
		if (r.hasErrors()) {
			return Results.badRequest(r.errorsAsJson());
		}
		if (User.findById(userId) == null){
			return Results.badRequest("User id dont exist");
		}
		Review review = r.get();
		review.setUser(User.findById(userId));
		review.save();
		

		return Results.status(CREATED, review.toJson());
	}

}
