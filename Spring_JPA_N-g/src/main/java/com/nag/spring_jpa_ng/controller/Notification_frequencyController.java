package com.nag.spring_jpa_ng.controller;

@CrossOrigin(origins="http://localhost:4321")
@RestController
@RequestMapping("/api")

public class Notification_frequencyController {

    @Autowired
    Notification_frequencyRepository notification_frequencyRepository;

    // Get all freqs
    @GetMapping("/notification_frequency")
    public List<Notification_frequency> getAllNotificationFrequencies(){
        return notification_frequencyRepository.findAll();
    }

    // Get frequency by id
    @GetMapping("/notification_frequency/notiFreqId/{notiFreqId}")
    public List <Notification_frequency> getNotificationFrequencyByNotiFreqId(@PathVariable(value = "notiFreqId") int notiFreqId) {
        return notification_frequencyRepository.findByNotiFreqId(notiFreqId).orElseThrow(() -> new RuntimeException("Notification frequency not found with frequency id:" + notiFreqId + "\n" + "Please check id and try again"))
    }

    // Get frequency by title
    @GetMapping("/notification_frequency/notiFreqTitle/{notiFreqTitle}")
    public List <Notification_frequency> getNotificationFrequencyByNotiFreqTitle(@PathVariable(value = "notiFreqTitle") String notiFreqTitle) {
        return notification_frequencyRepository.findByNotiFreqTitle(notiFreqTitle).orElseThrow(() -> new RuntimeException("Notification frequency not found with frequency title:" + notiFreqTitle + "\n" + "Please check title and try again"))
    }

    // Get frequency by base interval
    @GetMapping("/notification_frequency/baseInterval/{baseInterval}")
    public List <Notification_frequency> getNotificationFrequencyByBaseInterval(@PathVariable(value = "baseInterval") double baseInterval) {
        return notification_frequencyRepository.findByBaseInterval(baseInterval).orElseThrow(() -> new RuntimeException("Notification frequency not found with base interval:" + baseInterval + "\n" + "Please check interval and try again"))
    }

    // Get frequency by growth factor
    @GetMapping("/notification_frequency/growthFactor/{growthFactor}")
    public List <Notification_frequency> getNotificationFrequencyByGrowthFactor(@PathVariable(value = "growthFactor") double growthFactor) {
        return notification_frequencyRepository.findByGrowthFactor(growthFactor).orElseThrow(() -> new RuntimeException("Notification frequency not found with growth factor:" + growthFactor + "\n" + "Please check growth factor and try again")
    }

    // Get frequency by max repeats
    @GetMapping("/notification_frequency/maxRepeats/{maxRepeats}")
    public List <Notification_frequency> getNotificationFrequencyByMaxRepeats(@PathVariable(value = "maxRepeats") double maxRepeats) {
        return notification_frequencyRepository.findByMaxRepeats(maxRepeats).orElseThrow(() -> new RuntimeException("Notification frequency not found with max repeats:" + maxRepeats + "\n" + "Please check max repeats and try again")
    }

}
