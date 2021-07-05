package bootcamp.java.mod6.linktracker.endpoint.dto;

import bootcamp.java.mod6.linktracker.entity.LinkTracker;

public class StatDTO {
    private int redirects;
    private int illegalAccess;

    public StatDTO(LinkTracker tracker) {
        this.redirects = tracker.getAccessCounter();
        this.illegalAccess = tracker.getIllegalAccessCounter();
    }

    public int getRedirects() {
        return redirects;
    }

    public int getIllegalAccess() {
        return illegalAccess;
    }
}
