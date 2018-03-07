package me.najmsheikh.buttonchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */


public class Transfer {

    private final String CANDIDATE = "npcompete";

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("user_id")
    @Expose
    private Long userId;
    @SerializedName("candidate")
    @Expose
    private String candidate;

    /**
     * No args constructor for use in serialization
     */
    public Transfer() {
    }

    /**
     * @param amount The amount of the Transfer. Can be negative.
     * @param userId The ID of the User to whom the Transfer was directed.
     */
    public Transfer(String amount, Long userId) {
        super();
        this.amount = amount;
        this.userId = userId;
        this.candidate = CANDIDATE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

}