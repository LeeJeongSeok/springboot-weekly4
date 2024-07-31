package com.est.springbootweekly4.repository;

import com.est.springbootweekly4.domain.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByCategory(String category);
}
