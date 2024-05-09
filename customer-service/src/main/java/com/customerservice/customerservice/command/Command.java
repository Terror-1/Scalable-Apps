package com.customerservice.customerservice.command;

// Define a generic command interface
public interface Command<T> {
    T execute() throws Exception;
}
