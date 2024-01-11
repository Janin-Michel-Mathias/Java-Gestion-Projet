package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class ApiRest {
    private final Javalin app;

    public ApiRest(Javalin app) {
        this.app = app;
    }

    public void setupEndpoints() {
        // Endpoint GET de test
        app.get("/test-get", this::handleGetTest);

        // Endpoint POST de test
        app.post("/test-post", this::handlePostTest);
    }

    private void handleGetTest(Context ctx) {
        ctx.result("Ceci est un endpoint GET de test");
    }

    private void handlePostTest(Context ctx) {
        String requestBody = ctx.body(); // Récupération du corps de la requête POST
        ctx.result("Ceci est un endpoint POST de test. Donnees recxues : " + requestBody);
    }
}
