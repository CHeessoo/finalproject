package com.gdu.finalhanbando.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.finalhanbando.dto.InquiryAnswerDto;

@Mapper
public interface InquiryAnswerMapper {

  public int insertInquiryAnswer(InquiryAnswerDto answer);
  public InquiryAnswerDto getInquiryAnswer(int inquiryNo);
}
