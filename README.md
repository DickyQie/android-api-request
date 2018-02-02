#### okhttp-utils的封装之okhttp的使用 

<p><span style="color:#000000">HTTP是现代应用的网络。这就是我们如何交换数据和媒体。让你的东西做HTTP有效负载的速度和节省带宽。</span></p> 
<p><span style="color:#000000">okhttp是HTTP客户端的有效默认：</span></p> 
<ul> 
 <li><span style="color:#000000">HTTP 2支持允许所有请求相同的主机共享一个插座。</span></li> 
 <li><span style="color:#000000">连接池减少请求的延迟（如HTTP / 2不可用）。</span></li> 
 <li><span style="color:#000000">透明的gzip收缩下载大小。</span></li> 
 <li><span style="color:#000000">响应缓存避免完全重复要求网络。</span></li> 
</ul> 
<p>&nbsp;</p> 
<p><span style="color:#000000">okhttp坚守当网络是麻烦：它会悄悄地从常见的连接问题恢复。如果你的服务有多个IP地址okhttp将备用地址，如果第一次连接失败。这是IPv4向IPv6必要冗余的数据中心托管服务。okhttp发起新的连接特点现代TLS（SNI，alpn），落回TLS 1如果握手失败。</span></p> 
<p><span style="color:#000000">使用okhttp是容易的。它的请求/响应的API的设计与流畅的建设者和不变性。它同时支持同步和异步调用阻塞调用回调函数。</span></p> 
<p><span style="color:#000000">okhttp支持Android 2.3及以上。对于java，最低要求是1.7。</span></p> 
<p><span style="color:#000000">&nbsp;</span></p> 
<p><span style="color:#000000">最近半年来身边开发的朋友越来越多的提到OkHttp，上谷歌百度一下，确实OkHttp成了时下最火的HTTP框架。现在把自己整理的官方教程分享给大家，希望给初学者带来帮助。</span></p> 
<p><span style="color:#000000">主要包含了一些常见的操作：HTTP GET, HTTP POST,POST单个和多个文件上传），图片加载等。如图：</span></p> 
<p>&nbsp;</p> 
<p>&nbsp; </p> 
<p>&nbsp;&nbsp; <img alt="" height="409" src="https://static.oschina.net/uploads/space/2017/0209/100744_MEud_2945455.gif" width="266"></p> 
<p></p> 
