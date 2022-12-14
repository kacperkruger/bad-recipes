package com.github.kacperkruger.notification.service;

import com.github.kacperkruger.clients.notification.service.NotificationSenderStrategyResolver;
import com.github.kacperkruger.notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

public class NotificationServiceTest {

    @MockBean
    NotificationRepository notificationRepository;

    NotificationService notificationService;

    @MockBean
    NotificationSenderStrategyResolver notificationSenderResolver;

    @BeforeEach
    void setUp() {
        NotificationRepository mockRepository = mock(NotificationRepository.class);
        NotificationSenderStrategyResolver mockSenderResolver = mock(NotificationSenderStrategyResolver.class);
        notificationService = new NotificationService(mockSenderResolver, mockRepository);
    }

    @Test
    void test() {
        fail();
    }
}