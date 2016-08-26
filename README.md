

#XVolley框架


> XVolley 对 Google 提供的 Volley框架进行拓展，使其支持自定义Http报文，并在提供了一个自定义Http报文的工具包

[TOC]



## 项目依赖

XVolley依赖的项目有

网络请求框架 `volley.jar`

Json解析工具 `gson-2.4.jar`

android开发常用的工具类 `androidtools.jar`

> 直接复制xvolley包到项目中，其自带依赖的jar包




## 使用步骤



### 1、复制xvolley包到项目中，并设置项目依赖


### 2、定义一个BasicAPI的子类，或是二次封装后的子类（如LSAPI）实现其抽象方法，并在构造函数中完成传值或自定义报文。





## 框架介绍


### 框架包部分

>**xvolley** 为框架包

#### com.chenjiarun.xvolley.app.VolleyApp

> 继承了 androidtools.jar 包中的 com.chenjiarun.android.tools.app.App ，作为项目的Application，并在App启动时，初始化 Volley 框架的 RequestQueue，以单例模式向其它类提供 RequestQueue 与自身——VolleyApp



#### com.chenjiarun.xvolley.net.NetBuild

> 网络请求的配置文件，目前只有编码

```
public static String ENCODING = "UTF-8";
```

#### com.chenjiarun.xvolley.net.ParamType

> 请求时，body的类型，此处分为三种，除了常用的raw和formData，还定义了一种DIY的类型，即是自定义报文，body完全自定义，可按照具体需求进行设置。

**注：此处的raw与传统的raw方式存在区别，更多是指通过raw传输json数据的方式，所以框架会自动将传入的参数转化为JSON字符串**


```
public enum ParamType {

    raw, formData, DIY;

}
```



#### com.chenjiarun.xvolley.net.BasicAPI `abstract`

 定义了	 请求参数 `Map<String, String> params` ，并提供以下方法：

> `addParam(String key, Object value)` 用于添加
> 
> `getParams()` 用于获取全部参数
> 
> `getParamsEncoding()` 用于获取参数编码





定义了 请求头部 `Map<String, String> headers`，并提供以下方法：

>`addHeader(String key, String value)`用于添加
> 
> `modifyHeader(String key, String value)`用于修改
> 
> `getHeaders()`用于获取全部Header




定义了 抽象方法

```
 public abstract int getMethod(); //获取Http请求的方法，如Get、Post
 
 public abstract String getUrl(); //获取Http请求的url，此处的url指的是未拼接 url参数的
 
 public abstract void requestSuccess(JSONObject data, String msg); //volley 请求成功
 
 public abstract void requestFailure(Long code, String msg); //volley 请求失败
```

实现了 以下方法

> `getParamType()` 指定请求的类型，一共有三种，详见 `ParamType.java`,默认为formData，如需更换，请在API子类重载此方法

```
public ParamType getParamType() {
	return ParamType.formData;
}
```

>`getFinalUrl()` 获取完整的URL，此处的url指的是已经完成 url参数拼接的


> `getBody()` 获取报文body，在此方法中，对`ParamType.java`三种请求类型进行了区分，raw格式会自动转化为json字符串，formData会自动完成参数拼接转化，DIY会完成自定义报文的设置。


> `private byte[] diyBody = null;` 暂存自定义报文
> 
> `private String diyBodyContentType` 暂存自定义报文的BodyContentType
> 
> 皆提供了getter和setter方法



#### com.chenjiarun.xvolley.net.BasicRequest 

> 继承了 Volley框架的 com.android.volley.toolbox.JsonObjectRequest，作用是将com.chenjiarun.xvolley.net.BasicAPI定义的内容转化为一个Volley请求，并重写了`getBodyContentType`方法，进行以下处理，当遇到自定义报文时，重新设置Body的ContentType

```

@Override
public String getBodyContentType() {

	if (StringUtils.isNoEmpty(api.getDiyBodyContentType())) {
		
		return api.getDiyBodyContentType();

	}

	return super.getBodyContentType();
	
}

```

