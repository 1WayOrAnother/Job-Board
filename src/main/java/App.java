import java.util.ArrayList;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import models.Job;

import static spark.Spark.*;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/", (request, response) ->{
//            Job.clearAll();
            Map<String, Object> model = new HashMap<>();
            ArrayList allJobs = Job.getAll();
            model.put("allJobs", allJobs);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           String title = request.queryParams("title");
           String description = request.queryParams("description");
           String contactInfo = request.queryParams("contactInfo");
           Job newJob = new Job(title, description, contactInfo);
           ArrayList allJobs = Job.getAll();
           model.put("allJobs", allJobs);
           return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList allJobs = Job.getAll();
            model.put("allJobs", allJobs);
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfJobToFind = Integer.parseInt(request.params("id"));
            Job foundJob = Job.findById(idOfJobToFind);
            model.put("job", foundJob);
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("jobs/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfJobToDelete = Integer.parseInt(request.params("id"));
            Job deleteJob = Job.findById(idOfJobToDelete);
            deleteJob.deleteJob();
//            ArrayList allJobs = Job.getAll();
//            model.put("allJobs", allJobs);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
