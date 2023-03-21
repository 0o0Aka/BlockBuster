package com.example.mybatis.service;

import com.example.mybatis.bean.Actor;
import com.example.mybatis.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActorService {
    @Autowired
    ActorMapper actorMapper;
    public List<Map> getActorByMovieId(int id) {
        return actorMapper.getActorByMovieId(id);
    }

    public List<Actor> getActorByCname(String cname) {
        return actorMapper.getActorByCname(cname);
    }

    public List<Actor> getAllActor() {
        return actorMapper.getAllActor();
    }

    public List<Actor> searchActor(String id, String name) {
        if(id!=""){
            int id1=Integer.parseInt(id);
            return actorMapper.searchActorById(id1);
        }
        else{
            return actorMapper.searchActorByCname(name);
        }
    }

    public String updateActorById(Actor actor) {
        actorMapper.updateActorById(actor);
        return "更改演员信息成功";
    }

    public void deleteActor(int id) {
        actorMapper.deleteActor(id);
    }

    public String addActor(Actor actor) {
        if(actorMapper.haveActor(actor.getCname())!=0) {
            System.out.println("错误");
            return "中文名已经存在";
        }else {
            actorMapper.addActor(actor);
            return "添加演员成功";
        }
    }
}
