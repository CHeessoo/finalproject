package com.gdu.finalhanbando.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.finalhanbando.dto.InquiryAnswerDto;
import com.gdu.finalhanbando.dto.InquiryDto;
import com.gdu.finalhanbando.service.InquiryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class InquiryController {

  private final InquiryService inquiryService;
  
  @GetMapping("/inquirylist.do")
  public String inquirylist(HttpServletRequest request, Model model) {
    inquiryService.loadInquiryList(request, model);
    return "notice/inquirylist";
  }
  
  /* 1:1문의 작성하기 */
  @PostMapping("/addInquiry.do")
  public String addInquiry(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = inquiryService.addInquiry(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/notice/inquirylist.do";
  }
  
  @GetMapping("/inquirywrite.form")
  public String inquirywrite() {
    return "notice/inquirywrite";
  
  }
  
  @PostMapping("/inquiryremove.do")
  public String inquiryremove(@RequestParam(value="inquiryNo", required=false, defaultValue="0") int inquiryNo
                     , RedirectAttributes redirectAttributes) {
    int removeResult = inquiryService.removeInquiry(inquiryNo);
    redirectAttributes.addFlashAttribute("removeResult", removeResult);
    return "redirect:/notice/inquirylist.do";
  
 }
  
  @GetMapping("/inquirydetail.do")
  public String inquirydetail(@RequestParam(value="inquiryNo", required=false, defaultValue="0") int inquiryNo 
      , Model model){
    InquiryDto inquiry = inquiryService.loadInquiry(inquiryNo);
    model.addAttribute("inquiry", inquiry);
    InquiryAnswerDto answer = inquiryService.loadInquiryAnswer(inquiryNo);
    model.addAttribute("answer", answer);
    return "notice/inquirydetail";
  }
  
  @PostMapping("/modifyInquiry.do")
  public String modifyInquiry(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int modifyResult = inquiryService.modifyInquiry(request);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/notice/inquirydetail.do?inquiryNo=" + request.getParameter("inquiryNo");
  }
  
  /* 1:1문의 작성하기 */
  @PostMapping("/addInquiryAnswer.do")
  public String addInquiryAnswer(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = inquiryService.addInquiryAnswer(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/notice/inquirydetail.do?inquiryNo=" + request.getParameter("inquiryNo");
  }
  
  
  @PostMapping("/inquiryAnswerWrite.form")
  public String inquiryAnswerWrite(HttpServletRequest request, Model model) {
    model.addAttribute("inquiryNo", request.getParameter("inquiryNo"));
    return "notice/inquiryAnswerWrite";
  }
}
