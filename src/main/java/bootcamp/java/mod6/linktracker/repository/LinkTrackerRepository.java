package bootcamp.java.mod6.linktracker.repository;

import bootcamp.java.mod6.linktracker.entity.LinkTracker;
import bootcamp.java.mod6.linktracker.exception.InvalidCredentialsException;

import java.util.HashMap;
import java.util.Map;

public class LinkTrackerRepository {
    private static LinkTrackerRepository instance = new LinkTrackerRepository();

    private Map<Integer, LinkTracker> data = new HashMap<>();
    private int nextID = 0;

    private LinkTrackerRepository() {}

    public int save(LinkTracker tracker) {
        int id = nextID++;
        tracker.setId(id);
        data.put(id, tracker);
        return id;
    }

    public LinkTracker bumpGet(int id) throws InvalidCredentialsException {
        return this.bumpGet(id, "");
    }

    public LinkTracker bumpGet(int id, String password) throws InvalidCredentialsException {
        LinkTracker tracker = data.get(id);
        String p = tracker.getPassword();
        if (p != null && !p.equals(password)) {
            tracker.setIllegalAccessCounter(tracker.getIllegalAccessCounter() + 1);
            throw new InvalidCredentialsException("Senha incorreta");
        }
        tracker.setAccessCounter(tracker.getAccessCounter() + 1);
        return tracker;
    }

    public LinkTracker get(int id) throws InvalidCredentialsException {
        return get(id, "");
    }

    public LinkTracker get(int id, String password) throws InvalidCredentialsException {
        LinkTracker tracker = data.get(id);
        String p = tracker.getPassword();
        if (p != null && !p.equals(password)) {
            throw new InvalidCredentialsException("Senha incorreta");
        }
        return tracker;
    }

    public void remove(int id, String password) throws InvalidCredentialsException {
        this.get(id, password); // faz get somente para verificar a senha
        this.data.remove(id);
    }

    public static LinkTrackerRepository getInstance() {
        return instance;
    }
}
