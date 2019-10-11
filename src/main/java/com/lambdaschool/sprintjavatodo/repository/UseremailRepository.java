package com.lambdaschool.sprintjavatodo.repository;

import com.lambdaschool.oauth2.models.Useremail;
import com.lambdaschool.sprintjavatodo.model.Useremail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UseremailRepository extends CrudRepository<Useremail, Long>
{
    List<Useremail> findAllByUser_Username(String name);
}
