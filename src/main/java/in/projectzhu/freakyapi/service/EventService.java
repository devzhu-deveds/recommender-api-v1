package in.projectzhu.freakyapi.service;


import in.projectzhu.freakyapi.config.ApplicationProperties;
import in.projectzhu.freakyapi.domain.Event;
import in.projectzhu.freakyapi.domain.Profile;
import in.projectzhu.freakyapi.exceptions.EntityNotFoundException;
import in.projectzhu.freakyapi.repo.EventRepository;
import in.projectzhu.freakyapi.service.dto.ResultDTO;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EventService {

    private final EventRepository eventRepository;
    private final ApplicationProperties applicationProperties;
    private final ProfileService profileService;

    public EventService(EventRepository eventRepository, ApplicationProperties applicationProperties, ProfileService profileService) {
        this.eventRepository = eventRepository;
        this.applicationProperties = applicationProperties;
        this.profileService = profileService;
    }

    /**
     * Save an event
     *
     * @param event
     * @return
     */
    public Event save(Event event) {
        log.debug("Request to save event : {}", event);

        // extract application id info

        // global user id config

        event.setIsProcessed(Boolean.FALSE);
        event.setCreatedOn(LocalDateTime.now());

        event = eventRepository.save(event);
        return event;
    }

    /**
     * Update an event
     *
     * @param event
     * @return
     */
    public Event update(Event event) {
        log.debug("Request to update event with id : {}", event.getId());

        return event;
    }

    /**
     * Find an event by eventId
     *
     * @param id
     * @return
     */
    public Event findOne(Long id) throws EntityNotFoundException {

        log.debug("Getting event with id : {}", id);

        Event event = null;

        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isPresent()) {
            event = optionalEvent.get();
        } else {
            throw new EntityNotFoundException("Event with the specified id does not exist");
        }

        return event;
    }

    /**
     * Find events by application id
     *
     * @param applicationId
     * @return
     */
    public List<Event> findByApplication(Long applicationId, Integer pageSize, Integer pageNo) {

        log.debug("Request to get events by application : {}", applicationId);

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Event> pagedEvents = eventRepository.findAllByApplicationId(applicationId, pageable);

        List<Event> events = new ArrayList<>();
        if (pagedEvents.hasContent()) {
            events = pagedEvents.getContent();
            return events;
        }

        return events;
    }

    /**
     * Find events by global user id
     *
     * @param globalUserId
     * @return
     */
    public List<Event> findByUser(Long globalUserId) {
        List<Event> eventList = new ArrayList<>();

        return eventList;
    }

    /**
     * Delete an event by id
     *
     * @param id
     */
    public void deleteEvent(Long id) {
        log.debug("Request to delete event with id : {}", id);

        eventRepository.deleteById(id);
    }

    /**
     * Save many events
     *
     * @param eventList
     * @return
     */
    public List<Event> saveMany(List<Event> eventList) {
        log.debug("Request to save {} events", eventList.size());

        List<Event> savedEvents = eventRepository.saveAll(eventList);

        return savedEvents;
    }


    /**
     * Process an event
     * Batch process since there are complex mathematical computations to be done
     */
    public List<ResultDTO> processEvents() {
        long startId = getLastIdProcessed();
        long lastId = startId;
        log.info("Start processing of events from id : {}", startId + 1);

        List<ResultDTO> result = new ArrayList<>();
        // find events whose id is greater or equal to start id
        List<Event> events = eventRepository.findAllByIdGreaterThan(startId);
        log.info("There are {} events to be processed", events.size());
        for (Event event : events) {
            // recommend
            // use content filtering here
            List<Integer> recommended = doContentFiltering(event);
            // use clustering here
            List<Integer> recommendedByClustering = doClustering(event);
            recommended.addAll(recommendedByClustering);

            ResultDTO resultDTO = new ResultDTO(event.getId(), event.getUserId(), event.getGlobalUserId(), recommended);
            result.add(resultDTO);

            event.setIsProcessed(true);
            lastId = event.getId();
        }

        // update last processed id
        setLastIdProcessed(lastId);

        resolveProcessingResults(result);

        return result;
    }

    /**
     * Get the last id processed from the config file
     *
     * @return
     */
    public long getLastIdProcessed() {
        String configFileName = "id-tracker.txt";

        configFileName = applicationProperties.getConfigPath() + configFileName;

        File file = new File(configFileName);

        long startId = 0;
        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLong()) {
                startId = reader.nextLong();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return startId;
    }

    public void setLastIdProcessed(long lastIdProcessed) {
        String configFileName = "id-tracker.txt";

        configFileName = applicationProperties.getConfigPath() + configFileName;

        File configFile = new File(configFileName);

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(lastIdProcessed + "");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> doContentFiltering(Event event) {
        log.info("About to execute content filtering algorithm for event {}", event.getId());

        List<Integer> result = new ArrayList<>();
        // extract the event values
        List<Integer> eventValuesList = extractEventList(event);


        for (Event eventIteration : eventRepository.findAll()) {
            // skip the event in question.
            if (event.getId().equals(eventIteration.getId())) {
                continue;
            }

            if (eventIteration.getGlobalUserId().equals(event.getGlobalUserId())) {
                continue;
            }

            List<Integer> currentEventValueList = extractEventList(eventIteration);

            if (meetsThreshold(2, currentEventValueList, eventValuesList)) {
                List<Integer> unionIds = difference(currentEventValueList, eventValuesList);
                result.addAll(unionIds);
            }
        }


        return result;
    }

    /**
     * Check if 2 lists meet the value count threshold
     * @param threshold
     * @param firstList
     * @param secondList
     * @return
     */
    public boolean meetsThreshold(int threshold, List<Integer> firstList, List<Integer> secondList) {
        int tempThreshold = 0;

        for (Integer i : firstList) {
            for (Integer j : secondList) {
                if (i.equals(j)) {
                    tempThreshold += 1;
                }
            }
        }

        if (tempThreshold >= threshold) {
            return true;
        }

        return false;
    }

    public List<Integer> doClustering(Event event) {
        log.info("About to execute clustering algorithm for event : {}", event);

        List<Integer> recommended = new ArrayList<>();

        return recommended;
    }

    public List<Integer> extractEventList(Event event) {

        List<Integer> eventList = new ArrayList<>();

        eventList = Arrays.asList(event.getEventValue().split(","))
                .stream()
                .map(value -> Integer.parseInt(value))
                .collect(Collectors.toList());

        return eventList;
    }

    /**
     * Map the recommendations to user
     *
     * @param results
     */
    public void resolveProcessingResults(List<ResultDTO> results) {
        Map<Long, List<Integer>> resultMap = new HashMap<>();
        for (ResultDTO resultDTO : results) {
            Long globalUserId = resultDTO.getGlobalUserId();
            List<Integer> recommended = resultDTO.getRecommended();
            if (recommended == null) {
                recommended = new ArrayList<>();
            }
            resultMap.put(globalUserId, recommended);
        }

        for (Map.Entry<Long, List<Integer>> entry : resultMap.entrySet()) {
            try {
                Profile profile = profileService.findOneEntityByGlobalUserId(entry.getKey());
                profile.setRecommendations(entry.getValue().toString());
                profile.setUpdatedOn(LocalDateTime.now());
                profileService.save(profile);
            } catch (EntityNotFoundException e) {
                Profile profile = new Profile();
                profile.setGlobalUserId(entry.getKey());
                profile.setRecommendations(entry.getValue().toString());
                profile.setUpdatedOn(LocalDateTime.now());
                profileService.save(profile);
            }
        }
    }

    /**
     * Get elements that are not in both lists
     *
     * @param containerList
     * @param hypothesisList
     * @param <T>
     * @return
     */
    public <T> List<T> difference(List<T> containerList, List<T> hypothesisList) {
        List<T> list = new ArrayList<T>();

        for (T t : containerList) {
            if (!hypothesisList.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
}
