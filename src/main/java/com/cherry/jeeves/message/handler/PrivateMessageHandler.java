package com.cherry.jeeves.message.handler;

import com.beust.jcommander.internal.Lists;
import com.cherry.jeeves.domain.shared.Message;
import com.cherry.jeeves.service.WechatHttpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * 处理私人聊天消息
 */
@Component
@Slf4j
public class PrivateMessageHandler {

    private List<Credential> credentials = Lists.newArrayList();

    private CredentialRequirement requirement = new CredentialRequirement();

    @Autowired
    private WechatHttpService wechatHttpService;

    public void handle(Message message) {
        if (StringUtils.equals("周金雨", message.getFromUserName())) {
            final String content = message.getContent();
            if (StringUtils.isEmpty(message.getContent())) {
                return;
            }

            List<Boolean> matchCount = Lists.newArrayList();
            Credential credential = new Credential();
            requirement.getTypeMatch().stream().forEach(type -> {
                if (content.contains(type)) {
                    credential.setType(type);
                    matchCount.add(true);
                }
            });

            //工种类别必须匹配
            if (StringUtils.isEmpty(credential.getType())) {
                return;
            }
            requirement.getRankMatch().stream().forEach(rank -> {
                if (content.contains(rank)) {
                    credential.setRank(rank);
                    matchCount.add(true);
                }
            });
            requirement.getCityMatch().stream().forEach(city -> {
                if (content.contains(city)) {
                    credential.getCities().add(city);
                    matchCount.add(true);
                }
            });
            requirement.getSocialInsuranceMatch().stream().forEach(sl -> {
                if (content.contains(sl)) {
                    credential.setSocialInsurance(sl);
                    matchCount.add(true);
                }
            });

            requirement.getContractMatch().stream().forEach(contract -> {
                if (content.contains(contract)) {
                    credential.setContract(contract);
                    matchCount.add(true);
                }
            });

            if (matchCount.size() > 3) {
                credentials.add(credential);
                try {
                    wechatHttpService.sendText("周金雨", "缓存需求成功!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
