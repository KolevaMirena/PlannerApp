package com.plannerapp.repo;


import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {




    List<Task> findAllByAssignee(User user);


    @Query(nativeQuery = true, value = "SELECT * FROM tasks where assignee_id is null")
    List<Task> availableTasks();


}
