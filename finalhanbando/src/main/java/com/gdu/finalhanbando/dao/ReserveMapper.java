package com.gdu.finalhanbando.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.finalhanbando.dto.PaymentDto;
import com.gdu.finalhanbando.dto.ReserveDto;
import com.gdu.finalhanbando.dto.TouristDto;

@Mapper
public interface ReserveMapper {
  
  public int insertReserve(ReserveDto reserve);
  public int insertTourist(TouristDto tourist);
  public int insertReserveHotel(ReserveDto reserve);
  public int insertPayment(PaymentDto payment);
  
  public int getReserveCount();
  public int getReserveCountByUserNo(int userNo);
  public int getReserveHotelCountByUserNo(int userNo);
  
  public List<ReserveDto> getReserveList(Map<String, Object> map);
  public List<ReserveDto> getReserveListByUser(Map<String, Object> map);
  public List<ReserveDto> getReserveHotelListByUser(Map<String, Object> map);
  
  public ReserveDto getReserve(int reserveNo);
  public List<TouristDto> getTourists(int reserveNo);
  public PaymentDto getPaymentBy(Map<String, Object> map);
  public ReserveDto getReserveHotel(int reserveNo);
  
  public int updateReserve(ReserveDto reserve);
  public int deleteReserve(int reserveNo);
  
  public int updateReserveStatus(Map<String, Object> map);
}
