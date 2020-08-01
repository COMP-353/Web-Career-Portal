package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationDao extends JpaRepository<Application, Long> {

}
