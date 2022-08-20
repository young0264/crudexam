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

import java.util.Optional;

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
    @Transactional
    public void 게시글등록() throws Exception {
        //given
        Board board = new Board();
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목1");
        boardDto.setContent("내용1");
        Board savedBoard = boardService.addBoard(boardDto); //repository save
        Assert.assertEquals(savedBoard, boardRepository.findById(savedBoard.getId()).get());
        System.out.println("내용1  = " + boardRepository.findById(savedBoard.getId()).get().getContent());


        //===1차 캐시에 들어간 준 영속상태==//
        Optional<Board> byId = boardRepository.findById(savedBoard.getId());
        byId.get().setContent("수정내용2");
        byId.get().setTitle("수정제목2");
        //== 준영속 상태의 객체 수정하면 Transaction이 끝날때 DB에 바로 반영이 됨==//
        //save X,
        System.out.println("수정내용2 = "+boardRepository.findById(savedBoard.getId()).get().getContent());
        Assert.assertEquals("수정내용2", boardRepository.findById(savedBoard.getId()).get().getContent());

        //when
        //then

//        Assert.assertEquals(savedBoard.getId(), boardRepository.findById(savedBoard.getId()).get().getId());
//        Assert.assertEquals(board, boardRepository.findById(board.getId()).get());
    }

    @Test
    public void NO영속성게시물등록() throws Exception {
        //given
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목이다");
        boardDto.setContent("내용이다");
        //when


        //then
    }

}