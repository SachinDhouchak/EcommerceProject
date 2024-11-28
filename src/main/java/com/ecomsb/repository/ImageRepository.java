package com.ecomsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomsb.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
