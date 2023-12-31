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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.finalhanbando.dto.PackageDto;
import com.gdu.finalhanbando.dto.ReserveDto;
import com.gdu.finalhanbando.service.PackageService;
import com.gdu.finalhanbando.service.ReserveService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reserve")
@RequiredArgsConstructor
@Controller
public class ReserveController {

  private final ReserveService reserveService;
  private final PackageService packageService;
  
  // 패키지 예약관련 요청을 처리
  @GetMapping("/reserveList.do")
  public String list(HttpServletRequest request, Model model) {
    if(request.getParameter("userNo") != null) { // userNo가 파라미터로 넘어오는 경우 
      reserveService.loadReserveListByUser(request, model);
    } else { // 파라미터가 없는 경우
      reserveService.loadReserveList(request, model);
    }
    return "reserve/list";
  }
  
  @GetMapping("/write.form")
  public String reserve(HttpServletRequest request, Model model) {
    PackageDto pack = packageService.getPackage(Integer.parseInt(request.getParameter("packageNo")));
    model.addAttribute("pack", pack);
    model.addAttribute("resStart", request.getParameter("resStart").replace("-", "/"));
    return "reserve/write";
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam(value="reserveNo", required = false, defaultValue = "0") int reserveNo, Model model) {
    ReserveDto reserve = reserveService.loadReserve(reserveNo);
//    PaymentDto payLog = reserveService.loadPaymentByReserveNo(reserveNo);
    model.addAttribute("reserve", reserve);
//    model.addAttribute("payLog", payLog);
    return "reserve/detail";
  }
  
  @PostMapping("/edit.form")
  public String edit(@ModelAttribute("reserve") ReserveDto reserve, Model model, HttpServletRequest request) {
    String userNo = request.getParameter("userNo");
    String userName = request.getParameter("userName");
    String userMobile = request.getParameter("userMobile");
    model.addAttribute("userNo", userNo);
    model.addAttribute("userName", userName);
    model.addAttribute("userMobile", userMobile);
    return "reserve/edit";
  }
  
  @ResponseBody
  @GetMapping(value="/getTouristInfo.do", produces="application/json")
  public Map<String, Object> getTourists(HttpServletRequest request) {
    return reserveService.loadTourists(request);
  }
  
  @ResponseBody
  @PostMapping(value="/addReserve.do", produces="application/json")
  public Map<String, Object> addReserve(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
    return reserveService.addReserve(request);
  }
  
  @PostMapping("/addTourist.do")
  public String addTourist(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
    int addTouristResult = reserveService.addTourist(request);
    redirectAttributes.addFlashAttribute("addTouristResult", addTouristResult);
    return "redirect:/reserve/detail.do?reserveNo=" + request.getParameter("reserveNo");
  }

  @PostMapping("/modifyReserve.do")
  public String modifyBlog(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int modifyResult = reserveService.modifyReserve(request);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/reserve/detail.do?reserveNo=" + request.getParameter("reserveNo");
  }
  
  @PostMapping("/delete.do")
  public String removeReserve(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("removeResult", reserveService.removeReserve(request));
    return "redirect:/reserve/reserveList.do?userNo=" + request.getParameter("userNo");
  }
  

  // 호텔 예약관련 요청을 처리
  @GetMapping("/reserveHotelList.do")
  public String resHotelList(HttpServletRequest request, Model model) {
    reserveService.loadReserveHotelListByUser(request, model);
    return "reserve/listHotel";
  }
  
  @GetMapping("/writeHotel.form")
  public String reserveHotel(HttpServletRequest request, Model model) {
    model.addAttribute("hotel", reserveService.loadHotelInfoWithWriteform(Integer.parseInt(request.getParameter("hotelNo"))));
    model.addAttribute("room", reserveService.loadRoomInfoWithWriteform(Integer.parseInt(request.getParameter("roomNo"))));
    model.addAttribute("roomNo", request.getParameter("roomNo")); 
    // 체크인, 체크아웃, 총금액 받아와야 함
    return "reserve/writeHotel";
  }
  
  @GetMapping("/detailHotel.do")
  public String detailHotel(@RequestParam(value="reserveNo", required = false, defaultValue = "0") int reserveNo, Model model) {
    ReserveDto reserveHo = reserveService.loadReserveHotel(reserveNo);
    model.addAttribute("reserveHo", reserveHo);
    return "reserve/detailHotel";
  }
  
  @PostMapping("/addReserveHotel.do")
  public String addReserveHotel(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    Map<String, Object> map = reserveService.addReserveHotel(request);
    int addReserveHotelResult = (Integer) map.get("addResult");
    redirectAttributes.addFlashAttribute("addReserveHotelResult", addReserveHotelResult);
    return "redirect:/reserve/detailHotel.do?reserveNo=" + map.get("reserveNo");
  }
  
  
  
  
}
