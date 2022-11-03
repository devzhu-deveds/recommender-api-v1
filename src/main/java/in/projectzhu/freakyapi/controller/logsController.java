package in.projectzhu.freakyapi.controller;

import in.projectzhu.freakyapi.domain.logs;
import in.projectzhu.freakyapi.service.logsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api")
public class logsController {
    @Autowired
    private logsService serve;

@GetMapping
    public ResponseEntity<List<logs>> getAll(){
    List<logs> log = serve.allins();
    return new ResponseEntity<>(log, HttpStatus.OK);
}

@PostMapping
    public ResponseEntity<logs> addlog(@RequestBody logs log){
    logs addedlog = serve.newlog(log);
    return new ResponseEntity<>(addedlog,HttpStatus.CREATED);
}

@GetMapping("/find/{id}")
    public ResponseEntity<logs> findlogbyid(@PathVariable("id")Long id){

    logs log = serve.findone(id);

    return new ResponseEntity<>(log, HttpStatus.OK);

}
@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletebyid(@PathVariable("id") Long id){

    serve.deletelog(id);

    return new ResponseEntity<>( HttpStatus.OK);

}




}
