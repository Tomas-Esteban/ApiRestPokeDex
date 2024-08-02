package com.tomasesteban.pokeapi.Service;

import java.io.IOException;
import java.util.List;


import com.tomasesteban.pokeapi.Models.Type;

public interface TypeService {

    Type getTypeById(long id) throws IOException, InterruptedException;

	Type getTypeByName(String name) throws IOException, InterruptedException;

}
