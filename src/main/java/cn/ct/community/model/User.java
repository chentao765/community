package cn.ct.community.model;

public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private String gtmCreate;
    private String gtmUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(String gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public String getGtmUpdate() {
        return gtmUpdate;
    }

    public void setGtmUpdate(String gtmUpdate) {
        this.gtmUpdate = gtmUpdate;
    }
}
