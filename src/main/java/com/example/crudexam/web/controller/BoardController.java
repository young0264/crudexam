package com.example.crudexam.web.controller;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.service.board.BoardService;
import com.example.crudexam.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/board/newBoardPage")
    public String showNewBoardPage(@ModelAttribute("boardDto") BoardDto boardDto) {

        return "/board/newBoardPage";
    }

    @PostMapping("/board/newBoardPage")
    public String addBoard(BoardDto boardDto,
                                 RedirectAttributes redirectAttributes) {

        Board board = boardService.addBoard(boardDto);
        Long boardId = board.getId();
        redirectAttributes.addAttribute("boardId", boardId);
        return "redirect:/board/detailPage/{boardId}";
    }

    @GetMapping("/board/detailPage/{boardId}")
    public String showDetailPage(@PathVariable Long boardId,
                                 Model model) {
        Board newBoard = boardService.findById(boardId);
        model.addAttribute("board", newBoard);
        return "/board/detailPage";
    }

    @GetMapping("/board/editPage/{boardId}")
    public String showEditPage(@PathVariable Long boardId,
                               @ModelAttribute("boardDto") BoardDto boardDto , Model model) {
//        BoardDto boardDto = new BoardDto();
//        Board newBoard = boardService.findById(boardId);
//        boardDto.setTitle(newBoard.getTitle());
//        boardDto.setContent(newBoard.getContent());
        Board byId = boardService.findById(boardId);
        model.addAttribute("board", byId);
        return "/board/editPage";
    }

    @PostMapping("/board/editPage/{boardId}")
    public String completeEditPage(@PathVariable Long boardId, BoardDto boardDto) {
//        redirectAttributes.addAttribute("boardId", boardId);
        boardService.updateBoard(boardId, boardDto);
        return "redirect:/board/detailPage/{boardId}";
    }


}
