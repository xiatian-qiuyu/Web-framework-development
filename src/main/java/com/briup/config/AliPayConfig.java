package com.briup.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AliPayConfig {
    /*
     * 服务网关  ---  沙箱环境
     */

    private static String serverURL = "https://openapi.alipaydev.com/gateway.do";

    /*
     * 应用id,
     */

    private static String APP_ID = "2021000122675642";

    /*
     * 用户私钥,可以替换成你们的自己的私钥
     *
     */

    private static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFLn+ZRyU80C7ojLsWZyApZzjS1PMMFiKaQbsQjsD5Xenq2PsmKoRBMVGa4QzzBBv75C2KWqor3/MpoyvHLNVewWzsvTU/n3RVnspSL/4gwVPp5C/fsDX36nEB/pivKkHwTrPF+++U2Y5lwzhHIaIqAND3GgiX3AGZJmLLn0BpcrRstDCzlytmQESXiPdk+5srLD+V+RXqM0yvcbi8neYWph/Twu8beXh6kYID8CHPyQNerd93yMoxkH+tA07tYYwNZjkgQsKKX9//njj7xWgnG+tz8H0214ERGEgOevdsYR/VZnPBYSPHu5+xY122rq0/WlFTIjztPFc4pWVQ87kxAgMBAAECggEAe+HpvQkpwSyhRZ2KdWdNylWCzNJaljaMz1IvEFLJe+v4p4RX8YOA7CdVEi0e33pGEYOJPPXi7tbtGCD4mtfATdi8WmYRfvHyw6Xl6pdL2uoaBSlFwtvlB4vivIk3O1Cib0a2AIa9r4bC5zO1SK0na8zAk+l6bs+uCOhk46Vpw+sfEH818Ye7J5Nwyg4OR0UKgqZ9n0x5bSzanKFaiXnXGOSnR4YM9FJT6XPMniYoNTPGUg8FWBANMuJ9PgPQbuFMwQGah3PRZJKhlGwwGvWW+qOLLB4rj0lOWB5rEQX2Ndk6vf6VTr4OtyGFxB//NckHa8erv942Vbx7kAlzyspr8QKBgQDHfpyblFrQu/BsIPNXUkdbAtcO68allVRXbKA7IkbTC7ob6r2IuHMBh86ny/VlOs5+ERcCA0dD3Vwy3jsDaFiv3RFK8YG+HqBEKP3Kk94yNmqV6CCw0iLbKnETE2Dsh27vW/xAQWbYmLtyALojXpe6ezeMCw7PO7hbeyNjQJhmZQKBgQCq54XpYloe8bl4wKIc6E45BzupJ7M4DGEtpx35RZyAMdsNmx3UgIiGAh6dBEkjWBdrOKU71h6W/hAtG8c9M3Mdcj+S/POmcNRkFjtblWE7rPZl/P0MYcvIvMtiUeuN3KvfPF70Mlq23kDmcLNtRoF3Iu262zo51/QX+ZJnPCbE3QKBgGWbC+KFnfeAMUSxR58q7eLhwGz+Ew/Uzb81OSrFG3N0HpcHaP9Qimq2I7/8toCXggJda+1/g/WBserKmx3yMHFp09vqw1z0tkXg5todk+bZJlIxhyzn6g7cP/kqPoxZslvA6upDJUGeEKJQ0n0fOYImLbVrEGDmFtCpF+3+QWCtAoGAFAkOXtzj2crWxnw2xRLK0M6zRNJdpU12OwbjcayiezjdCExOA/KJjsRC2H9/H4CdRjmezVP9xA/Gz7L4+YjFjwcswsuauMFzdMRScxshCgDt5ejxtXjljgPsB/u4lKnxEDugmhopHlUR4LIHpuDkHpZerlAzFELn38OnUSjxFpUCgYARTVeqa1abHwwwCbwk+DLqnyL4cQIblOJpguAiAyrrjCylF61xkAwve9VznwEt3GQBPFEcbfcw6DJny6FhDSHIz8o/+A/4x3mdgOiHgjuIRkSGurWLWdJRoMMtDOJaRATZWaUEYnjs9Chn0uFlxoUb8x4K27K9iaJZy2Wl/ObjAg==";
    /*
     * 发送数据的格式,  目前为json
     *
     */

    private static String format = "json";

    /*
     * 设置字符集的编码格式 utf-8
     */

    private static String CHARSET = "utf-8";

    /*
     * 支付宝公钥,  可以替换成你们自己的
     */

    private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApKbdUeelbLF+ZzjG+XtnlIBG9pL7rM72XZKi32K3fBfi/o06K7Qx6iOvoiUlFp9EpLNn4yK4468keAh3QgHSbpQvb/TIbbtuFjmkxHQTTytd1mlnev53rVYJXxmJIaUlIREYLN2M2holpFuKdEjRy8y+Nwqo45FYrNidFYLJ3uzdHg4RHg1BrFzvXhnLTt9hP9TRV72NI/ZpWXpunZv9but4BNuKmT5mG5t89hsGpbj2jsxOEzzfF/B29CftM7Q2OS+YdAejqPHcu9/UGBZZ7chmqyCDwjR+sOrC8OyFq4zAJlRPVbAnZLjH9/dBpp/CGZ9cNtFxMiG+8TPLjxX7lwIDAQAB";
    /*
     * 支付宝签名  目前采用的是RSA2
     */

    private static String signType = "RSA2";
    /*
     * 页面跳转同步通知页面   http      ?12345464.外网可以访问的地址
     */



    public static AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(serverURL,APP_ID,APP_PRIVATE_KEY,format,
                CHARSET,ALIPAY_PUBLIC_KEY,signType);
    }
}
