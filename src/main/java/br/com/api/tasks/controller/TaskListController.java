package br.com.api.tasks.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.tasks.exception.NotFoundException;
import br.com.api.tasks.model.TaskList;
import br.com.api.tasks.repository.TaskListRepository;

@RestController
@RequestMapping("/api/v1")
public class TaskListController {
 
    @Autowired
    private TaskListRepository taskListRepository;

    @GetMapping("/tasklist")
    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    @GetMapping("/taskLists/{id}")
    public ResponseEntity<TaskList> getTaskListById(
    @PathVariable(value = "id") Long taskListId) throws NotFoundException {
        TaskList taskList = taskListRepository.findById(taskListId)
        .orElseThrow(() -> new NotFoundException("TaskList not found on :: "+ taskListId));
        return ResponseEntity.ok().body(taskList);
    }

    @PostMapping("/tasklist")
    public TaskList createTaskList(@Valid @RequestBody TaskList taskList) {
        return taskListRepository.save(taskList);
    }	

}
