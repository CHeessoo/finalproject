package com.gdu.finalhanbando.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gdu.finalhanbando.dto.InquiryAnswerDto;
import com.gdu.finalhanbando.dto.InquiryDto;

public interface InquiryService {
  public void loadInquiryList(HttpServletRequest request, Model model);
  public int addInquiry(HttpServletRequest request);
  public InquiryDto loadInquiry(int inquiryNo);
  public int removeInquiry(int inquiryNo);
  public int modifyInquiry(HttpServletRequest request);
  public int addInquiryAnswer(HttpServletRequest request);
  public InquiryAnswerDto loadInquiryAnswer(int inquiryNo);
}

