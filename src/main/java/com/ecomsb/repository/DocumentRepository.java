package com.ecomsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomsb.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
