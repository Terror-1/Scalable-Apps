package com.example.demo.command;

import com.example.demo.entity.Session;
import com.example.demo.service.SessionService;
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