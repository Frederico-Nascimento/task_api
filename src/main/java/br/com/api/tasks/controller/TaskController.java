package br.com.api.tasks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.tasks.exception.NotFoundException;
import br.com.api.tasks.model.Task;
import br.com.api.tasks.model.TaskList;
import br.com.api.tasks.repository.TaskListRepository;
import br.com.api.tasks.repository.TaskRepository;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
 
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskListRepository taskListRepository;
    
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(
    @PathVariable(value = "id") Long taskId) throws NotFoundException {
        Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Task not found on :: "+ taskId));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/task/{id}")
    public Task createTask(
    	@PathVariable(value = "id") Long taskListId,
    	@Valid @RequestBody Task task) throws NotFoundException {

		TaskList taskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new NotFoundException("Task list not found on :: "+ taskListId));
 
    	task.setFinished(false);
        task.setTasklist(taskList);
    	return taskRepository.save(task);
    }
    
    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(
    @PathVariable(value = "id") Long taskId,
    @Valid @RequestBody Task taskDetails) throws NotFoundException {
         Task task = taskRepository.findById(taskId)
          .orElseThrow(() -> new NotFoundException("Task not found on :: "+ taskId));
         if(!taskDetails.getDescription().isEmpty()) {
             task.setDescription(taskDetails.getDescription());
         }
         if(taskDetails.isFinished()!=null) {
        	 task.setFinished(taskDetails.isFinished());
         }
         final Task updatedTask = taskRepository.save(task);
         return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public Map<String, Boolean> deleteTask(
        @PathVariable(value = "id") Long taskId) throws Exception {
        Task task = taskRepository.findById(taskId)
           .orElseThrow(() -> new NotFoundException("Task not found on :: "+ taskId));

        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}