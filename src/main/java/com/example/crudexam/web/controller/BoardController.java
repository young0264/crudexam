package com.example.crudexam.web.controller;


import com.example.crudexam.domain.board.Board;
import com.example.crudexam.service.board.BoardService;
import com.example.crudexam.web.dto.BoardDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/newBoardPage")
    public String showNewBoard(@ModelAttribute("boardDto") BoardDto boardDto) {
        return "/board/newBoardPage";
    }

    @PostMapping("/board/newBoardPage")
    public String addBoard(BoardDto boardDto,
                           RedirectAttributes redirectAttributes) {
        Board currentBoard = boardService.addBoard(boardDto);
        redirectAttributes.addAttribute("boardId", currentBoard.getId());
        return "redirect:/board/detailPage/{boardId}";
    }

    @GetMapping("/board/detailPage/{boardId}")
    public String showDetailPage(@PathVariable Long boardId,
                                 Model model) {
        if (!boardService.isExistBoard(boardId)) {
            return "redirect:/board/newBoardPage";
        }
        Board currentBoard = boardService.findById(boardId);
        model.addAttribute("board", currentBoard);
        return "/board/detailPage";
    }

    //id를가지고 get방식으로 화면을 띄웡돼(value->board)
    @GetMapping("/board/editPage/{boardId}")
    public String finishUpdateBoard(@PathVariable Long boardId,
                                    @ModelAttribute("boardDto") BoardDto boardDto, Model model) {
        Board byId = boardService.findById(boardId);
        model.addAttribute("board", byId);

        return "/board/editPage";
    }

    @PostMapping("/board/editPage/{boardId}")
    public String updateBoard(@PathVariable Long boardId,
                              BoardDto boardDto) {

        boardService.updateBoard(boardId, boardDto);

        return "redirect:/board/detailPage/{boardId}";
    }

}
