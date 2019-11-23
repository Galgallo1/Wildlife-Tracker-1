import static spark.Spark.*;

import models.Animal;
import models.EndangeredAnimal;
import models.Sightings;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/wildlife/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "wildlife.hbs");
        }, new HandlebarsTemplateEngine());

        post("/wildlife/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            if(age == null && health == null) {
                Animal animal = new Animal("lion");
                animal.save();
            }
            else{
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal("monkey");
                endangeredAnimal.save();
                endangeredAnimal.saveAge(age);
                endangeredAnimal.saveHealth(health);
            }
            Sightings sightings = new Sightings(location,ranger, animalId);
            sightings.save();
            response.redirect("/wildlife/new");
            return null;
        }, new HandlebarsTemplateEngine());

//        get("all-animals", (request, response) -> {}, );
    }
}
