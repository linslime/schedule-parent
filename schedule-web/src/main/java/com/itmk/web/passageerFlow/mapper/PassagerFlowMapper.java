package com.itmk.web.passageerFlow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.passageerFlow.entity.PassagerFlow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface PassagerFlowMapper extends BaseMapper<PassagerFlow> {

    @Insert("insert into passager_flow(room_name,dateTime,beginTime,endTime,passagerFlow,passagerNumber) values(#{roomId},#{dateTime},#{beginTime},#{endTime},#{passagerFlow},#{passagerNumber})" )
    void insert(String roomId, String dateTime, String beginTime,String endTime,float passagerFlow,int passagerNumber);

    @Select("select * from passager_flow where ('' = #{dateTime} or #{dateTime} = dateTime)and('' = #{roomName} or #{roomName} = room_name) limit #{start},#{pageSize}")
    List<PassagerFlow> find(long start,long pageSize,String roomName,String dateTime);

    @Select("select count(All *) from passager_flow where ('' = #{dateTime} or #{dateTime} = dateTime)and('' = #{roomName} or #{roomName} = room_name)")
    int count(long start,long pageSize,String roomName,String dateTime);

    @Delete("delete from passager_flow where id = #{id}")
    void removeById(Long id);

    @Update("update passager_flow set room_name=#{room_name} ,dateTime=#{dateTime},beginTime=#{beginTime},endTime=#{endTime},passagerFlow=#{passagerFlow},passagerNumber=#{passagerNumber}  where id = #{id}")
    void update(Long id,String room_name,String dateTime,String beginTime,String endTime,float passagerFlow,int passagerNumber);
}
