package walidjek.glady.api.deposit;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.hateoas.JsonError;
import walidjek.glady.deposit.DepositService;
import walidjek.glady.deposit.DepositType;
import walidjek.glady.deposit.InsufficientBalanceException;

@Controller("/api/deposit")
public class DistributionController {

    private final DepositService depositService;

    public DistributionController(DepositService depositService) {
        this.depositService = depositService;
    }

    @Post("/gift")
    public void distributeGift(DistributionRequest distributionRequest) {
        depositService.distribute(
                distributionRequest.getCompanyId(),
                distributionRequest.getUserId(),
                distributionRequest.getAmount(),
                DepositType.GIFT
        );
    }

    @Post("/meal")
    public void distributeMeal(DistributionRequest distributionRequest) {
        depositService.distribute(
                distributionRequest.getCompanyId(),
                distributionRequest.getUserId(),
                distributionRequest.getAmount(),
                DepositType.MEAL
        );
    }

    @Error(exception = InsufficientBalanceException.class)
    HttpResponse<JsonError> InsufficientBalance(InsufficientBalanceException exception) {
        return HttpResponseFactory.INSTANCE.status(HttpStatus.FORBIDDEN, new JsonError(exception.getMessage()));
    }
}
