package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobDao extends JpaRepository<Job, Long> {

}
