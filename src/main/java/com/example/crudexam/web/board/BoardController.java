package com.example.crudexam.web.board;

import com.example.crudexam.domain.board.Board;
import com.example.crudexam.service.board.BoardService;
import com.example.crudexam.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/newBoardPage")
    public String BoardNewPage(Model model) {
        model.addAttribute(new BoardDto());
        return "/board/newBoardPage";
    }

    //상세페이지니까 방금 데이터를 보여줘야겟지
    @PostMapping("/board/newBoardPage")
    //model에 "boardDto"가 들어가는겨
    public String BoardAddPage(BoardDto boardDto,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        Board currentBoard = boardService.BoardAdd(boardDto);
//        model.addAttribute("board", currentBoard);
        redirectAttributes.addAttribute("boardId", currentBoard.getId());
        return "redirect:/board/detailPage/{boardId}";
    }

    @GetMapping("/board/detailPage/{id}")
    public String BoardDetailPage(@PathVariable Long id,
                                  Model model) {
        if (!boardService.isExistBoard(id)) {
            return "redirect:/board/newBoardPage";
        }
        Board currentBoard = boardService.findBoardById(id);
        model.addAttribute("board", currentBoard);
        return "/board/detailPage"; //model넣은게 그 view로 return되야 반영이
    }

    @GetMapping("/board/editPage/{id}")
    public String showEditPage(@PathVariable Long id,
                               Model model) {
        Board boardById = boardService.findBoardById(id);
        model.addAttribute(new BoardDto());
        model.addAttribute("board", boardById);
        return "/board/editPage";
    }

    @PostMapping("board/editPage/{id}")
    public String updateEditPage(@PathVariable Long id,
                                 BoardDto boardDto) {
        boardService.updateBoard(id, boardDto);
        return "redirect:/board/detailPage/{id}";
    }
}
