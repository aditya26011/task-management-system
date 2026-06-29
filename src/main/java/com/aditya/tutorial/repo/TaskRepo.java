package com.aditya.tutorial.repo;

import com.aditya.tutorial.entity.Task;
import com.aditya.tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findByAssignedUser(User loggedInUser);
}
