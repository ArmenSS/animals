package com.animal.animalservice.mapper.config;

public interface BaseMapper<Entity, Request, Response> {

  Entity toEntity(Request request);

  Response toResponse(Entity entity);
}
