package com.geekerstar.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2020/2/1 12:32
 * @description
 */
@Data
@ApiModel("JVM信息")
public class JvmInfo implements Serializable {

    private static final long serialVersionUID = 5204122753822167287L;

    @ApiModelProperty("JVM 最大内存")
    private Double jvmMemoryMax;

    @ApiModelProperty("JVM 可用内存")
    private Double jvmMemoryCommitted;

    @ApiModelProperty("JVM 已用内存")
    private Double jvmMemoryUsed;

    @ApiModelProperty("JVM 缓冲区已用内存")
    private Double jvmBufferMemoryUsed;

    @ApiModelProperty("当前缓冲区数量")
    private Double jvmBufferCount;

    @ApiModelProperty("JVM 守护线程数量")
    private Double jvmThreadsdaemon;

    @ApiModelProperty("JVM 当前活跃线程数量")
    private Double jvmThreadsLive;

    @ApiModelProperty("JVM 峰值线程数量")
    private Double jvmThreadsPeak;

    @ApiModelProperty("JVM 已加载 Class 数量")
    private Double jvmClassesLoaded;

    @ApiModelProperty("JVM 未加载 Class 数量")
    private Double jvmClassesUnloaded;

    @ApiModelProperty("GC 时, 年轻代分配的内存空间")
    private Double jvmGcMemoryAllocated;

    @ApiModelProperty("GC 时, 老年代分配的内存空间")
    private Double jvmGcMemoryPromoted;

    @ApiModelProperty("GC 时, 老年代的最大内存空间")
    private Double jvmGcMaxDataSize;

    @ApiModelProperty("FullGC 时, 老年代的内存空间")
    private Double jvmGcLiveDataSize;
}
