package com.example.crudexam.service;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.domain.board.BoardRepository;
import com.example.crudexam.web.dto.BoardFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

//메서드들 만들기
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board saveBoard(BoardFormDto boardFormDto) {
        Board newBoard = new Board();
        newBoard.setTitle(boardFormDto.getTitle());
        newBoard.setContent(boardFormDto.getContent());
        return boardRepository.save(newBoard);
    }

    public boolean isExistBoard(Long id) {
        Optional<Board> boardById = boardRepository.findById(id);
        if (boardById.isEmpty()) {
            return false;
        }return true;
    }


    public Board findBoardById(Long id) {
        Optional<Board> currentBoard = boardRepository.findById(id);
        return currentBoard.get();
    }

    public void boardUpdate(Long id, BoardFormDto boardFormDto) {
        Board boardById = findBoardById(id);
        boardById.setTitle(boardFormDto.getTitle());
        boardById.setContent(boardFormDto.getContent());
        boardRepository.save(boardById);
    }

}
