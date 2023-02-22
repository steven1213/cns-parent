package com.steven.im.common.type;


import lombok.Getter;

/**
 * @author steven.cao
 */
@Getter
public enum CnsIMMsgType {

    /**
     * 文本消息
     */
    CNS_IM_TEXT_MSG("文本消息"),
    /**
     * 位置消息
     */
    CNS_IM_LOCATION_MSG("位置消息"),
    /**
     * 表情消息
     */
    CNS_IM_FACE_MSG("表情消息"),
    /**
     * 自定义消息
     */
    CNS_IM_CUSTOMER_MSG("自定义消息"),
    /**
     * 语音消息
     */
    CNS_IM_SOUND_MSG("语音消息"),
    /**
     * 图像消息
     */
    CNS_IM_IMAGE_MSG("图像消息"),
    /**
     * 文件消息
     */
    CNS_IM_FILE_MSG("文件消息"),
    /**
     * 视频消息
     */
    CNS_IM_VIDEO_MSG("视频消息"),

    /**
     * 视频消息
     */
    CNS_IM_RELAY_MSG("合并转发消息"),
    ;

    private final String desc;

    CnsIMMsgType(String desc) {
        this.desc = desc;
    }
}
