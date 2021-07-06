package bootcamp.java.mod6.linktracker.service;

import bootcamp.java.mod6.linktracker.endpoint.dto.StatDTO;
import bootcamp.java.mod6.linktracker.endpoint.form.IdRequestForm;
import bootcamp.java.mod6.linktracker.endpoint.form.LinkTrackerForm;
import bootcamp.java.mod6.linktracker.entity.LinkTracker;
import bootcamp.java.mod6.linktracker.exception.InvalidCredentialsException;
import bootcamp.java.mod6.linktracker.repository.LinkTrackerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Service
public class LinkTrackerService {
    public Map<String, Integer> create(LinkTrackerForm form) {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        LinkTracker tracker = LinkTrackerForm.convert(form);
        Map<String, Integer> m = new HashMap<>();
        m.put("linkId", repo.save(tracker));
        return m;
    }

    public RedirectView redirect(int id, IdRequestForm form) throws InvalidCredentialsException {
        LinkTracker tracker = this.bumpGetById(id, form);
        RedirectView r = new RedirectView();
        r.setUrl("https://" + tracker.getLink());
        return r;
    }

    public StatDTO metrics(int id, IdRequestForm form) throws InvalidCredentialsException {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        LinkTracker tracker;
        if (form == null) {
            tracker = repo.get(id);
        } else {
            tracker = repo.get(id, form.getPassword());
        }
        return new StatDTO(tracker);
    }

    public Map<String, String> invalidate(int id, IdRequestForm form) throws InvalidCredentialsException {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        String password = form == null ? "" : form.getPassword();
        repo.remove(id, password);

        Map<String, String> m = new HashMap<>();
        m.put("message", "Link removido");
        return m;
    }

    private LinkTracker bumpGetById(int id, IdRequestForm form) throws InvalidCredentialsException {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        if (form == null) {
            return repo.bumpGet(id);
        }
        return repo.bumpGet(id, form.getPassword());
    }
}
