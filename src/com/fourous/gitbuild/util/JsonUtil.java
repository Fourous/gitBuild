package com.fourous.gitbuild.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.fourous.gitbuild.base.exception.MergeException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Json转换工具类
 */
@SuppressWarnings("all")
public class JsonUtil {
    public static Gson gson = new Gson();

    /**
     * json解析器
     **/
    public static JsonParser parser = new JsonParser();

    private static final Type typeMap = new TypeToken<Map>() {
    }.getType();
    private static final Type typeList = new TypeToken<ArrayList>() {
    }.getType();
    /**
     * 这里链接到license文件，对JSON可以加入license实现控制
     */
    public static final String k;

    static {
        StringBuilder a = new StringBuilder();
        a.append("license");
        k = a.toString();
    }

    /**
     * 将json字符串转为指定对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将字符串转换为MAP
     *
     * @param json
     * @return
     */
    public static Map toMap(String json) {
        return gson.fromJson(json, Map.class);
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将JSON字符串转换为HashMap
     *
     * @param json
     * @return
     */
    public static Map getJsonToMap(String json) {
        return gson.fromJson(json, typeMap);
    }

    /**
     * 将JSON字符串转换为ArrayList
     *
     * @param json
     * @return
     */
    public static ArrayList getJsonToList(String json) {
        return gson.fromJson(json, typeList);
    }

    /**
     * 解析JOSN字符串，返回ArrayList，可以指定集合内的对象类型
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> getJsonToList(String json, Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        //解析json字符串
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(json);
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                T oneBean = gson.fromJson(jsonArray.get(i), clazz);
                list.add(oneBean);
            }
        } else {
            T oneBean = gson.fromJson(jsonElement, clazz);
            list.add(oneBean);
        }
        return list;
    }

    /**
     * 将JSON字符串转换为ArrayListBean
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getJsonToListBean(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将HashMap字符串转换为JSON
     *
     * @param map
     * @return
     */
    public static String getMapToJson(Map map) {
        return gson.toJson(map);
    }

    /**
     * 判断给定的字符串是否满足JSON格式
     *
     * @param jsonStr
     * @return
     */
    public static boolean enableJson(String jsonStr) {
        boolean rs = false;
        if (jsonStr != null && !"".equals(jsonStr)) {
            try {
                if (jsonStr.matches("^\\[.*\\]$")) {
                    gson.fromJson(jsonStr.toString(), List.class);
                    rs = true;
                }
                if (jsonStr.matches("^\\{.*\\}$")) {
                    gson.fromJson(jsonStr.toString(), Map.class);
                    rs = true;
                }
            } catch (Exception e) {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * 判断给定字符串是否满足JSON数组格式
     *
     * @param jsonStr
     * @return
     */
    public static boolean isJsonArray(String jsonStr) {
        if (enableJson(jsonStr)) {
            try {
                gson.fromJson(jsonStr.toString(), List.class);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断json字符串是否为一个对象
     *
     * @param json
     * @return
     */
    public static boolean isJSONObject(String json) {
        return parser.parse(json).isJsonObject();
    }

    /**
     * 判断json字符串是否为空
     *
     * @param json
     * @return
     */
    public static boolean isJSONNULL(String json) {
        return parser.parse(json).isJsonNull();
    }

    /**
     * 解析JSON字符串为一个json对象
     *
     * @param json
     * @return
     */
    public static JsonObject parserToJsonObject(String json) {
        JsonElement element = parser.parse(json);
        if (element.isJsonObject()) {
            return element.getAsJsonObject();
        } else {
            throw new JsonSyntaxException("解析JSON字符串错误，预计为JSON对象，解析为JSON" + json);
        }
    }

    /**
     * 解析MAP为一个json对象
     *
     * @param jsonMap
     * @return
     */
    public static JsonObject parserToJsonObject(Map jsonMap) {
        String mapToJson = getMapToJson(jsonMap);
        return parserToJsonObject(mapToJson);
    }

    /**
     * 合并JSON文件，将target变量表示的JSON字符串合并到base变量表示的JOSN字符串
     *
     * @param base
     * @param target
     * @return
     * @throws JsonSyntaxException
     * @throws MergeException
     */
    public static String merge(String base, String target) throws JsonSyntaxException, MergeException {
        JsonParser parser = new JsonParser();
        JsonElement baseElement = parser.parse(base);
        JsonElement targetElement = parser.parse(target);

        JsonElement result = merge(baseElement, targetElement);
        return result.toString();
    }

    public static JsonElement merge(JsonElement baseElement, JsonElement targetElement) throws MergeException {
        if (baseElement.isJsonObject() && targetElement.isJsonObject()) {
            //如果基准JSON元素和目标元素都是JSON对象
            JsonObject baseObject = baseElement.getAsJsonObject();
            JsonObject targetObeject = targetElement.getAsJsonObject();

            //循环目标JSON对象，将所有属性合并到基准JSON对象
            Set<Map.Entry<String, JsonElement>> targetEntries = targetObeject.entrySet();
            for (Map.Entry<String, JsonElement> entry : targetEntries) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();

                if (value.isJsonPrimitive()) {
                    baseObject.add(key, value);
                } else if (value.isJsonObject()) {
                    JsonElement merge = merge(baseObject.get(key), value);
                    baseObject.add(key, merge);
                } else if (value.isJsonArray()) {
                    JsonElement merge = merge(baseObject.get(key), value);
                    baseObject.add(key, merge);
                } else if (value.isJsonNull()) {
                    baseObject.add(key, value);
                }
            }
            return baseObject;
        } else if (baseElement.isJsonArray() && targetElement.isJsonArray()) {
            //如果基准JSON元素和目标JSON元素都是JSON数组
            //JSON数组合并规则就是将值合并
            JsonArray baseArray = baseElement.getAsJsonArray();
            JsonArray targetArray = baseElement.getAsJsonArray();
            baseArray.addAll(targetArray);
            return baseArray;
        } else {
            //若是基准元素和目标元素不相同，或者不为JSON对象或者数组，抛出合并异常
            StringBuffer errorMessage = new StringBuffer();
            errorMessage.append("JSON合并异常，基本元素或者目标元素不为JOSN对象或者JSON数组");
            errorMessage.append(StringUtil.LINE_SEPARATOR);
            errorMessage.append("基准元素为：").append(baseElement.toString()).append(StringUtil.LINE_SEPARATOR);
            errorMessage.append("目标元素为：").append(targetElement.toString()).append(StringUtil.LINE_SEPARATOR);
            throw new MergeException(errorMessage.toString());
        }
    }
}
