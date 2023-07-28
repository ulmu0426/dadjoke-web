package com.ulmu.dadjoketest.repository;

import com.ulmu.dadjoketest.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {

}
