package com.example.crudexam.service.board;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.domain.board.BoardRepository;
import com.example.crudexam.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //게시글 등록
    public Board addBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent()).build();
        return boardRepository.save(board);
    }

    public Board findById(Long boardId) {
        Optional<Board> byId = boardRepository.findById(boardId);
        return byId.get();
    }

    //수정

    public void updateBoard(Long id, BoardDto boardDto) {

//        Board newBoard = findById(id);
//        newBoard.setContent(boardDto.getContent());
//        newBoard.setTitle(boardDto.getTitle());
//
        Board newBoard = Board.builder()
                .id(id)
                .title(boardDto.getTitle())
                .content(boardDto.getContent()).build();

//        byId.setTitle(boardDto.getTitle());
//        byId.setContent(boardDto.getContent());
        boardRepository.save(newBoard);
    }


    //삭제


}
