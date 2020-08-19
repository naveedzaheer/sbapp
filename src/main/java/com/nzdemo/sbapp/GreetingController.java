package com.nzdemo.sbapp;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.microsoft.azure.servicebus.jms.ServiceBusJmsConnectionFactory;
import com.microsoft.azure.servicebus.jms.ServiceBusJmsConnectionFactorySettings;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // private static final String template = "Hello, %s!";
    // private final AtomicLong counter = new AtomicLong();
    private ArrayList<Greeting> greetings = new ArrayList<Greeting>();
    private JMSContext jmsContext;

    public GreetingController() {
        greetings.add(new Greeting(1, "Hello-1"));
        greetings.add(new Greeting(2, "Hello-2"));
        greetings.add(new Greeting(3, "Hello-3"));
        // ServiceBusJmsConnectionFactorySettings connFactorySettings = new ServiceBusJmsConnectionFactorySettings();
        // connFactorySettings.setConnectionIdleTimeoutMS(20000);
        // String ServiceBusConnectionString = "Endpoint=sb://nzjms2q.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=fY7EotUvcEinC+Bm+dPceTJ33gu9syRq6g3pqVBDygk=";
        // ConnectionFactory factory = new ServiceBusJmsConnectionFactory(ServiceBusConnectionString, connFactorySettings);
        // jmsContext = factory.createContext();
    }

	@GetMapping("/greetings")
	public ArrayList<Greeting> greeting() {
		return greetings;
    }

	@GetMapping("/greetings/{id}")
	public Greeting greeting(@PathVariable long id) {
        return greetings.stream()
        .filter(g -> id == g.getId())
        .findAny()
        .orElse(null);
    }
    
    @PostMapping("/greetings")
	public Greeting greeting(@RequestBody Greeting greeting) {
        greetings.add(greeting);
        // Queue queue = jmsContext.createQueue("greetings");
        // JMSProducer producer = jmsContext.createProducer();
        // TextMessage msg = jmsContext.createTextMessage(greeting.toString());
        // producer.send(queue, msg);
	    return greeting;
    }    
    
    @PutMapping("/greetings/{id}")
	public Greeting greeting(@RequestBody Greeting greeting, @PathVariable Long id) {
        Greeting greetingToUpdate = greetings.stream()
        .filter(g -> id == g.getId())
        .findAny()
        .orElse(null);
        if (greetingToUpdate != null)
        {
            greetingToUpdate.setContent(greeting.getContent());
        }
	    return greeting;
	}
}