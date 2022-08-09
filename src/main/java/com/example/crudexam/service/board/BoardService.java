package com.example.crudexam.service.board;

import com.example.crudexam.domain.board.Board;
import com.example.crudexam.domain.board.BoardRepository;
import com.example.crudexam.web.dto.BoardDto;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board BoardAdd(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    public Board findBoardById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        return byId.get();
    }

    public void updateBoard(Long id, BoardDto boardDto) {
        Board boardById = findBoardById(id);
        boardById.setTitle(boardDto.getTitle());
        boardById.setContent(boardDto.getContent());
        boardRepository.save(boardById);
    }

    public boolean isExistBoard(Long id) {
        Optional<Board> byId = boardRepository.findById(id);
        if(byId.isEmpty()) {
            return false;
        }else{
            return true;
        }

    }
}
