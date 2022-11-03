package in.projectzhu.freakyapi.service;

import in.projectzhu.freakyapi.domain.logs;
import in.projectzhu.freakyapi.repo.logsrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class logsService {

    @Autowired
    private logsrepo repo;

    public logs newlog(logs log){

        log.setUsercode(UUID.randomUUID().toString());
        return repo.save(log);
    }


    public List<logs> allins(){
        return (List<logs>) repo.findAll();
    }


public logs findone(Long id){

        return repo.findById(id).get();

}

public void deletelog(Long id){

        repo.deleteById(id);

}





}
