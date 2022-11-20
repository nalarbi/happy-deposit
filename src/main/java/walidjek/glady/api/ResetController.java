package walidjek.glady.api;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import walidjek.glady.DataInitializer;

@Controller("/api/reset")
public class ResetController {

    private final DataInitializer dataInitializer;

    public ResetController(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    @Post
    public void reset() {
        this.dataInitializer.reset();
    }

}
