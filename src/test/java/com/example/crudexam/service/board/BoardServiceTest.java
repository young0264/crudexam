package com.example.crudexam.service.board;

import com.example.crudexam.domain.board.Board;
import com.example.crudexam.domain.board.BoardRepository;
import com.example.crudexam.web.dto.BoardDto;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //롤백
public class BoardServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    @Test
    public void 게시글등록() throws Exception{
        //given
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목1");
        boardDto.setContent("내용1");
        //when
        Board savedBoard = boardService.addBoard(boardDto);
//        Long boardId = board.getId();
        //then
        em.flush();
        Assert.assertEquals(savedBoard, boardRepository.findById(savedBoard.getId()));
//        Assertions.assertThat(board,boardRepository.findById(boardId));

    }





}