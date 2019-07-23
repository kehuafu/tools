/**
 * 备注模块：
 * 1、android 9.0/P版本后网络请求需要添加《网络安全配置》
 * 步骤：
 * ①在res目下新建xmi文件夹，在xml文件夹中新建network_config.xml
 * <?xml version="1.0" encoding="utf-8"?>
 * <network-config>
 * <base-config cleartextTrafficPermitted="true" />
 * </network-config>
 * ②在AndroidManifest.xml文件application标签中添加
 * android:networkSecurityConfig="@xml/network_config"
 * android:usesCleartextTraffic="true"(或者只添加这个)
 */

package khf.edu.mytools.module.remarks;