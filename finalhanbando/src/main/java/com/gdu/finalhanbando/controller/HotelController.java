package com.gdu.finalhanbando.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.finalhanbando.service.HotelService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/hotel")
@RequiredArgsConstructor
@Controller
public class HotelController {
  private final HotelService hotelService;
  
  
 /*************************** 리스트 ***************************************************/
  
  @GetMapping("list.do")
  public String hotelList() {
    return "hotel/list";
  }
  
  @ResponseBody
  @GetMapping("getList.do")
  public Map<String, Object> getHotelist(HttpServletRequest request){
    
    return hotelService.getHotelList(request);
  }
  
  @GetMapping("increseHit.do")
  public String increseHit(@RequestParam(value="hotelNo", required=false, defaultValue="0") int hotelNo) {
    int increseResult = hotelService.increseHit(hotelNo);
    if(increseResult == 1) {
      return "redirect:/hotel/detail.do?hotelNo=" + hotelNo;
    } else {
      return "redirect:/hotel/list.do";
    }
  }
  /*************************** 상세 ***************************************************/  
  @GetMapping("detail.do")
  public String hotelDetail(@RequestParam(value = "hotelNo", required = false, defaultValue = "0") int hotelNo, 
                                HttpServletRequest request, Model model) {
    
   hotelService.hoteDetail(request, hotelNo, model); 
    
   return "hotel/detail";
  } 
  
  /*************************** 작성 ***************************************************/  
  @GetMapping("write.form")
  public String write(Model model) {
    hotelService.regionList(model);
    hotelService.makeHotelNo(model);
    return "hotel/write";
  }
  @ResponseBody
  @GetMapping("roomList.do")
  public void roomList(HttpServletRequest request, Model model){
    
    return ;
  }
  
  
  @PostMapping("addHotel.do")
  public String writeHotel(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) throws Exception {
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@" + multipartRequest.getParameter("hotelName"));
   int hotelResult = hotelService.writeHotel(multipartRequest) ? 1 : 0;
    
    redirectAttributes.addFlashAttribute("hotelResult", redirectAttributes); 
    
    
    return "redirect:/hotel/list.do";
  }
  
  @GetMapping("addRoom.form")
  public String HotelRoom(@RequestParam(value="hotelNo", required=false, defaultValue="0")int hotelNo, Model model) {
   model.addAttribute("hotelNo", hotelNo);
    return "hotel/hotelRoom";  
  }
  
  @ResponseBody
  @PostMapping("addHotelRoom.do")
  public int writeRoom(MultipartHttpServletRequest multipartRequest, 
                        @RequestParam("files") List<MultipartFile> files , Model model) throws Exception {
    
    int addResult = hotelService.writeRoom(multipartRequest, files) ? 1 : 0;
    
    return addResult;
    
  }
  

  
   
  
  
}
