package com.hdd.toolkit.model;

import java.io.Serializable;
import lombok.Data;

/**
 * schedule_job
 * @author 
 */
@Data
public class ScheduleJob implements Serializable {
    private Long id;

    /**
     * 工程名
     */
    private String jobName;

    private static final long serialVersionUID = 1L;
}