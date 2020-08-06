package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Help;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HelpDao extends JpaRepository<Help, Long> {

    @Query("select case when count(h.id) = 1 then true else false end from Help h where h.id = :id")
    boolean helpExistsWithId(@Value("id") int id);

    @Query("update Help h set h.status = 'Closed' where h.id = :id")
    void closeHelpWithId(@Value("id") int id);

    @Query(nativeQuery = true, value = "select * from Help h where h.employerId = :id")
    List<Help> getAllOpenHelpForEmployerWithId(@Value("id") String id);
}
