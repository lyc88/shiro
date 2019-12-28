package com.lyc.shiro.constant;

/**
 * 建议用枚举
 * 状态码规范 定义 可 参考
 */
/**
 * 服务异常
 *
 * 参考 https://www.kancloud.cn/onebase/ob/484204 文章
 *
 * 一共 10 位，分成四段
 *
 * 第一段，1 位，类型
 *      1 - 业务级别异常
 *      2 - 系统级别异常
 * 第二段，3 位，系统类型
 *      001 - 用户系统
 *      002 - 商品系统
 *      003 - 订单系统
 *      004 - 支付系统
 *      005 - 优惠劵系统
 *      ... - ...
 * 第三段，3 位，模块
 *      不限制规则。
 *      一般建议，每个系统里面，可能有多个模块，可以再去做分段。以用户系统为例子：
 *          001 - OAuth2 模块
 *          002 - User 模块
 *          003 - MobileCode 模块
 * 第四段，3 位，错误码
 *       不限制规则。
 *       一般建议，每个模块自增。
 */
public class Codes {

    /** 未登录 */
    public static final int OK = 200;

    /** 未登录 */
    public static final int UNAUTHEN = 401;

    /** 未授权，拒绝访问 */
    public static final int UNAUTHZ = 403;

    /** shiro相关的错误 */
    public static final int SHIRO_ERR = 500;

    /** 服务端异常 */
    public static final int SERVER_ERR = 500;
}
