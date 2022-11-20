package walidjek.glady.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class UserResponse {

    private int id;
    private String name;
    private int giftBalance;
    private int mealBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("gift_balance")
    public int getGiftBalance() {
        return giftBalance;
    }

    public void setGiftBalance(int giftBalance) {
        this.giftBalance = giftBalance;
    }

    @JsonProperty("meal_balance")
    public int getMealBalance() {
        return mealBalance;
    }

    public void setMealBalance(int mealBalance) {
        this.mealBalance = mealBalance;
    }
}
