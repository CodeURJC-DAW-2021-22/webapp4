package es.codeurjc.wallypop.dto;

import javax.persistence.Column;

public class UserDTO {

    @Column(name = "NAME")
    private String FULL_NAME;

    @Column(name = "PASSWORD")
    private String PASSWORD;

    @Column(name = "EMAIL")
    private String NAME;

    @Column(name = "TEL")
    private String TEL;

    public UserDTO(String FULL_NAME, String PASSWORD, String NAME, String TEL) {
        this.FULL_NAME = FULL_NAME;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
        this.TEL = TEL;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }
}
