package com.example.demo.command;

import com.example.demo.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class RemoveItemCommand implements Command<ResponseEntity<String>> {

    private final HttpServletRequest request;
    private final SessionService sessionService;
    private final String itemId;

    @Override
    public ResponseEntity<String> execute() {
        return sessionService.removeItem(request, itemId);
    }
}
