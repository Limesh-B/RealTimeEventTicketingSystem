package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.service.ThreadManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thread")
public class ThreadController {

    private final ThreadManagementService threadManagementService;

    @Autowired
    public ThreadController(ThreadManagementService threadManagementService) {
        this.threadManagementService = threadManagementService;
    }

    @PostMapping("/start")
    public String startThread() {
        threadManagementService.startThread();
        return "Thread started!";
    }

    @PostMapping("/stop")
    public String stopThread() {
        threadManagementService.stopThread();
        return "Thread stopped!";
    }

    @GetMapping("/status")
    public String getThreadStatus() {
        return threadManagementService.isThreadRunning() ? "Running" : "Stopped";
    }
}
