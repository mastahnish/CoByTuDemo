package com.example.jacek.cobytudemo;

import java.io.Serializable;

public class MainCourse implements Serializable{

    /**
     *
     */


    private int id; // id kolejno odebranego dania

    private String mainCourseID; // da_id
    private String restaurateurID; // da_uz_id; id restauratora
    private String photoURL; // da_foto (url)
    private String restaurantName; // da_gdzie
    private String mainCourseCategoryID; // da_kategoria; tabela 'kategorie' w db
    private String mainCourseSubCatId; //da_podkategoria

    private String mainCourseType; // da_rodzaj
    private String mainCourseName; // da_nazwa
    private String mainCourseDescription; // da_opis
    private String mainCourseAverageEvaluation; //da_srednia
    private String additionalPrimary; // do_podst | former jsonArray
    private String additionalVariant; // do_wariant | former jsonArray
    private String additionalsVariantList1; //do_wariant_lista1
    private String additionalsVariantList2; //do_wariant_lista2
    private String additionalsAdditionalList; //do_dodat
    private String allergens; //alergeny | former jsonArray
    private String priceBasic; //cena podstawowa
    private String price; // cena
    private String preparationTime; // da_czas
    private int weight; // waga
    private int kcal; // kcal



    private String evaluation; // ud0_da_lubi (%)

    private String username; //


    public MainCourse(int id, String mainCourseID, String restaurateurID,
                      String photoURL, String restaurantName,
                      String mainCourseCategoryID, String mainCourseSubCatId, String mainCourseType,
                      String mainCourseName, String mainCourseDescription, String mainCourseAverageEvaluation,
                      String additionalPrimary, String additionalVariant,
                      String additionalsVariantList1, String additionalsVariantList2,
                      String additionalsAdditionalList, String allergens, String priceBasic, String price,
                      String preparationTime, int weight, int kcal, String evaluation,
                      String username) {
        super();
        this.id = id;
        this.mainCourseID = mainCourseID;
        this.restaurateurID = restaurateurID;
        this.photoURL = photoURL;
        this.restaurantName = restaurantName;
        this.mainCourseCategoryID = mainCourseCategoryID;
        this.mainCourseSubCatId = mainCourseSubCatId;
        this.mainCourseType = mainCourseType;
        this.mainCourseName = mainCourseName;
        this.mainCourseDescription = mainCourseDescription;
        this.mainCourseAverageEvaluation = mainCourseAverageEvaluation;
        this.additionalPrimary = additionalPrimary;
        this.additionalVariant = additionalVariant;
        this.additionalsVariantList1 = additionalsVariantList1;
        this.additionalsVariantList2 = additionalsVariantList2;
        this.additionalsAdditionalList = additionalsAdditionalList;
        this.allergens = allergens;
        this.priceBasic = priceBasic;
        this.price = price;
        this.preparationTime = preparationTime;
        this.weight = weight;
        this.kcal = kcal;
        this.evaluation = evaluation;
        this.username = username;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainCourseID() {
        return mainCourseID;
    }

    public void setMainCourseID(String mainCourseID) {
        this.mainCourseID = mainCourseID;
    }

    public String getRestaurateurID() {
        return restaurateurID;
    }

    public void setRestaurateurID(String restaurateurID) {
        this.restaurateurID = restaurateurID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMainCourseCategoryID() {
        return mainCourseCategoryID;
    }

    public void setMainCourseCategoryID(String mainCourseCategoryID) {
        this.mainCourseCategoryID = mainCourseCategoryID;
    }

    public String getMainCourseType() {
        return mainCourseType;
    }

    public void setMainCourseType(String mainCourseType) {
        this.mainCourseType = mainCourseType;
    }

    public String getMainCourseName() {
        return mainCourseName;
    }

    public void setMainCourseName(String mainCourseName) {
        this.mainCourseName = mainCourseName;
    }

    public String getMainCourseDescription() {
        return mainCourseDescription;
    }

    public void setMainCourseDescription(String mainCourseDescription) {
        this.mainCourseDescription = mainCourseDescription;
    }

    public String getMainCourseAverageEvaluation() {
        return mainCourseAverageEvaluation;
    }

    public void setMainCourseAverageEvaluation(String mainCourseAverageEvaluation) {
        this.mainCourseAverageEvaluation = mainCourseAverageEvaluation;
    }


    public String getAdditionalPrimary() {
        return additionalPrimary;
    }

    public void setAdditionalPrimary(String additionalPrimary) {
        this.additionalPrimary = additionalPrimary;
    }

    public String getAdditionalVariant() {
        return additionalVariant;
    }

    public void setAdditionalVariant(String additionalVariant) {
        this.additionalVariant = additionalVariant;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getPriceBasic() {
        return priceBasic;
    }

    public void setPriceBasic(String priceBasic) {
        this.priceBasic = priceBasic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdditionalsVariantList1() {
        return additionalsVariantList1;
    }

    public void setAdditionalsVariantList1(String additionalsVariantList1) {
        this.additionalsVariantList1 = additionalsVariantList1;
    }

    public String getAdditionalsVariantList2() {
        return additionalsVariantList2;
    }

    public void setAdditionalsVariantList2(String additionalsVariantList2) {
        this.additionalsVariantList2 = additionalsVariantList2;
    }

    public String getAdditionalsAdditionalList() {
        return additionalsAdditionalList;
    }

    public void setAdditionalsAdditionalList(String additionalsAdditionalList) {
        this.additionalsAdditionalList = additionalsAdditionalList;
    }


    public String getMainCourseSubCatId() {
        return mainCourseSubCatId;
    }


}