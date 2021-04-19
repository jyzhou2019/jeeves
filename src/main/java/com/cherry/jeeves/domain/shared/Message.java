package com.cherry.jeeves.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @JsonProperty
    private String MsgId;
    @JsonProperty
    private String FromUserName;
    @JsonProperty
    private String ToUserName;
    @JsonProperty
    private int MsgType;
    @JsonProperty
    private String Content;
    @JsonProperty
    private long Status;
    @JsonProperty
    private long ImgStatus;
    @JsonProperty
    private long CreateTime;
    @JsonProperty
    private long VoiceLength;
    @JsonProperty
    private long PlayLength;
    @JsonProperty
    private String FileName;
    @JsonProperty
    private String FileSize;
    @JsonProperty
    private String MediaId;
    @JsonProperty
    private String Url;
    @JsonProperty
    private int AppMsgType;
    @JsonProperty
    private int StatusNotifyCode;
    @JsonProperty
    private String StatusNotifyUserName;
    @JsonProperty
    private RecommendInfo RecommendInfo;
    @JsonProperty
    private int ForwardFlag;
    @JsonProperty
    private AppInfo AppInfo;
    @JsonProperty
    private int HasProductId;
    @JsonProperty
    private String Ticket;
    @JsonProperty
    private int ImgHeight;
    @JsonProperty
    private int ImgWidth;
    @JsonProperty
    private int SubMsgType;
    @JsonProperty
    private long NewMsgId;
    @JsonProperty
    private String OriContent;

}
