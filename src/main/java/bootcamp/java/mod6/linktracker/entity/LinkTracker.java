package bootcamp.java.mod6.linktracker.entity;

public class LinkTracker {
    private int id;
    private String link;
    private String password;
    private int accessCounter;
    private int illegalAccessCounter;

    public LinkTracker(int id, String link) {
        this.id = id;
        this.link = link;
    }

    public LinkTracker(int id, String link, String password) {
        this.id = id;
        this.link = link;
        this.password = password;
    }

    public LinkTracker(String link, String password) {
        this.link = link;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessCounter() {
        return accessCounter;
    }

    public void setAccessCounter(int accessCounter) {
        this.accessCounter = accessCounter;
    }

    public int getIllegalAccessCounter() {
        return illegalAccessCounter;
    }

    public void setIllegalAccessCounter(int illegalAccessCounter) {
        this.illegalAccessCounter = illegalAccessCounter;
    }
}
