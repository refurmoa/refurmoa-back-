package com.highfive.refurmoa.user.repository;

import java.util.List;

import com.highfive.refurmoa.entity.Mile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Mile;

public interface MileRepository extends JpaRepository<Mile, Integer> {
  
	@Query(value="select * from mile where member_id= :memberId order by mile_num DESC limit 3", nativeQuery=true)
	public List<Mile> mileList(@Param("memberId") String memberId);
	
}
