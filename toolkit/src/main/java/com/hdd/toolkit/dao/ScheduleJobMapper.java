package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleJobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);
}