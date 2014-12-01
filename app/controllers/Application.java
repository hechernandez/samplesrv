package controllers;

import java.io.File;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static File getFile() {
    	return Play.application().getFile("/app/testFile.txt");
    }

}
