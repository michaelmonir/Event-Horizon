package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

public interface SuperUpdatedUserRepository {
    void add (Object updatedUser);
    void delete (Object updatedUser);
    void deleteById (int id);
    void update (Object updatedUser);
}