同时也重写了 `parseNetworkResponse(NetworkResponse response)`方法,将返回的数据转化为`UTF-8`编码格式



#### com.chenjiarun.xvolley.net.BasicHttp

> 请求的实际发起处，此处将com.chenjiarun.xvolley.net.BasicAPI 转化为 com.chenjiarun.xvolley.net.BasicRequest，并放进Volley的请求队列中，如

```

VolleyApp.getQueue().add(request);

```

***注：本类同时会进行一些请求的日志输出***



#### com.chenjiarun.xvolley.util.NetUtils

> 提供了如下工具方法：


> public static byte[] encodeParameters(Map<String, String> params, String paramsEncoding) 
> 
> 将参数转为body报文（formData模式）

> public static byte[] encodeParametersToJson(Map<String, String> params, String paramsEncoding)
> 
> 将参数转转为body报文（raw模式下的json传输格式）



#### com.chenjiarun.xvolley.net.XVolleyConstant

>定义一些自定义报文会使用到的常量

#### com.chenjiarun.xvolley.net.XVolleyMessage

> 在XVolley的自定义报文中，请求参数分为2种，第一种是文本参数，使用`addText`方法添加，一种是文件参数，使用`addFile`方法添加,XVolleyMessage为自定义报文的实体类，维护一个输出流`ByteArrayOutputStream bos` 当添加文本参数或文件参数，会动态向其写入对应的字节流。

最终通过以下方法获得body内容

> public byte[] getMessage() throws IOException

通过以下方法获得 bodyContentType

> public String getBodyContentType()


#### com.chenjiarun.xvolley.net.XVolleyUtils

> 用于生成自定义报文的内容，提供以下方法

```

// 生成Http报文的分隔符
public static String generateBoundary(); 

//获取报文参数的第一行
public static byte[] generateBeginLine(String boundary); 

//获取报文参数结束行
public static byte[] generateEndLine(String boundary); 

// 为Text型参数生成 Content-Disposition
public static byte[] generateContentDispositionForText(String key);

//为文件型参数生成 Content-Disposition
public static byte[] generateContentDispositionForFile(String key, String fileName);

//把文件写到输出流中
public static void writeFileToOutputStream(File file, ByteArrayOutputStream bos);



```






### 示例包部分


>**XVolleyExample** 为示例包


#### com.chenjiarun.xvolley.xvolleyexample.MainActivity

> 程序入口,发起一次上传的请求


#### com.chenjiarun.xvolley.xvolleyexample.LSAPI

> 对框架中的`BasicAPI`进行了二次封装，方便具体项目中，需要设置一些统一的Header或者Param的情况，并中转了请求成功`requestSuccess`与失败`requestFailure` 的回调，中转方法如下：

```
  public abstract void success(JSONObject data, String msg) throws Exception;

  public abstract void error(Long code, String msg);

```

**注：此处做中转的原因是多加一层，可在此层中进行请求异常的统一处理，如Json解析错误等**


#### com.chenjiarun.xvolley.xvolleyexample.LSHttp


> 对框架中的`BasicHttp` 进行了二次封装，在其中处理Volley返回的 `onErrorResponse` 和
 `onResponse` 
 
> 如果需要对接口返回的内容做拦截，可以此类中进行,如accessToken过期的重发处理等，最终请求成功与失败都会回调`LSAPI` 的 `requestSuccess`和 `requestFailure`方法。

 
#### com.chenjiarun.xvolley.xvolleyexample.ImageAPI
 
> 示例API，继承了LSAPI，
> 
> 指定了请求方式为POST、
> 
> 指定了URL，
> 
> 设置请求模式为自定义报文（`ParamType.DIY`），
> 
> 并在构造方法中，将出入的参数封装为`XVolleyMessage`对象，
> 
> 最后通过`LSHttp(this).request();`进行请求。
















