package com.gdu.finalhanbando.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.finalhanbando.service.FaqService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class FaqController {
  private final FaqService faqService;
  
  @GetMapping("/faqList.do")
  public String faq(HttpServletRequest request) {
    return "notice/faqList";
  }
  
  
  @ResponseBody
  @GetMapping(value="/loadFaqList.do", produces="application/json")
  public Map<String, Object> fapList(HttpServletRequest request){
    return faqService.loadFaqList(request);
  }
  
  @GetMapping("faqCash.do")
  public String faqCash(HttpServletRequest request, Model model) {
    faqService.loadFaqCashList(request, model);
    return "notice/faqCash";
  }
  
  @GetMapping("faqKorea.do")
  public String faqKorea(HttpServletRequest request, Model model) {
    faqService.loadFaqKoreaList(request, model);
    return "notice/faqKorea";
  }
  
  @GetMapping("faqMember.do")
  public String faqMember(HttpServletRequest request, Model model) {
    faqService.loadFaqMemberList(request, model);
    return "notice/faqMember";
  }
  
  @GetMapping("/faqWrite.form")
  public String write(HttpServletRequest request, Model model) {
    faqService.getFaqDetail(request, model);
    return "notice/faqWrite";
  }
  
  @PostMapping("/addFaq.do")
  public String addNotice(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = faqService.addFaq(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/notice/faqList.do";
}
  
  @GetMapping("/faqDetailWrite.form")
  public String faqDetailWrite() {
    return "notice/faqDetailWrite"; 
  }
  
  @PostMapping("/addDetailFaq.do")
  public String addFaqDetail(HttpServletRequest request) {
      faqService.addFaqDetail(request);
      return "redirect:/notice/faqWrite.form";
  }
  
  @ResponseBody
  @PostMapping(value="/faqRemove.do" , produces="application/json")
  public Map<String, Object> faqRemove(@RequestParam(value="faqNo", required=false, defaultValue="0") int faqNo) {
    return faqService.removeFaq(faqNo);
 }
 
  @ResponseBody
  @PostMapping(value="/modifyFaq.do", produces="application/json") 
  public Map<String, Object> modifyFaq(HttpServletRequest request, RedirectAttributes redirectAttributes) {
      Map<String, Object> response = new HashMap<>();
      
      // Modify FAQ and get the result
      int modifyResult = faqService.modifyFaq(request);
      
      // Add the result to the response
      response.put("modifyResult", modifyResult);
      
      return response;
  }

  
  
  
}