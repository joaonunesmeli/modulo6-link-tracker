package bootcamp.java.mod6.linktracker.controller;

import bootcamp.java.mod6.linktracker.endpoint.dto.StatDTO;
import bootcamp.java.mod6.linktracker.endpoint.form.IdRequestForm;
import bootcamp.java.mod6.linktracker.endpoint.form.LinkTrackerForm;
import bootcamp.java.mod6.linktracker.entity.LinkTracker;
import bootcamp.java.mod6.linktracker.exception.InvalidCredentialsException;
import bootcamp.java.mod6.linktracker.repository.LinkTrackerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @PostMapping("/")
    public Map<String, Integer> create(@RequestBody LinkTrackerForm form) {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        LinkTracker tracker = LinkTrackerForm.convert(form);
        Map<String, Integer> m = new HashMap<>();
        m.put("linkId", repo.save(tracker));
        return m;
    }

    @GetMapping("/link/{id}")
    public RedirectView redirect(@PathVariable int id) throws InvalidCredentialsException {
        LinkTracker tracker = this.bumpGetById(id, null);
        RedirectView r = new RedirectView();
        r.setUrl("https://" + tracker.getLink());
        return r;
    }

    @PostMapping("/link/{id}")
    public RedirectView redirect(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
        LinkTracker tracker = this.bumpGetById(id, form);
        RedirectView r = new RedirectView();
        r.setUrl("https://" + tracker.getLink());
        return r;
    }

    @PostMapping("/metrics/{id}")
    public StatDTO metrics(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
        LinkTrackerRepository repo = LinkTrackerRepository.getInstance();
        LinkTracker tracker;
        if (form == null) {
            tracker = repo.get(id);
        } else {
            tracker = repo.get(id, form.getPassword());
        }
        return new StatDTO(tracker);
    }

    @PostMapping("/invalidate/{id}")
    public Map<String, String> invalidate(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
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
