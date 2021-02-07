package org.amall.dubbo.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;

import java.util.Map;

public class StrUtils extends StrUtil{

    /**
     * 如果字符串是<code>null</code>或者&quot;&quot;，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * emptyToDefault(null, &quot;default&quot;)  = &quot;default&quot;
     * emptyToDefault(&quot;&quot;, &quot;default&quot;)    = &quot;default&quot;
     * emptyToDefault(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
     * emptyToDefault(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param object       要转换的对象字符串
     * @param defualtValue 默认字符串
     * @return 字符串本身或指定的默认字符串
     * @since 4.1.0
     */
    public static String emptyToDefault(Object object,String defualtValue){
        return StrUtil.emptyToDefault(Convert.toStr(object,defualtValue),defualtValue);
    }



    /**
     * MD5加密，生成16进制MD5字符串<br>
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示，如果模板为null，返回"null"
     * @param params   参数值
     * @return 格式化后的文本，如果模板为null，返回"null"
     */
    public static String md5(CharSequence template, Object... params) {
        return SecureUtil.md5(StrUtil.format(template,params));
    }

    /**
     * 转换为JSON字符串
     *
     * @param object 被转为JSON的对象
     * @return JSON字符串
     */
    public static String toJson(Object object){
        return JSONUtil.toJsonStr(object);
    }

    /**
     * JSON字符串转为map类对象，转换异常将被抛出
     *
     * @param json JSON字符串
     * @return map类对象
     * @since 3.1.2
     */
    public static Map<String,Object> parseJsonForMap(String json){
        if(JSONUtil.isJson(json)){
            return JSONUtil.toBean(json,Map.class);
        }else{
            return CollUtil.newHashMap();
        }
    }

    /**
     * JSON字符串转为map类对象，转换异常将被抛出
     *
     * @param json JSON字符串
     * @return map类对象
     * @since 3.1.2
     */
    public static Map<String,Object> parseJsonForMap(Object json){
        return parseJsonForMap(StrUtil.toString(json));
    }

}
