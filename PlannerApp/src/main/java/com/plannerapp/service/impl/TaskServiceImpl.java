package com.plannerapp.service.impl;

import com.plannerapp.model.dto.task.TaskAddBindingModel;
import com.plannerapp.model.dto.task.TaskDTO;
import com.plannerapp.model.dto.task.TaskHomeViewModel;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(TaskAddBindingModel taskAddBindingModel) {


        Task task = new Task();

        Priority priority = this.priorityRepository.findByName(taskAddBindingModel.getPriority());

        if(priority != null){


            task.setDescription(taskAddBindingModel.getDescription());
            task.setDueDate(LocalDate.parse(taskAddBindingModel.getDueDate()));
            task.setPriority(priority);
        }


        this.taskRepository.save(task);



    }

    @Override
    public void remove(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {

       Optional<Task> optionalTask = this.taskRepository.findById(id);

       if(optionalTask.isPresent()){
           Task task = optionalTask.get();

           if(username == null){

               task.setAssignee(null);
           }else {
               User user = this.userRepository.findByUsername(username);

               task.setAssignee(user);
           }

           this.taskRepository.save(task);
       }


    }

    @Override
    public TaskHomeViewModel getHomeViewData(String username) {

        User user = this
                .userRepository.findByUsername(username);

        List<TaskDTO> assignedTasks = this.taskRepository.findAllByAssignee(user).stream()
                .map(TaskDTO::createFromTask).collect(Collectors.toList());


        List<TaskDTO> availableTasks = this.taskRepository.availableTasks().stream()
                .map(TaskDTO::createFromTask).collect(Collectors.toList());

        return new TaskHomeViewModel(assignedTasks, availableTasks);
    }


}
