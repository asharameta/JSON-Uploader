package com.example.recruttask.backendspringboot.Service;

import com.example.recruttask.backendspringboot.entity.User;
import com.example.recruttask.backendspringboot.reository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }


    public User save(User user) {
        return repository.save(user);
    }

    public Page findByParams(String name, String surname, String login, Pageable paging){return repository.findByParams(name,surname,login,paging); }

    //public List filterByColumn(String txt){}
}
