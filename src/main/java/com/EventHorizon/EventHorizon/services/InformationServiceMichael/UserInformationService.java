package com.EventHorizon.EventHorizon.services.InformationServiceMichael;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserInformationService
{
    void add(Information information, boolean addUser);

    void delete(int id, Information information);
}
