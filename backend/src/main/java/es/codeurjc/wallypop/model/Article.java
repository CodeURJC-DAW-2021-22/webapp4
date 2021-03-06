package es.codeurjc.wallypop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
@JsonIgnoreProperties(value = {"user", "users"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ARTICLE")
    private long ID_ARTICLE;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "USERS")
    private User USERS;

    @NonNull
    @Column(name = "CITY")
    private String CITY;

    @NonNull
    @Column(name = "POSTAL_CODE")
    private String POSTAL_CODE;

    @NonNull
    @Column(name = "TITLE")
    private String TITLE;

    @NonNull
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String DESCRIPTION = null;

    @NonNull
    @Column(name = "PRICE")
    private float PRICE;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATE")
    private Date DATE = new java.sql.Date(System.currentTimeMillis());

    @Column(name = "RESERVED")
    private boolean RESERVED = false;

    @Column(name = "SOLD")
    private boolean SOLD = false;

    @Column(name = "PHOTO_BLOB")
    private Blob PHOTO;

    @Column(name = "N_VISITS")
    private int N_VISITS = 0;

    @ManyToMany
    @JoinColumn(name = "CATEGORYS")
    private List<Category> CATEGORYS = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ARTICLE")
    private List<Report> REPORTS = new LinkedList<>();

    public Article() {

    }

    public Article(User uSER, String tITLE, String dESCRIPTION, String cITY, String pOSTAL_CODE, float pRICE,
                   Blob pHOTO, List<Category> lISTcATEGORYS) {
        super();
        USERS = uSER;
        TITLE = tITLE;
        DESCRIPTION = dESCRIPTION;
        PRICE = pRICE;
        PHOTO = pHOTO;
        CATEGORYS = lISTcATEGORYS;
        CITY = cITY;
        POSTAL_CODE = pOSTAL_CODE;
    }

    public List<Category> getCATEGORYS() {
        return CATEGORYS;
    }

    public void setCATEGORYS(List<Category> cATEGORYS) {
        CATEGORYS = cATEGORYS;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String cITY) {
        CITY = cITY;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        DESCRIPTION = dESCRIPTION;
    }

    public long getID_ARTICLE() {
        return ID_ARTICLE;
    }

    public void setID_ARTICLE(long ID_ARTICLE) {
        this.ID_ARTICLE = ID_ARTICLE;
    }

    public int getN_VISITS() {
        return N_VISITS;
    }

    public void setN_VISITS(int n_VISITS) {
        N_VISITS = n_VISITS;
    }

    @JsonIgnore
    public Blob getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(Blob pHOTO) {
        PHOTO = pHOTO;
    }

    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public void setPOSTAL_CODE(String pOSTAL_CODE) {
        POSTAL_CODE = pOSTAL_CODE;
    }

    public float getPRICE() {
        return PRICE;
    }

    public void setPRICE(float pRICE) {
        PRICE = pRICE;
    }

    public String getPRICE_s() {
        return String.valueOf(getPRICE());
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String tITLE) {
        TITLE = tITLE;
    }

    public User getUSER() {
        return USERS;
    }

    public void setUSER(User uSER) {
        USERS = uSER;
    }

    public String getUserEmail() {
        return USERS.getNAME();
    }

    public Long getUserID() {
        return USERS.getID_USER();
    }

    public boolean isRESERVED() {
        return RESERVED;
    }

    public void setRESERVED(boolean rESERVED) {
        RESERVED = rESERVED;
    }

    public boolean isSOLD() {
        return SOLD;
    }

    public void setSOLD(boolean sOLD) {
        SOLD = sOLD;
    }

    public void visit() {
        N_VISITS += 1;
    }

    @NonNull
    public User getUSERS() {
        return USERS;
    }

    public void setUSERS(@NonNull User USERS) {
        this.USERS = USERS;
    }

    public List<Report> getREPORTS() {
        return REPORTS;
    }

    public void setREPORTS(List<Report> REPORTS) {
        this.REPORTS = REPORTS;
    }

    public void addCategory(Category c) {
        CATEGORYS.add(c);
    }
}