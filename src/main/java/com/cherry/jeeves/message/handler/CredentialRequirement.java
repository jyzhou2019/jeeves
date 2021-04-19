package com.cherry.jeeves.message.handler;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Set;

/**
 * 需求
 */
@Data
public class CredentialRequirement {

    /**
     * 级别匹配关键字
     */
    private Set<String> rankMatch = Sets.newHashSet("一级", "二级");

    /**
     * 房建、水利、
     */
    private Set<String> typeMatch = Sets.newHashSet("房建", "机电", "市政", "水利", "公路", "市政质检员", "市政施工员");

    /**
     * 城市
     */
    private Set<String> cityMatch = Sets.newHashSet("杭州", "绍兴", "临安", "萧山", "温州", "义务", "金华", "宁波", "浙江", "省内");

    /**
     * 社保匹配
     */
    private Set<String> socialInsuranceMatch = Sets.newHashSet("唯一社保", "社保唯一", "省内无社保");

    /**
     * 签约方式匹配
     */
    private Set<String> contractMatch = Sets.newHashSet("季度签", "一年签", "1年签", "年签", "两年", "2年", "2年签", "二年签", "为期一年");
}
