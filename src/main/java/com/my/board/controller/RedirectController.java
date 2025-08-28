package com.my.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mapping") // 앞에 슬래시 권장
public class RedirectController {

    // GET /mapping 또는 /mapping/
    @GetMapping({"", "/"})
    public String testMain() {
        return "test/testMain"; // 앞에 슬래시 X
    }

    // GET /mapping/page
    @GetMapping("/page")
    public String pageView(Model model) {
        model.addAttribute("msg", "Model로 보낸 값");
        return "test/page";
    }

    // GET /mapping/requestMapping
    @RequestMapping(value = "/requestMapping", method = RequestMethod.GET)
    public String requestMapping(Model model) {
        model.addAttribute("msg", "requestMapping로 보낸 값");
        return "test/page";
    }

    // GET /mapping/modelAndView -> page로 리다이렉트(플래시 사용)
    @GetMapping("/modelAndView")
    public ModelAndView modelAndView(RedirectAttributes ra) {
        ra.addFlashAttribute("msg", "ModelAndView");
        return new ModelAndView("redirect:/mapping/page");
    }

    // GET /mapping/modelAndView2 -> 뷰 직접 렌더링
    @GetMapping("/modelAndView2")
    public ModelAndView modelAndView2() {
        ModelAndView mv = new ModelAndView("test/page");
        mv.addObject("msg", "ModelAndView2");
        return mv;
    }

    // GET /mapping/redirectView -> page로 리다이렉트
    @GetMapping("/redirectView")
    public String redirectView(RedirectAttributes ra) {
        ra.addFlashAttribute("state", "RedirectView");
        return "redirect:/mapping/page";
    }

    // GET /mapping/delete -> /mapping 으로 리다이렉트 (RedirectView 사용시 'redirect:' 붙이면 안 됨)
    @GetMapping("/delete")
    public RedirectView delete(RedirectAttributes ra) {
        ra.addFlashAttribute("state", "삭제 완료");
        return new RedirectView("/mapping");
    }

    // 외부 URL
    @GetMapping("/naver")
    public String naver() {
        return "redirect:https://naver.com";
    }

    @GetMapping("/local")
    public String local() {
        // 리액트 개발 서버 포트에 맞춰 수정(예: 3000, 5173 등)
        return "redirect:http://localhost:3000";
    }
}
