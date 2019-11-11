package com.wondersgroup.framework.commom.util;


import java.util.Collection;
import java.util.Map;


/**
 * @author chenlin
 * @create 2019-06-21 9:33
 * @description: 字符串帮助类
 * @version：1.0
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';


    /**
     * 方法作用：判断对象是否为空
     *
     * @param object 需要判断的对象
     * @return: boolean true表示空，false表示非空
     * @createDate: 2019/6/21 9:39
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:39
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNull(Object object) {

        return object == null;
    }

    /**
     * 方法作用：判断对象是否非空
     *
     * @param object 需要判断的对象
     * @return: boolean true表示非空，false表示空
     * @createDate: 2019/6/21 9:40
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:40
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }


    /**
     * 方法作用：获取参数不为空值，效果同oracle的nvl函数，
     * 如果value为空则返回defaultValue，否则返回自己
     *
     * @param value        需要判断的值
     * @param defaultValue 默认值
     * @return: T
     * @createDate: 2019/6/21 9:33
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:33
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 方法作用：判断一个Collection是否为空，包含List，Set，Queue等(实现Collection接口的都可以)
     *
     * @param coll 需要判断的容器
     * @return: boolean true表示空，false表示非空
     * @createDate: 2019/6/21 9:35
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:35
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 方法作用：判断一个Collection是否非空
     *
     * @param coll 需要判断的Collection
     * @return: boolean true表示非空，false表示空
     * @createDate: 2019/6/21 9:37
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:37
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 方法作用：判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     * @return: boolean  true：为空 false：非空
     * @createDate: 2019/6/21 9:41
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:41
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isEmpty(Object[] objects) {

        return isNull(objects) || (objects.length == 0);
    }

    /**
     * 方法作用：判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return: boolean true：非空 false：空
     * @createDate: 2019/6/21 9:42
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:42
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNotEmpty(Object[] objects) {

        return !isEmpty(objects);
    }

    /**
     * 方法作用：判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return: boolean true：为空 false：非空
     * @createDate: 2019/6/21 9:42
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:42
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isEmpty(Map<?, ?> map) {

        return isNull(map) || map.isEmpty();
    }

    /**
     * 方法作用：判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return: boolean true：非空 false：空
     * @createDate: 2019/6/21 9:43
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 9:43
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNotEmpty(Map<?, ?> map) {

        return !isEmpty(map);
    }

    /**
     * 方法作用：判断字符串是否为空串
     *
     * @param str 需要判断的对象
     * @return: boolean true表示空，false表示非空
     * @createDate: 2019/6/21 10:20
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:20
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isEmpty(String str) {

        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 方法作用：判断字符串是否为非空串
     *
     * @param str 需要判断的字符串
     * @return: boolean true表示非空，false表示空
     * @createDate: 2019/6/21 10:21
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:21
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isNotEmpty(String str) {

        return !isEmpty(str);
    }

    /**
     * 方法作用：判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 需要判断的对象
     * @return: boolean true表示是数据，false表示不是数据
     * @createDate: 2019/6/21 10:25
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:25
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean isArray(Object object) {

        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 方法作用：字符串去除空格
     *
     * @param str
     * @return: java.lang.String
     * @createDate: 2019/6/21 10:26
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:26
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String trim(String str) {

        return (str == null ? "" : str.trim());
    }

    /**
     * 方法作用：截取字符串
     *
     * @param str   需要截取的字符串
     * @param start 开始的索引位置
     * @return: java.lang.String
     * @createDate: 2019/6/21 10:27
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:27
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String substring(final String str, int start) {

        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 方法作用：
     *
     * @param str
     * @param start
     * @param end
     * @return: java.lang.String
     * @createDate: 2019/6/21 10:28
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 10:28
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String substring(final String str, int start, int end) {

        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 方法作用：下划线转驼峰命名
     *
     * @param str
     * @return: java.lang.String
     * @createDate: 2019/6/21 11:08
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 11:08
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String toUnderScoreCase(String str) {

        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        /* 前置字符是否大写 */
        boolean preCharIsUpperCase = true;
        /* 当前字符是否大写 */
        boolean curreCharIsUpperCase = true;
        /* 下一字符是否大写 */
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 方法作用：是否包含字符串
     *
     * @param str
     * @param strs
     * @return: boolean
     * @createDate: 2019/6/21 11:10
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 11:10
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static boolean inStringIgnoreCase(String str, String... strs) {

        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法作用：将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return: java.lang.String 转换后的驼峰式命名的字符串
     * @createDate: 2019/6/21 11:11
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 11:11
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String convertToCamelCase(String name) {

        StringBuilder result = new StringBuilder();
        /* 快速检查 */
        if (name == null || name.isEmpty()) {
            /*  没必要转换 */
            return "";
        } else if (!name.contains("_")) {
            /*  不含下划线，仅将首字母大写 */
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        /* 用下划线将原始字符串分割 */
        String[] camels = name.split("_");
        for (String camel : camels) {
            /*  跳过原始字符串中开头、结尾的下换线或双重下划线 */
            if (camel.isEmpty()) {
                continue;
            }
            /* 首字母大写 */
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }
}