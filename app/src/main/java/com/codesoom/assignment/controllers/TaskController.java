package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.NotFoundException;
import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.Tasks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    private Tasks tasks = new Tasks();

    @GetMapping
    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return tasks.findTask(id)
                .orElseThrow(() -> new NotFoundException("task를 찾을 수 없습니다"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        tasks.addTask(task);
        return task;
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task source) {
        Task task = tasks.findTask(id)
                .orElseThrow(() -> new NotFoundException("task를 찾을 수 없습니다"));
        task.setTitle(source.getTitle());
        return task;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Task(@PathVariable("id") Long id) {
        tasks.findTask(id)
                .orElseThrow(() -> new NotFoundException("task를 찾을 수 없습니다"));
        tasks.remove(id);
    }

}
