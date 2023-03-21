package com.example.mybatis.controller;

import com.example.mybatis.bean.Actor;
import com.example.mybatis.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Actor")
public class ActorController {
    @Autowired
    ActorService actorService;
    @RequestMapping("/getActorByMovieId")
    public List<Map> getActorByMovieId(int id){
         return actorService.getActorByMovieId(id);
    }
    @RequestMapping("/getActorByCname")
    public List<Actor> getActorByCname(String cname){
        return actorService.getActorByCname(cname);
    }

    @RequestMapping("/getAllActor")
    public Map<String, Object> getAllActor(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",actorService.getAllActor().size());
        map.put("data",actorService.getAllActor());
        return map;
    }

    @RequestMapping("/searchActor")
    public Map<String, Object> searchMovie(String id,String name){
        System.out.println(id+name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",actorService.searchActor(id,name).size());
        map.put("data",actorService.searchActor(id,name));
        return map;
    }

    @RequestMapping("/updateActorById")
    public String updateActorById(Actor actor){
        System.out.println(actor);
        return actorService.updateActorById(actor);
    }

    @RequestMapping("/deleteActor")
    public void deleteActor(int id){
        actorService.deleteActor(id);
    }

    @RequestMapping("/addActor")
    public String addActor(Actor actor){
        System.out.println(actor);
        return actorService.addActor(actor);
    }
}
