package com.example.crudexam.web.controller.board;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.service.BoardService;
import com.example.crudexam.web.dto.BoardFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    //상품등록을 먼저 만들어보자
    private final BoardService boardService;

    @GetMapping("/board/new")
    public String newBoardPage(Model model) {
        model.addAttribute(new BoardFormDto());
        return "/board/createBoard";
    }

    @PostMapping("/board/new")
    public String createBoard(BoardFormDto boardFormDto,
                              RedirectAttributes redirectAttributes) {
        System.out.println("Postnew");
        Board savedBoard = boardService.saveBoard(boardFormDto);
        redirectAttributes.addAttribute("id", savedBoard.getId());

        return "redirect:/board/detail/{id}";
    }

    @GetMapping("/board/detail/{id}")
    public String showDetailPage(@PathVariable Long id, Model model) {
        if (!boardService.isExistBoard(id)) {
            return "redirect:/board/list";
        }////
        Board currentBoard = boardService.findBoardById(id);
        model.addAttribute("board", currentBoard);
        return "/board/detail";
    }

    @PostMapping("/board/detail/{id}")//new dto
    public String updateForm(@PathVariable Long id, BoardFormDto boardFormDto) {
        boardService.boardUpdate(id, boardFormDto);
        return "/board/editBoard";
    }

    @GetMapping("/board/editBoard/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {

        Board boardById = boardService.findBoardById(id);
        model.addAttribute("board", boardById);
        model.addAttribute(new BoardFormDto());

        return "board/editBoard";
    }
}
