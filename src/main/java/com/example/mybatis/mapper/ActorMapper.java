package com.example.mybatis.mapper;

import com.example.mybatis.bean.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ActorMapper {

    List<Map> getActorByMovieId(int id);

    List<Actor> getActorByCname(String cname);

    List<Actor> getAllActor();

    List<Actor> searchActorById(int id1);

    List<Actor> searchActorByCname(String name);

    void updateActorById(Actor actor);

    void deleteActor(int id);

    void addActor(Actor actor);

    int haveActor(String cname);
}
