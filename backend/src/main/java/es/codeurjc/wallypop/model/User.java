package es.codeurjc.wallypop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(value = {"password", "favorites", "articlessold", "token"})
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @Column(name = "ID_USER")
    private Long ID_USER;

    @NonNull
    @Column(name = "NAME")
    private String FULL_NAME;

    @NonNull
    @Column(name = "PASSWORD")
    private String PASSWORD;

    @NonNull
    @Column(name = "EMAIL")
    private String NAME;

    @NonNull
    @Column(name = "TEL")
    private String TEL;

    @NonNull
    @Column(name = "N_SOLD")
    private int N_SOLD = 0;

    @NonNull
    @Column(name = "N_SELL")
    private int N_SELL = 0;

    @NonNull
    @Column(name = "IS_ADMIN")
    private boolean IS_ADMIN = false;

    @JsonIgnore
    @Column(name = "TOKEN")
    private String TOKEN = null;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "USERS")
    private List<Article> ARTICLES = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "USERS")
    private List<Favorites> FAVORITES = new LinkedList<>();

    public User() {
        // DEFAULT CONSTRUCTOR FOR JPA
    }

    public User(String nAME, String pASSWORD, String eMAIL, String tEL) {
        super();
        FULL_NAME = nAME;
        PASSWORD = pASSWORD;
        NAME = eMAIL;
        TEL = tEL;
    }

    public User(String nAME, String pASSWORD, String eMAIL, String tEL, boolean iS_ADMIN) {
        super();
        FULL_NAME = nAME;
        PASSWORD = pASSWORD;
        NAME = eMAIL;
        TEL = tEL;
        IS_ADMIN = iS_ADMIN;
    }

    public void deleteArticle() {
        N_SELL -= 1;
    }

    public List<Article> getARTICLES() {
        return ARTICLES;
    }

    public void setARTICLES(List<Article> aRTICLES) {
        ARTICLES = aRTICLES;
    }

    public List<Article> getARTICLESSold() {
        List<Article> lResult = new LinkedList<>();
        for (Article a : ARTICLES) {
            if (a.isSOLD()) {
                lResult.add(a);
            }
        }
        return lResult;
    }

    public List<Favorites> getFAVORITES() {
        return FAVORITES;
    }

    public void setFAVORITES(List<Favorites> fAVORITES) {
        FAVORITES = fAVORITES;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String fULL_NAME) {
        FULL_NAME = fULL_NAME;
    }

    public Long getID_USER() {
        return ID_USER;
    }

    public void setID_USER(long iD_USER) {
        ID_USER = iD_USER;
    }

    public void setID_USER(@NonNull Long ID_USER) {
        this.ID_USER = ID_USER;
    }

    public int getN_SELL() {
        return N_SELL;
    }

    public void setN_SELL(int n_SELL) {
        N_SELL = n_SELL;
    }

    public int getN_SOLD() {
        return N_SOLD;
    }

    public void setN_SOLD(int n_SOLD) {
        N_SOLD = n_SOLD;
    }

    // EMAIL
    public String getNAME() {
        return NAME;
    }

    // EMAIL
    public void setNAME(String eMAIL) {
        NAME = eMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String pASSWORD) {
        PASSWORD = pASSWORD;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String tEL) {
        TEL = tEL;
    }

    public boolean isIS_ADMIN() {
        return IS_ADMIN;
    }

    public void setIS_ADMIN(boolean IS_ADMIN) {
        this.IS_ADMIN = IS_ADMIN;
    }

    public void newArticle() {
        N_SELL += 1;
    }

    public void sell(Boolean bool) {
        if (bool) {
            N_SOLD += 1;
        } else {
            N_SOLD -= 1;
        }
    }

    public void updateN_Sell() {
        N_SELL = ARTICLES.size() - N_SOLD;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    @Override
    public String toString() {
        return "User [id=" + ID_USER + ", full_name=" + FULL_NAME + ", email=" + NAME + ", tel=" + TEL + ", nSell=" + N_SELL + ", nSold=" + N_SOLD + ", admin=" + IS_ADMIN + "]";
    }
}
