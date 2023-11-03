package com.plannerapp.model.dto.task;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeViewModel {


    private List<TaskDTO> assignedTasksToMe;
    private List<TaskDTO> availableTasks;


    public TaskHomeViewModel(){

        this(new ArrayList<>(), new ArrayList<>());

    }

    public TaskHomeViewModel(List<TaskDTO> assignedTasksToMe, List<TaskDTO> availableTasks){

        this.assignedTasksToMe = assignedTasksToMe;
        this.availableTasks = availableTasks;
    }


    public List<TaskDTO> getAssignedTasksToMe() {
        return assignedTasksToMe;
    }

    public void setAssignedTasksToMe(List<TaskDTO> assignedTasksToMe) {
        this.assignedTasksToMe = assignedTasksToMe;
    }

    public List<TaskDTO> getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(List<TaskDTO> availableTasks) {
        this.availableTasks = availableTasks;
    }
}
