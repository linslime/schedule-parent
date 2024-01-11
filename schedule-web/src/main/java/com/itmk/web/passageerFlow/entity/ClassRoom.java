package com.itmk.web.passageerFlow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author java实战基地
 * @Version 3501754007
 */
@Data
@TableName("classroom")
public class ClassRoom {
    @TableId(type = IdType.AUTO)
    private Long roomId;
    private String roomName;
    private String roomAddress;
    private String roomSize;
}
