package com.juniorenriquegonalez.springboot.web.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.juniorenriquegonalez.springboot.web.apirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
}
