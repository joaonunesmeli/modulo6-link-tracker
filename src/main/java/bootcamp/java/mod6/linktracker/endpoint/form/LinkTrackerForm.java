package bootcamp.java.mod6.linktracker.endpoint.form;

import bootcamp.java.mod6.linktracker.entity.LinkTracker;

public class LinkTrackerForm {
    private String link;
    private String password;

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

    public static LinkTracker convert(LinkTrackerForm form) {
        return new LinkTracker(form.getLink(), form.getPassword());
    }
}
