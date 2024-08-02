package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.TypeDto;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Service.TypeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("type")
@AllArgsConstructor
public class TypeController {

    @Autowired
    private ModelMapper modelMapper;
    private TypeService service;

    @GetMapping
    private ResponseEntity<List<TypeDto>> getAllTypes() throws Exception {

        List<TypeDto> typeResponse = service.getAllTypes()
                .stream()
                .map(type -> modelMapper.map(type, TypeDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(typeResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TypeDto> getTypeById(@PathVariable("id") Long id) throws NotFoundExcep {

        Type type = service.getTypeById(id);
        TypeDto typeResponse = modelMapper.map(type, TypeDto.class);
        return ResponseEntity.ok().body(typeResponse);
    }

    @PostMapping
    public ResponseEntity<TypeDto> createType(@RequestBody TypeDto TypeDto) throws Exception {

        Type typeRequest = modelMapper.map(TypeDto, Type.class);
        Type type = service.createType(typeRequest);
        TypeDto typeResponse = modelMapper.map(type, TypeDto.class);

        return new ResponseEntity<TypeDto>(typeResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TypeDto> updateType(@RequestBody TypeDto TypeDto) throws Exception {

        Type typeRequest = modelMapper.map(TypeDto, Type.class);
        Type type = service.updateType(typeRequest);
        TypeDto typeResponse = modelMapper.map(type, TypeDto.class);
        return ResponseEntity.ok().body(typeResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteType(@PathVariable("id") Long id) throws Exception {

        service.deleteType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}