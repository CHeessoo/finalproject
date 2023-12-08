package com.gdu.finalhanbando.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.finalhanbando.dto.FaqCaDto;
import com.gdu.finalhanbando.dto.FaqDto;

@Mapper
public interface FaqMapper {
  public List<FaqDto> getFaqList(Map<String, Object> map);
  public List<FaqDto> getFaqKoreaList(Map<String, Object> map);
  public List<FaqDto> getFaqCashList(Map<String, Object> map);
  public List<FaqDto> getFaqMemberList(Map<String, Object> map);
  public FaqDto getFaq(int faqNo);
  public int getFaqCount();
  public int insertFaqDetail(FaqCaDto faqCa);
  public List<FaqCaDto> getFaqDetail(Map<String, Object> map);
  public int insertFaq(FaqDto faq);
  public int deleteFaq(int FaqNo);
  public int updateFaq(FaqDto faq);

}