package com.itmk.web.passageerFlow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.passageerFlow.entity.ListParm;
import com.itmk.web.passageerFlow.entity.PassagerFlow;

import java.util.List;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
public interface passagerFlowService extends IService<PassagerFlow> {
    IPage<PassagerFlow> getList(ListParm listParm);

    void save();

    List<PassagerFlow> find(ListParm listParm);

    int count(ListParm listParm);

    void removeById(Long id);

    void update(Long id,String room_name,String dateTime,String beginTime,String endTime,float passagerFlow,int passagerNumber);
}
