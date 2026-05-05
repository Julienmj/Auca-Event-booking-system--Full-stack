package eventbooking.service;

import eventbooking.model.Event;
import eventbooking.model.Registration;
import eventbooking.repository.EventRepository;
import eventbooking.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Registration register(Registration registration) {
        Optional<Event> optionalEvent = eventRepository.findById(registration.getEventId());

        if (optionalEvent.isEmpty()) {
            throw new IllegalArgumentException("Event not found with id: " + registration.getEventId());
        }

        Event event = optionalEvent.get();

        if (event.getAvailableSeats() <= 0) {
            throw new IllegalArgumentException("No available seats for event: " + event.getTitle());
        }

        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        return registrationRepository.save(registration);
    }
}
