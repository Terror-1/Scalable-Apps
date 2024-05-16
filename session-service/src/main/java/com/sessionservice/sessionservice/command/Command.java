package com.sessionservice.sessionservice.command;

// Define a generic command interface
public interface Command<T> {
    T execute() throws Exception;
}
