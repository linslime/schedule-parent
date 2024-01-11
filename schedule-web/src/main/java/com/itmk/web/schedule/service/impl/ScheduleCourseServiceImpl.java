package com.itmk.web.schedule.service.impl;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.schedule.entity.*;
import com.itmk.web.schedule.mapper.ScheduleCourseMapper;
import com.itmk.web.schedule.service.ScheduleCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class ScheduleCourseServiceImpl extends ServiceImpl<ScheduleCourseMapper, ScheduleCourse> implements ScheduleCourseService {
    @Resource
    ScheduleCourseMapper scheduleCourseMapper;

    @Override
    public int find(Long id){
        return scheduleCourseMapper.find(id);
    }

    @Override
    public void deleteById(Long id){
        scheduleCourseMapper.deleteById(id);
    }

    @Override
    public void saveSchedule(ScheduleParm scheduleParm) {
        //获取星期
        List<Integer> weekList = scheduleParm.getWeeks();
        LocalDate startDate = scheduleParm.getStartDate();
        LocalDate endDate = scheduleParm.getEndDate();
        List<LocalDate> dateList = new ArrayList<>();
        while(startDate.compareTo(endDate) <=0){
            //获取所有的时间
            if(weekList.contains(startDate.getDayOfWeek().getValue())){
                dateList.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }
        //排课入库处理
        List<ScheduleCourse> list = new ArrayList<>();
        for(LocalDate date : dateList){
            ScheduleCourse schedule = new ScheduleCourse();
            schedule.setDateTime(date);
            schedule.setDuration(scheduleParm.getDuration());
            schedule.setTeacherId(scheduleParm.getTeacherId());
            schedule.setRoomId(scheduleParm.getRoomId());
            schedule.setBeginTime(scheduleParm.getBeginTime());
            schedule.setEndTime(scheduleParm.getEndTime());
            list.add(schedule);
        }
        //保存
        this.saveBatch(list);
    }

    @Override
    public void autoSchedule(AutoScheduleParm autoScheduleParm){
        System.out.println("hhh");
        List<ScheduleCourse> list = getScheduleCourses(autoScheduleParm);
        System.out.println("hhh");
        this.saveBatch(list);
    }

    public List<ScheduleCourse> getScheduleCourses(AutoScheduleParm autoScheduleParm){
        List<ScheduleCourse> scheduleCourses = new ArrayList<>();
        try {
            String[] arg = new String[] { "python", "D:\\MyDocument\\code\\fwwb\\main\\Schedule.py", String.valueOf(autoScheduleParm.getRoomId()),String.valueOf(autoScheduleParm.getStartDate()),String.valueOf(autoScheduleParm.getEndDate())};
            Process proc = Runtime.getRuntime().exec(arg);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            ScheduleCourse scheduleCourse = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
//                AutoScheduleParm autoScheduleParm1 = JSON.parseObject(line, AutoScheduleParm.class);
//                System.out.println(autoScheduleParm1);
                scheduleCourse = JSON.parseObject(line,ScheduleCourse.class);
                System.out.println(scheduleCourse);
                scheduleCourses.add(scheduleCourse);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return scheduleCourses;
    }

    @Override
    public List<ScheduleCourse> findAll(String teacherId, String roomId) {
        List<ScheduleCourse> scheduleCourses = scheduleCourseMapper.findAll(teacherId,roomId);
        return scheduleCourses;
    }

    public String getStringByTime(ScheduleCourse sc){
        DateTimeFormatter dfDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dfDate.format(sc.getDateTime());
        DateTimeFormatter dfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = dfTime.format(sc.getBeginTime());
        return date + " " + time;
    }

    public HashMap<String, ScheduleCourse> Split(ScheduleCourse sc, Long step, HashMap<String, ScheduleCourse> sC){
        LocalTime t1 = LocalTime.parse("00:00");
        LocalTime t2 = LocalTime.parse("23:40");
        LocalTime t3 = LocalTime.parse("06:00");
        LocalTime t4 = LocalTime.parse("23:59");
        System.out.println(sc);
        if(sc.getEndTime().isBefore(t3)){

            for(LocalTime time = sc.getBeginTime();time.isAfter(t3);time = time.plusMinutes(step)){
                ScheduleCourse s = new ScheduleCourse();
                s.setId(sc.getId());
                s.setRoomId(sc.getRoomId());
                s.setTeacherId(sc.getTeacherId());
                s.setDateTime(sc.getDateTime());
                s.setBeginTime(time);
                s.setEndTime(time.plusMinutes(step));
                if(time.plusMinutes(step).isBefore(t3)){
                    s.setEndTime(t4);
                }
                s.setDuration(step);
                s.setTeacherName(sc.getTeacherName());
                s.setRoomName(sc.getRoomName());
                s.setColor(sc.getColor());
                s.setTeacherDesc(sc.getTeacherDesc());
                String key = getStringByTime(s);
                if(sC.containsKey(key)){
                    String value = sC.get(key).getTeacherName() + s.getTeacherName() + "：" + s.getTeacherDesc() + "；";
                    sC.get(key).setTeacherName(value);
                }else{
                    String value = s.getTeacherName() + "：" + s.getTeacherDesc() + "；";
                    s.setTeacherName(value);
                    sC.put(key,s);
                }
            }
        }else{
            for(LocalTime time = sc.getBeginTime();time.isBefore(sc.getEndTime());time = time.plusMinutes(step)){
                ScheduleCourse s = new ScheduleCourse();
                s.setId(sc.getId());
                s.setRoomId(sc.getRoomId());
                s.setTeacherId(sc.getTeacherId());
                s.setDateTime(sc.getDateTime());
                s.setBeginTime(time);
                s.setEndTime(time.plusMinutes(step));
                s.setDuration(step);
                s.setTeacherName(sc.getTeacherName());
                s.setRoomName(sc.getRoomName());
                s.setColor(sc.getColor());
                s.setTeacherDesc(sc.getTeacherDesc());

                String key = getStringByTime(s);
                if(sC.containsKey(key)){
                    String value = sC.get(key).getTeacherName() + s.getTeacherName() + "：" + s.getTeacherDesc() + "；";
                    sC.get(key).setTeacherName(value);
                }else{
                    String value = s.getTeacherName() + "：" + s.getTeacherDesc() + "；";
                    s.setTeacherName(value);
                    sC.put(key,s);
                }
            }
        }
        return sC;
    }

    @Override
    public  List<ScheduleCourse> conformity(List<ScheduleCourse> scheduleCourses) {
        // 创建 HashMap 对象 Sites
        List<ScheduleCourse> list = new ArrayList<>();
        HashMap<String, ScheduleCourse> sC = new HashMap<String, ScheduleCourse>();
        for(ScheduleCourse i : scheduleCourses) {;
            sC = Split(i,60l,sC);
        }
        for(ScheduleCourse i :sC.values()){
            list.add(i);
        }
        return list;
    }

    @Override
    public List<ScheduleCourse> selectCourseSchedulingList(ListParm query) {
        //条件构造器
       QueryWrapper<ScheduleCourse> queryWrapper = new QueryWrapper<>();
//       queryWrapper.lambda().in(query.getRoomIdList() != null && query.getRoomIdList().size() > 0,ScheduleCourse::getRoomId)
//               .in(query.getCourseIdList() != null && query.getCourseIdList().size() > 0,ScheduleCourse::getCourseId)
//                .in(query.getTeacherIdList() != null && query.getTeacherIdList().size() > 0,ScheduleCourse::getTeacherId)
//                .ge(StringUtils.isNotEmpty(query.getStartDate()), ScheduleCourse::getDateTime, query.getStartDate())
//                .le(StringUtils.isNotEmpty(query.getEndDate()), ScheduleCourse::getDateTime, query.getEndDate())
//                .eq(StringUtils.isNotEmpty(query.getBeginTime()), ScheduleCourse::getBeginTime, query.getBeginTime())
//                .eq(StringUtils.isNotEmpty(query.getEndTime()), ScheduleCourse::getEndTime, query.getEndTime());
        return this.baseMapper.selectCourseSchedulingList(queryWrapper);
    }

    //时间转为分钟
    private Integer toMinute(LocalTime time) {
        return time.getHour() * 60 + time.getMinute();
    }

    private boolean isBetween(LocalTime time, LocalTime destTime1, LocalTime destTime2) {
        Integer minute = toMinute(time);
        return toMinute(destTime1) < minute && minute < toMinute(destTime2);
    }

    // 判断时间是否冲突
    private boolean timeHasUsed(LocalTime beginTime, LocalTime endTime, LocalTime dbBeginTime,
                            LocalTime dbEndTime) {
        return isBetween(beginTime, dbBeginTime, dbEndTime)
                || isBetween(endTime, dbBeginTime, dbEndTime)
                || isBetween(dbBeginTime, beginTime, endTime)
                || isBetween(dbEndTime, beginTime, endTime)
                || (beginTime.equals(dbBeginTime) && endTime.equals(dbEndTime));
    }
    @Override
    public List<Option> getDesc(){
        return scheduleCourseMapper.getDesc();
    }

    @Override
    public int count(String teacherId, String roomId){
        return scheduleCourseMapper.count(teacherId,roomId);
    }
}
