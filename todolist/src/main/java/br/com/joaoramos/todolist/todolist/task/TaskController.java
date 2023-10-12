package br.com.joaoramos.todolist.todolist.task;

import br.com.joaoramos.todolist.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository iTaskRepository;

    @PostMapping("/")
    public ResponseEntity createdTask(@RequestBody TaskModel taskModel, HttpServletRequest request){
        Object idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)idUser);

        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt()) ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("A data de inicio/final deve ser maior que a data atual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("A data de ínicio não pode ser após a data final");
        }
        TaskModel taskSave = iTaskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskModel);
    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        Object idUser = request.getAttribute("idUser");
        List<TaskModel> tasksByIdUser = iTaskRepository.findByIdUser((UUID) idUser);
        return tasksByIdUser;
    }
    @PutMapping("/{id}")
    public TaskModel update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){

        TaskModel task = this.iTaskRepository.findById(id).orElse(null);
        Utils.copyNullProperties(taskModel,task);
        return iTaskRepository.save(task);
    }

}
