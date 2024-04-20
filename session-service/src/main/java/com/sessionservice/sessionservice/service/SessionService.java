package com.sessionservice.sessionservice.service;

import com.sessionservice.sessionservice.repository.CartItemRepository;
import com.sessionservice.sessionservice.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {
    private final SessionRepository sessionRepository;
    private final CartItemRepository cartItemRepository;
}
