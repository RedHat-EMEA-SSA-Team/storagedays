package com.redhat.emeatigers.storagedays;

import java.security.*;
import java.util.*;
import java.util.stream.*;

import javax.servlet.http.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/event")
public class EventController {

	@Autowired
    private EventRepository repository;

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getAll() {
        Spliterator<Event> events = repository.findAll().spliterator();
        return StreamSupport.stream(events, false).collect(Collectors.toList());
    }
    
    @ResponseBody
    @GetMapping(path="/count", produces = MediaType.TEXT_PLAIN_VALUE)
    public long count() {
    	return repository.count();
    }
    
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public Event save(@RequestBody Event event, HttpServletRequest request){
    	event.setSource(request.getRemoteAddr());
    	return repository.save(event);
    }
    
    @ResponseBody
    @GetMapping(path="/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event random(HttpServletRequest request) {
    	Event e = new Event();
    	e.setTs(System.currentTimeMillis());
    	e.setSource(request.getRemoteAddr());
    	e.setEvent(RandomStringUtils.random( RANDOC_STRING_LENGTH, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom()));
    	return repository.save(e);
    }
    
    private char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
    private final int RANDOC_STRING_LENGTH = 200;
    
    
    
}
