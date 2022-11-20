package walidjek.glady.api.user;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import walidjek.glady.deposit.DepositType;
import walidjek.glady.user.User;
import walidjek.glady.user.UserService;

@Controller("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Get("/{userId}")
    public UserResponse get(int userId) {
        User user = this.userService.getUser(userId);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());


        int giftBalance = this.userService.getBalance(user, DepositType.GIFT);
        int mealBalance = this.userService.getBalance(user, DepositType.MEAL);
        userResponse.setGiftBalance(giftBalance);
        userResponse.setMealBalance(mealBalance);

        return userResponse;
    }

}
