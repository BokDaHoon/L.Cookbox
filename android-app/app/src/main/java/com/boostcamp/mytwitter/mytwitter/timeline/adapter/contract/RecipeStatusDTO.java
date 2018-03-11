package com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DaHoon on 2017-07-24.
 */

public class RecipeStatusDTO implements Serializable {
    @SerializedName("REP_ID")
    private String recipeId;
    @SerializedName("REP_ENM")
    private String repEname;
    @SerializedName("REP_NM")
    private String recipeName;
    @SerializedName("REP_LCLS_ID")
    private String repLclsId;
    @SerializedName("REP_MCLS_ID")
    private String repMclsId;
    @SerializedName("REP_SCLS_ID")
    private String repSclsId;
    @SerializedName("IMG_URL")
    private String imageUrl;
    @SerializedName("MOV_URL")
    private String movieUrl;
    @SerializedName("REP_DESC")
    private String recipeDescription;
    @SerializedName("FVR_COUNT")
    private int favoriteCount;
    @SerializedName("INQ_COUNT")
    private int inqueryCount;
    @SerializedName("PRICE")
    private int price;

    public String getRecipeId() {
        return recipeId;
    }

    public RecipeStatusDTO setRecipeId(String recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getRepEname() {
        return repEname;
    }

    public RecipeStatusDTO setRepEname(String repEname) {
        this.repEname = repEname;
        return this;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public RecipeStatusDTO setRecipeName(String recipeName) {
        this.recipeName = recipeName;
        return this;
    }

    public String getRepLclsId() {
        return repLclsId;
    }

    public RecipeStatusDTO setRepLclsId(String repLclsId) {
        this.repLclsId = repLclsId;
        return this;
    }

    public String getRepMclsId() {
        return repMclsId;
    }

    public RecipeStatusDTO setRepMclsId(String repMclsId) {
        this.repMclsId = repMclsId;
        return this;
    }

    public String getRepSclsId() {
        return repSclsId;
    }

    public RecipeStatusDTO setRepSclsId(String repSclsId) {
        this.repSclsId = repSclsId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RecipeStatusDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public RecipeStatusDTO setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
        return this;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public RecipeStatusDTO setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
        return this;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public RecipeStatusDTO setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
        return this;
    }

    public int getInqueryCount() {
        return inqueryCount;
    }

    public RecipeStatusDTO setInqueryCount(int inqueryCount) {
        this.inqueryCount = inqueryCount;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RecipeStatusDTO setPrice(int price) {
        this.price = price;
        return this;
    }
}
