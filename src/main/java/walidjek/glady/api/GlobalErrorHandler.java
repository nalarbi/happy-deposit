package walidjek.glady.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import walidjek.glady.GladyException;
import walidjek.glady.company.CompanyNotFoundException;
import walidjek.glady.user.UserNotFoundException;

@Controller
public class GlobalErrorHandler {

    @Error(global = true, exception = UserNotFoundException.class)
    HttpResponse<JsonError> userNotFound(UserNotFoundException exception) {
        return HttpResponse.<JsonError>badRequest().body(new JsonError(exception.getMessage()));
    }

    @Error(global = true, exception = CompanyNotFoundException.class)
    HttpResponse<JsonError> companyNotFound(CompanyNotFoundException exception) {
        return HttpResponse.<JsonError>badRequest().body(new JsonError(exception.getMessage()));
    }

    @Error(global = true, exception = GladyException.class)
    HttpResponse<JsonError> companyNotFound(GladyException exception) {
        return HttpResponse.<JsonError>serverError().body(new JsonError(exception.getMessage()));
    }
}
