import static spark.Spark.*;

import models.Animal;
import models.EndangeredAnimal;
import models.Sightings;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
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
            String name = request.queryParams("name");
            String ranger = request.queryParams("ranger");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            if(age == null && health == null) {
                Animal animal = new Animal(name);
                animal.save();
            }
            else{
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name);
                endangeredAnimal.save();
                endangeredAnimal.saveAge(age);
                endangeredAnimal.saveHealth(health);
            }
            Sightings sightings = new Sightings(location,ranger, animalId);
            sightings.save();
            response.redirect("/all-animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("all-animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal>animals = Animal.all();
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.all();
            List<Sightings> sightings = Sightings.all();
            model.put("sightings", sightings);
            model.put("animals", animals);
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "all-animals.hbs");
        }, new HandlebarsTemplateEngine() );

        get("/sightings",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sightings> sightings = Sightings.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
