package br.com.joaoramos.todolist.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public TaskModel createdTask(@RequestBody TaskModel taskModel){
        TaskModel taskSave = iTaskRepository.save(taskModel);
        return taskSave;
    }
}
