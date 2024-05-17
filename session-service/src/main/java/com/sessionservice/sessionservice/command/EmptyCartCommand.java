package com.sessionservice.sessionservice.command;

import com.sessionservice.sessionservice.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class EmptyCartCommand implements Command<ResponseEntity<String>> {

    private final HttpServletRequest request;
    private final SessionService sessionService;


    @Override
        public ResponseEntity<String> execute() {
        return sessionService.emptyCart(request);
        }
}
