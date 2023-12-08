package com.gdu.finalhanbando.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.finalhanbando.dto.FacilitiesDto;
import com.gdu.finalhanbando.dto.HotelDto;
import com.gdu.finalhanbando.dto.HotelImageDto;
import com.gdu.finalhanbando.dto.RegionDto;
import com.gdu.finalhanbando.dto.RoomFeatureDto;
import com.gdu.finalhanbando.dto.RoompriceDto;
import com.gdu.finalhanbando.dto.RoomtypeDto;

@Mapper
public interface HotelMapper {
  public List<HotelDto> selectHotelList(Map<String, Object> map);
  public int countHotel();
  public List<RoompriceDto> getListPrice(List<HotelDto> hotelDto);
  public int hotelHit(int hotelNo);
  public List<HotelDto> getReviewHotelList(Map<String, Object> map);
  public List<HotelDto> getRecommendHotelList(Map<String, Object> map);
  public List<HotelDto> getPriceHotelList(Map<String, Object> map);
  public List<RegionDto> getRegion();
  
  public int getHotelNo();
  public int insertHotelNo(int HotelNo);
  
  public int insertRoomtype(RoomtypeDto roomtypeDto);
  public int insertRoomFeature(RoomFeatureDto roomFeatureDto);
  public int insertRoomPrice(RoompriceDto roompriceDto);
  public int insertRoomImage(HotelImageDto hotelImageDto);
  
  public int updateHotel(HotelDto hotelDto);
  public int insertFacilities(FacilitiesDto facilitiesDto);
  
  public HotelDto getHotel(int hotelNo);
  public List<HotelImageDto> getHotelImage(int hotelNo);
  public FacilitiesDto getFacilityies(int hotelNo);
  
  public List<RoomtypeDto> getRoomtype(int hotelNo);
  public List<HotelImageDto> getRoomImage(List<RoomtypeDto> roomtypeDto);
  public List<RoomFeatureDto> getRoomFeature(List<RoomtypeDto> roomtypeDto);
  public List<RoompriceDto> getPrice(int hotelNo);
  
  public RoomtypeDto roomtype(int roomNo);
  public int countReserveRoom(Map<String, Object> map);
}
