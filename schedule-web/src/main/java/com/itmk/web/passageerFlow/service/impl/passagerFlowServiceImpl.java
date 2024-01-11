package com.itmk.web.passageerFlow.service.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.passageerFlow.entity.ListParm;
import com.itmk.web.passageerFlow.entity.PassagerFlow;
import com.itmk.web.passageerFlow.mapper.PassagerFlowMapper;
import com.itmk.web.passageerFlow.service.passagerFlowService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Service
public class passagerFlowServiceImpl extends ServiceImpl<PassagerFlowMapper, PassagerFlow> implements passagerFlowService {
    @Resource
    PassagerFlowMapper passagerFlowMapper;
    @Override
    public IPage<PassagerFlow> getList(ListParm listParm) {
        //构造分页查询对象
        IPage<PassagerFlow> page = new Page<>();
        page.setCurrent(listParm.getCurrentPage());
        page.setSize(listParm.getPageSize());
        //构造查询条件
        QueryWrapper<PassagerFlow> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(listParm.getRoomName())){
            query.lambda().like(PassagerFlow::getRoomName,listParm.getRoomName());
        }
        return this.baseMapper.selectPage(page,query);
    }

    public static List<PassagerFlow> getPassagerFlowList(){
        List<PassagerFlow> passagerFlows = new ArrayList<>();
        try {
            // 1. 创建 Workbook 对象
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("D:\\Desktop\\date.xlsx")));

            // 2. 创建 Sheet 对象
            Sheet sheet = workbook.getSheetAt(0); // 第一个工作表

            // 3. 遍历每一行，获取单元格数据
            for (Row row : sheet) {
                String roomName = row.getCell(0).toString();
                String dateTime = row.getCell(1).toString();
                String beginTime = row.getCell(2).toString();
                String endTime = row.getCell(3).toString();
                String passagerFlow = row.getCell(4).toString();

                float pF = Float.parseFloat(passagerFlow);
                int pf = (int) Math.ceil(pF/3.8);
                if (pf <= 2){
                    pf = 2;
                }
                PassagerFlow PF = new PassagerFlow();


                PF.setRoomName(roomName);
                PF.setDateTime(dateTime);
                PF.setBeginTime(beginTime);
                PF.setEndTime(endTime);
                PF.setPassagerFlow(pF);
                PF.setPassagerNumber(pf);
                passagerFlows.add(PF);

            }
            // 4. 关闭 Workbook 对象
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passagerFlows;
    }
    @Override
    public void save(){
        List<PassagerFlow> list = getPassagerFlowList();
        for(int i = 0; i < list.size();i++){
            passagerFlowMapper.insert(list.get(i).getRoomName(),list.get(i).getDateTime(),list.get(i).getBeginTime(),list.get(i).getEndTime(),list.get(i).getPassagerFlow(),list.get(i).getPassagerNumber());
        }
    }

    @Override
    public List<PassagerFlow> find(ListParm listParm){
        long start = (listParm.getCurrentPage() - 1l)* listParm.getPageSize();
        List<PassagerFlow> list = passagerFlowMapper.find(start,listParm.getPageSize(),listParm.getRoomName(),listParm.getDateTime());
//        List<PassagerFlow> list = passagerFlowMapper.find();
        System.out.println("aa");
        System.out.println(listParm);
        System.out.println(list);
        System.out.println("aa");
        return list;
    }

    @Override
    public int count(ListParm listParm){
        int count = passagerFlowMapper.count((listParm.getCurrentPage() - 1l)* listParm.getPageSize(),listParm.getPageSize(),listParm.getRoomName(),listParm.getDateTime());
        return count;
    }

    @Override
    public void removeById(Long id){
        passagerFlowMapper.removeById(id);
    }

    @Override
    public void update(Long id,String room_name,String dateTime,String beginTime,String endTime,float passagerFlow,int passagerNumber){
        passagerFlowMapper.update(id,room_name,dateTime,beginTime,endTime,passagerFlow,passagerNumber);
    }
}
