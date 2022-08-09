package com.example.crudexam.service.board;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.domain.board.BoardRepository;
import com.example.crudexam.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board addBoard(BoardDto boardDto) {
        Board newBoard = new Board();
        newBoard.setTitle(boardDto.getTitle());
        newBoard.setContent(boardDto.getContent());
        Board savedboard = boardRepository.save(newBoard);
        return savedboard;
    }

    public Board findById(Long boardId) {
        Optional<Board> byId = boardRepository.findById(boardId);
        return byId.get();
    }

    public void updateBoard(Long id, BoardDto boardDto) {
        Board byId = findById(id);
        byId.setTitle(boardDto.getTitle());
        byId.setContent(boardDto.getContent());
        boardRepository.save(byId);
    }


    public boolean isExistBoard(Long boardId) {
        Optional<Board> byId = boardRepository.findById(boardId);
        if (byId.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
