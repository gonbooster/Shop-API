package controllers;

import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	 
    public Result index() {
			 String route =  
			"############################# User Routes #############################\n"
			+"get users by id: GET /user/:id\n"
			+"create a user: POST /user/register\n login a user: GET /user/login/:email/:password\n"
			+"login a user: GET /user/login/:email/:password\n"
			+"get all User products: GET /user/products/:id\n"
			+"############################# Product Routes #############################\n"
			+"get all products: GET /products/:page/:count\n"
			+"get product by id: GET /product/:id\n"
			+"create a product: POST /product/register/:userId\n"
			+"get reviews by id: GET /review/:id\n"
			+"create a review: POST /review/register/:userId\n"
			+"############################# Category Routes #############################\n"
			+"get all categories: GET /categories/:page/:count\n"
			+"get all category subcategories: GET /category/subcategories/:id\n"
			+"get all category subcategories: GET /category/subcategory/products/:id";
        return ok(route);
    }

}
