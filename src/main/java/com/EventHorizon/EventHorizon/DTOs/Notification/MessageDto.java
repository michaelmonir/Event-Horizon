package com.EventHorizon.EventHorizon.DTOs.Notification;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class MessageDto {
    public String userEmail;
    public String subject;
    public String body;
}
