package com.tharun.spring_boot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharun.spring_boot_crud.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

}
