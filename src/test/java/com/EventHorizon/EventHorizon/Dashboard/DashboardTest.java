package com.EventHorizon.EventHorizon.Dashboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DashboardTest {
    @Autowired
    private Dashboard dashboard;

    @Test
    public void findEventsInPage(){
        System.out.println(dashboard.getPage(0,5));
    }
}