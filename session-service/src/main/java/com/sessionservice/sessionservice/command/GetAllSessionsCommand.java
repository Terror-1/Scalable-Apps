package com.sessionservice.sessionservice.command;

import com.sessionservice.sessionservice.entity.Session;
import com.sessionservice.sessionservice.service.SessionService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllSessionsCommand implements Command<List<Session>> {

    private final SessionService sessionService;


    @Override
    public List<Session> execute() {
        return sessionService.getAllSessions();
    }
}