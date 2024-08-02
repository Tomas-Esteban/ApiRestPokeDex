package com.tomasesteban.pokeapi.Service;

import java.util.List;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Type;

public interface TypeService {
    Type createType(Type stats) throws Exception;

    List<Type> getAllTypes() throws Exception;

    Type getTypeById(long id) throws NotFoundExcep;

    Type updateType(Type stats) throws Exception;

    void deleteType(long id) throws NotFoundExcep;
}
