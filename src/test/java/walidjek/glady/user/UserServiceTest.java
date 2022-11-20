package walidjek.glady.user;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import walidjek.glady.deposit.Deposit;
import walidjek.glady.deposit.DepositType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final int USER_ID = 987;

    @Mock
    UserRepository userRepository;


    @Test
    @DisplayName("Compute user balance")
    void computeUserBalance() {
        UserService userService = new UserService(userRepository);

        User user = new User();

        Deposit giftDeposit1 = new TestDeposit(100, DepositType.GIFT, true);
        Deposit giftDeposit2 = new TestDeposit(30, DepositType.GIFT, true);
        Deposit giftDeposit3 = new TestDeposit(200, DepositType.GIFT, false);

        Deposit mealDeposit1 = new TestDeposit(40, DepositType.MEAL, true);
        Deposit mealDeposit2 = new TestDeposit(20, DepositType.MEAL, true);
        Deposit mealDeposit3 = new TestDeposit(10, DepositType.MEAL, false);

        user.addDeposit(giftDeposit1);
        user.addDeposit(giftDeposit2);
        user.addDeposit(giftDeposit3);
        user.addDeposit(mealDeposit1);
        user.addDeposit(mealDeposit2);
        user.addDeposit(mealDeposit3);

        when(userRepository.get(USER_ID)).thenReturn(Optional.of(user));

        int giftBalance = userService.getBalance(USER_ID, DepositType.GIFT);
        assertEquals(giftBalance, 130); //  giftDeposit1(100) + giftDeposit2(30)

        int mealBalance = userService.getBalance(USER_ID, DepositType.MEAL);
        assertEquals(mealBalance, 60); // mealDeposit1(40) + mealDeposit2(20)
    }

    @Test
    @DisplayName("Get balance of unknown user")
    void getBalanceOfUnknownUser() {
        UserService userService = new UserService(userRepository);
        when(userRepository.get(111)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getBalance(111, DepositType.GIFT));
    }
}
