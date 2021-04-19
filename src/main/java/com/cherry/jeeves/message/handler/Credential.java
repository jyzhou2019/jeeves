package com.cherry.jeeves.message.handler;

import com.beust.jcommander.internal.Sets;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Credential {

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 出 或者 是 进
     */
    private boolean needed;

    /**
     * 级别
     */
    private String rank;

    private String type;

    private Set<String> cities = Sets.newHashSet();

    private String socialInsurance;

    private String contract;

    private Date createTime;
}
