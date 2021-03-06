package es.codeurjc.wallypop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "REPORT")
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id_report")
//@JsonIgnoreProperties(value = "article")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID_REPORT")
    private long ID_REPORT;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "ARTICLE")
    private Article ARTICLE;

    @NonNull
    @Column(name = "EMAIL")
    private String EMAIL;

    @NonNull
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String DESCRIPTION;

    @JsonIgnore
    @Column(name = "PROOF")
    private Blob PROOF = null;

    public Report() {

    }

    public Report(Article aRTICLE, String eMAIL, Blob pROOF, String dESCRIPTION) {
        super();
        ARTICLE = aRTICLE;
        EMAIL = eMAIL;
        PROOF = pROOF;
        DESCRIPTION = dESCRIPTION;
    }

    public Report(Article aRTICLE, String eMAIL, String dESCRIPTION) {
        super();
        ARTICLE = aRTICLE;
        EMAIL = eMAIL;
        DESCRIPTION = dESCRIPTION;
    }

    public Article getARTICLE() {
        return ARTICLE;
    }

    public void setARTICLE(Article aRTICLE) {
        ARTICLE = aRTICLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        DESCRIPTION = dESCRIPTION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String eMAIL) {
        EMAIL = eMAIL;
    }

    public long getID_REPORT() {
        return ID_REPORT;
    }

    public void setID_REPORT(long ID_REPORT) {
        this.ID_REPORT = ID_REPORT;
    }

    @JsonIgnore
    public Blob getPROOF() {
        return PROOF;
    }

    public void setPROOF(Blob pROOF) {
        PROOF = pROOF;
    }

    @Override
    public String toString() {
        return "Report [ID_REPORT=" + ID_REPORT + ", ARTICLE=" + ARTICLE + ", EMAIL=" + EMAIL + ", DESCRIPTION="
                + DESCRIPTION + ", PROOF=" + PROOF + "]";
    }
}
