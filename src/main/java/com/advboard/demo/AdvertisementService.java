package com.advboard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository repo;
    @Autowired
    private UserRepository repo1;

    public List<Advertisement> listAll() { return repo.findAll(); }
    public List<Advertisement> findSimilar(String description) { return repo.findSimilar("%"+description+"%"); }

    public void save(Advertisement advertisement) {
        repo.save(advertisement);
    }

    public Advertisement get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }


    public List<User> listAllUser() {
        return repo1.findAll();
    }

    public void saveUser(User user) { repo1.save(user); }

    public User getUser(long id) {
        return repo1.findById(id).get();
    }
    public User findUserByName(String name){return repo1.findUserByName(name);}

    public void deleteUser(long id) {
        repo1.deleteById(id);
    }
}
