package com.tpoo.tpnote.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends CrudRepository<Address, Long> {

}

