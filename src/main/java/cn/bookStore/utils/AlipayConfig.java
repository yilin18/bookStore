package cn.bookStore.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/7
 */
public class AlipayConfig {

    /* *
     *类名：AlipayConfig
     *功能：基础配置类
     *详细：设置帐户有关信息及返回路径
     *修改日期：2017-04-05
     *说明：
     *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
     */


//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100100638326";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCVIqtKMI5YED+rTUHFPBHg9fAutV3BVsjteTESr3kbQ2N5k+shGyfmLCzKlif3EgahB3ZKc2e2P4uBap0qg1ydTjUTgxe0vBzFvG+W3C/15d5BSZD8b0PwpaXlnimQ6g5AZ8qRjXHHx1FzfCwEonDq9dzb0jHSapyxz7K6LORhPRHUtTa7fFYMxJ5BbTi0Gr3Ums3srns/LAchyziYT1OLVL3veYeADTj3HE5Ct6Ih/lfLlS/yLVEU8nyKvw7u1QtwJIgchkBeO30C2Eljj2pWzA/mMsw1DOx7HzdIfkHRDZA8hu5qENxlHUXIoSU3gjG0xqvsneTN2pyYY+P9N4ETAgMBAAECggEAT+Vg6sAUoi0iTv12rNm/oktfv0Y+0iNSRZbVqXMEaIyzsCCEqRnJSFCGNoo3IvAvSPmcb8pf1403ib+6L1pstYF+gdOLUbItn+mRxrpzn0LkzQlCeVqKhVPZCqivEAm0VODF7zBdsm+fvUTauCjmYZoSFloHgvjGtaPV1lPSjhy9XsrPWfAM84gNUTAV+Y9OShRnIi+GtCTiWIOc0APFA9lzBqYr1LEIpwzzNU6f0QhyyWVhY/C98Jnhp0RjTuHno2VZdEG+JCsaomYC4NCun1DSyQ3qxsjfq+WpW0c+647L7F2fMFrEZYf7qxcJlvSoLD09+75n689swe0KcexeYQKBgQDU1n5PgH8RcAYGGIZPzScwMofM2ugLcGJvrdJ9VFKxK/n8RjjPejMBOcyYs8vdkGZwi6mAHDJxHWhHAFAH10F13cZoxZ6PliKujKpSsj8utqpS9q86CSw6yV2dIW23hQ6zOLtue8dsEO+2nl9Vt0VwCYk9F3KDyXwBoeAR35q4KQKBgQCzYQ4SDfKWQ5uERkLrG1LAH37ONxKu9BQbGIYHUTlGwHyceXTZQCmYq5xeaPLgQ4tr+6Di4WH5yH9MpWHesErt49HUjCcI+otKK6+q9VwUBNCoQhdEJgwTDsUUCvlO9+neM78MoBC8UTq/dI0Ncs4JP8E+pOXSXfw53Hi8NJMG2wKBgEIV26BZ2JqUevpXkRSkMZ/QgMQF0HkEyzu78BiEHyzt57ACKc+aNM4ToUhkw08nb/dQFjA2ER94LxGksjTNDIFJfDq56bLRvjEOcjA9MksR4q7h86SEhpw2O//3CjcqdFcHrYLvogsW8ydz40wdHM1d2KQEbMUowX1MQuOp10OJAoGATsRtk0wAnC8v2ROpSugCnE64NgFE+QJA2e0Y3ZmzFvhrP6KtxsBAA8RHs8VN8WB3loE2Eqp5hV+fVp7ArTKolHGXsEei1HSAOZ+ZUe7P8hJzNY9f/BLg9nVj4Ps4oo6GaYY7fGXFM4xPycZ6Mc+Z3cHbL5DlQkz29slzUuGbNkUCgYEAlG8D3jo8THnG09JHJSZQpHEP2lrSoK8ERMs6BtBphU8F7RNTMsEdl/q2N6gG8HB/32F5QHQyD5VoPN4zxVvNdPJOV+dQKfWkJFNelCCl9MovxYoyIteLiOoc+Tpw6grkGhdrl29Quwj0CH4HCNir/0tt1MKUj12M6zFFGslACzM=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6OlxipcTUpST8OxUBsq3jC0TILwP7sdWCJK4TAouNSTXODO9yCX9ZS0FUX/XhJp5IKNM+3hKQeN48zrATY8VgT7fB72yV9H37DDsVKSCbNM33z0buKjAVEc2CwGwKlVM+qC/QI6H7ibCSeCV1E5JPTJuqt9Ng6LaPX2R8oKKVJeExRKVk8Oc3srfHS18puI7BIsYaQfwkjRxbzN6pf0tPTEL/Nd/ruCaxO7Wmum4ax9xMnpcxlupcXORo2EI+0a1kMHLAZK5u48rhRq/0VLfABLdnkE3WkNvCNgsfPkOUqEUaK7bk16POoiOogufJLVoKzU1DlLfO2RVZHkImWvpVwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8888/bookStore_war/order/paySuccessNotify.do";
    // public static String notify_url = "http://sylbookstore.51vip.biz:33259/bookStore_war/order/paySuccessNotify.do";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8888/bookStore_war/order/paySuccessReturn.do";
    // public static String return_url = "http://sylbookstore.51vip.biz:33259/bookStore_war/order/paySuccessReturn.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



