package es.codeurjc.wallypop.dto;

public class ArticleRequest {
    private String CITY;
    private String POSTAL_CODE;
    private String TITLE;
    private String DESCRIPTION;
    private float PRICE;
    private int[] categories;

    public ArticleRequest(String CITY, String POSTAL_CODE, String DESCRIPTION, String TITLE, float PRICE, int[] categories) {
        this.CITY = CITY;
        this.POSTAL_CODE = POSTAL_CODE;
        this.DESCRIPTION = DESCRIPTION;
        this.TITLE = TITLE;
        this.PRICE = PRICE;
        this.categories = categories;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public void setPOSTAL_CODE(String POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public float getPRICE() {
        return PRICE;
    }

    public void setPRICE(float PRICE) {
        this.PRICE = PRICE;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }
}
