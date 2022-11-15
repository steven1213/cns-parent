package com.steven.cns.infra.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonUtilsTest {

    @Test
    public void toJsonStringIgnoreNull() {
        GsonTestDto gsonDto = new GsonUtilsTest().gsonTestDto1();
        String result = GsonUtils.toJsonStringIgnoreNull(gsonDto);
        Assert.assertNotNull(result);
    }

    @Test
    public void toJsonString() {
        GsonTestDto gsonDto = new GsonUtilsTest().gsonTestDto1();
        String result = GsonUtils.toJsonString(gsonDto);
        Assert.assertNotNull(result);
    }

    @Test
    public void strToJavaBean() {
        String gsonStr = new GsonUtilsTest().gsonStr1();
        GsonTestDto result = GsonUtils.strToJavaBean(gsonStr, GsonTestDto.class);
        Assert.assertNotNull(result);
    }

    @Test
    public void fromJson() {
        String gsonStr = new GsonUtilsTest().gsonStr1();
        GsonTestDto result = GsonUtils.fromJson(gsonStr, GsonTestDto.class);
        Assert.assertNotNull(result);
    }

    @Test
    public void strToList() {
        String gsonStr = new GsonUtilsTest().gsonListStr();
        List<GsonTestDto> result = GsonUtils.strToList(gsonStr, GsonTestDto.class);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void strToListMaps() {
        String gsonStr = new GsonUtilsTest().gsonListMapStr();
        List<Map<String, String>> result = GsonUtils.strToListMaps(gsonStr);
        Assert.assertEquals(2, result.size());
    }

//    @Test
//    public void strToMaps() {
//        String gsonStr = new GsonUtilsTest().gsonListMapStr();
//        Map<String,GsonTestDto> result = GsonUtils.strToMaps(gsonStr);
//        Assert.assertEquals(1, result.size());
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class GsonTestDto {
        private String name;

        private Integer age;

        private String email;
    }

    public GsonTestDto gsonTestDto1() {
        return GsonTestDto.builder()
                .name("steven")
                .email("steven.cao1213@gmail.com")
                .build();
    }

    public GsonTestDto gsonTestDto2() {
        return GsonTestDto.builder()
                .name("steven")
                .age(23)
                .email("steven.cao1213@gmail.com")
                .build();
    }

    public String gsonStr1() {
        return "{\"name\":\"steven\",\"age\":null,\"email\":\"steven.cao1213@gmail.com\"}";
    }

    public String gsonListStr() {
        return "[{\"name\":\"steven\",\"age\":null,\"email\":\"steven.cao1213@gmail.com\"},{\"name\":\"taome\",\"age\":28,\"email\":\"taome@gmail.com\"}]";
    }

    public String gsonListMapStr() {
        return "[{\"name\":\"taome\",\"age\":\"28\"},{\"name\":\"steven\",\"age\":\"30\"}]";
    }
}