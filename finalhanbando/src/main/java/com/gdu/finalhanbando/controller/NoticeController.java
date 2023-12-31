package com.gdu.finalhanbando.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.finalhanbando.dto.NoticeDto;
import com.gdu.finalhanbando.service.NoticeService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
  
  private final NoticeService noticeService;
  
  @GetMapping("/list.do")
  public String list(HttpServletRequest request, Model model) {
    noticeService.loadNoticeList(request, model);
    return "notice/list";
  }
  
  @PostMapping("/addNotice.do")
  public String addNotice(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = noticeService.addNotice(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/notice/list.do";
  }
  
  @GetMapping("/search.do")
  public String search(HttpServletRequest request, Model model){
    noticeService.LoadSearchList(request, model);
    return "notice/list";
  }
  
  @GetMapping("/write.form")
  public String write() {
    return "notice/write";
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam(value="noticeNo", required=false, defaultValue="0") int noticeNo 
      , Model model){
    NoticeDto notice = noticeService.loadNotice(noticeNo);
    model.addAttribute("notice", notice);
    return "notice/detail";
  }
  
  @PostMapping("/modifyNotice.do")
  public String modifyNotice(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int modifyResult = noticeService.modifyNotice(request);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/notice/detail.do?noticeNo=" + request.getParameter("noticeNo");
  }
  
  @PostMapping("/edit.form")
  public String edit(@ModelAttribute("notice") NoticeDto notice) {
    return "notice/edit";
  }
  
  @PostMapping("/remove.do")
  public String remove(@RequestParam(value="noticeNo", required=false, defaultValue="0") int noticeNo
                     , RedirectAttributes redirectAttributes) {
    int removeResult = noticeService.removeNotice(noticeNo);
    redirectAttributes.addFlashAttribute("removeResult", removeResult);
    return "redirect:/notice/list.do";
  
 }
  
  @ResponseBody
  @PostMapping(value="/imageUpload.do", produces="application/json")
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
    return noticeService.imageUpload(multipartRequest);
  }
  
}
