package com.example.crudexam.domain.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByTitleContains(String keyword);
    Board findByTitleContains(String keyword);

}
