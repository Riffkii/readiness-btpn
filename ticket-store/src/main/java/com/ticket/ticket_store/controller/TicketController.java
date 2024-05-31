package com.ticket.ticket_store.controller;

import com.ticket.ticket_store.dto.TicketReq;
import com.ticket.ticket_store.dto.TicketRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/*
Pada kode ini saya membuat sistem ticketing yang sangat sederhana, yang dimana
admin dapat restock ticket dan user bisa hit ke setAvailableTicket untuk order
ticket. Kode ini sangat sederhana karena hanya mengandalkan counter integer sebagai
persediaan tiketnya.
 */
@RestController
@RequestMapping(path = "/ticket")
public class TicketController {
    private AtomicInteger availableTicket = new AtomicInteger(100);

    @GetMapping
    private ResponseEntity<TicketRes> getAvailableTicket() {
        return ResponseEntity.ok(new TicketRes(this.availableTicket.get()));
    }

    @PostMapping
    private ResponseEntity<String> setAvailableTicket(@RequestBody TicketReq req) {
        if(this.availableTicket.get() > 0 ) {
            this.availableTicket.getAndAdd(-req.getAmount());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket sold");
        }
    }

    @PatchMapping
    private ResponseEntity<String> updateAvailableTicket(@RequestParam("amount") Integer amount) {
        this.availableTicket.set(this.availableTicket.get() + amount);
        return ResponseEntity.ok("Success");
    }
}
