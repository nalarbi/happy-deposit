package walidjek.glady.deposit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    @DisplayName("Gift deposit expiration date")
    void giftDepositExpirationDate() throws ParseException {
        GiftDeposit giftDeposit = this.createGiftDeposit(formatter.parse("30-07-1991"));
        assertEquals(formatter.parse("29-07-1992"),giftDeposit.getExpirationDate());
    }

    @Test
    @DisplayName("Meal deposit expiration date")
    void mealDepositExpirationDate() throws ParseException {
        MealDeposit mealDeposit1 = this.createMealDeposit(formatter.parse("30-07-1991"));
        assertEquals(formatter.parse("29-02-1992"), mealDeposit1.getExpirationDate());

        MealDeposit mealDeposit2 = this.createMealDeposit(formatter.parse("22-07-1992"));
        assertEquals(formatter.parse("28-02-1993"), mealDeposit2.getExpirationDate());

        MealDeposit mealDeposit3 = this.createMealDeposit(formatter.parse("07-01-1991"));
        assertEquals(formatter.parse("28-02-1991"), mealDeposit3.getExpirationDate());
    }

    private GiftDeposit createGiftDeposit(Date date) {
        return new GiftDeposit(0, null, null, date);
    }

    private MealDeposit createMealDeposit(Date date) {
        return new MealDeposit(0, null, null, date);
    }
}
