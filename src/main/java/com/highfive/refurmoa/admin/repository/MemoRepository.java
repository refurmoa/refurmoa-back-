package com.highfive.refurmoa.admin.repository;

import com.highfive.refurmoa.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Integer> {

    List<Memo> findAllByOrderByMemoNumDesc();
}
