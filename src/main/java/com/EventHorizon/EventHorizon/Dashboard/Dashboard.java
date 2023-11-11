package com.EventHorizon.EventHorizon.Dashboard;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Dashboard {
    private List<Event> events;
    @Autowired
    private EventRepositry eventRepositry;
    int pageSize;
    PageRequest pageWithRecords;
    public List<Event> getPage(int pageIndex,int pageSize){
        this.pageSize=pageSize;
        this.pageWithRecords= PageRequest.of(pageIndex,pageSize);
        events=eventRepositry.findAll((PageRequest) pageWithRecords).getContent();
        return events;
    }
}
