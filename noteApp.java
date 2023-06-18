import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import static spark.Spark.*;

class Note {
    private String title;
    private String content;
    private Date createdDate;

    // Getters and setters omitted for brevity
}

public class NoteApp {
    private List<Note> notes;

    public NoteApp() {
        this.notes = new ArrayList<>();
    }

    public void run() {
        staticFiles.location("/public");

        get("/", (request, response) -> {
            return new ModelAndView(null, "index");
        }, new ThymeleafTemplateEngine());

        post("/add", (request, response) -> {
            String title = request.queryParams("title");
            String content = request.queryParams("content");

            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setCreatedDate(new Date());

            notes.add(note);

            response.redirect("/");
            return null;
        }, new ThymeleafTemplateEngine());
    }

    public static void main(String[] args) {
        NoteApp noteApp = new NoteApp();
        noteApp.run();
    }
}