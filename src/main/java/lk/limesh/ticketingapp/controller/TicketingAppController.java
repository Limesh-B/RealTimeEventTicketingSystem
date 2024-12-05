//package lk.limesh.ticketingapp.controller;
//
//import lk.limesh.ticketingapp.service.TicketingAppService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/test1")
//public class TicketingAppController {
//
//    private final TicketingAppService ticketingAppService;
//
//    @Autowired
//    public TicketingAppController(TicketingAppService ticketingAppService) {
//        this.ticketingAppService = ticketingAppService;
//    }
//
//    @Bean
//    @GetMapping("/testing")
//    public String testing() {
//        return "Hello World!";
//    }
//}
