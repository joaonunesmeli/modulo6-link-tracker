package bootcamp.java.mod6.linktracker.controller;

import bootcamp.java.mod6.linktracker.endpoint.dto.StatDTO;
import bootcamp.java.mod6.linktracker.endpoint.form.IdRequestForm;
import bootcamp.java.mod6.linktracker.endpoint.form.LinkTrackerForm;
import bootcamp.java.mod6.linktracker.exception.InvalidCredentialsException;
import bootcamp.java.mod6.linktracker.service.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class Controller {
    private final LinkTrackerService service;

    @Autowired
    public Controller(LinkTrackerService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Map<String, Integer> create(@RequestBody LinkTrackerForm form) {
        return service.create(form);
    }

    @PostMapping("/link/{id}")
    public RedirectView redirect(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
        return service.redirect(id, form);
    }

    @PostMapping("/metrics/{id}")
    public StatDTO metrics(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
        return service.metrics(id, form);
    }

    @PostMapping("/invalidate/{id}")
    public Map<String, String> invalidate(@PathVariable int id, @RequestBody IdRequestForm form) throws InvalidCredentialsException {
        return service.invalidate(id, form);
    }
}
