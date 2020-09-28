# 纯kotlin+ViewModel+LiveData+协程MVVM

MVVM大家都了解差不多了，但是我发现MVVM整成架构时，每个人的写法真的是千差万别。<br>

除了MVVM必要的ViewModel、LiveData,其它写法也没有统一的标准，看下来写法特别多，jetpack、kotlin、java、databinding、rxjava、协程、操作符等等各种混用,甚至还有把mvp的presenter当vm使用的，有的项目混用的多了看起来真的很不直观，没法用到实际项目中。

## 纯kotlin+ViewModel+LiveData+协程
那就自己写一个简单的纯净版mvvm，不用jetpack、databinding、rxjava、晦涩的操作符等。同时也练习一下kotlin各种高阶函数、扩展、新特性等。

## 屏幕截图
<img src="http://ww1.sinaimg.cn/large/9dc979c7gy1gj6dryd7q2j20me19uaek.jpg" width="45%"/>  <img src="http://ww1.sinaimg.cn/large/9dc979c7gy1gj6dubfhlmj20lu19cwlp.jpg" width="45%"/>
<img src="http://ww1.sinaimg.cn/large/9dc979c7gy1gj6f6g0n2eg20b40hskjo.gif"/>

## 主要功能
* 封装统一Base基类，加载页、错误页
* ViewModel和LiveData扩展
* 常用各种扩展
* 网络请求+协程回调,封装统一的onAppLoading 、onAppSuccess、onAppError、onAppComplete回调
* 网络框架使用的Rxhttp [https://github.com/liujingxing/okhttp-RxHttp](https://github.com/liujingxing/okhttp-RxHttp)
* 图片框架使用的coli[https://github.com/coil-kt/coil](https://github.com/coil-kt/coil)
* 发现界面拷贝eyepetizer项目
* 新建项目可以以base module作为依赖

## 参考项目
[https://github.com/Reginer/MVVMHub](https://github.com/Reginer/MVVMHub)
[https://www.pgyer.com/eyepetizer](https://www.pgyer.com/eyepetizer)
这两个项目强烈建议多学习

<img src="http://img.doutula.com/production/uploads/image/2017/10/19/20171019385983_HiYLgQ.jpg" width="80%"/>

## License

**所有数据来源于开眼，仅供学习和交流使用，严禁用于任何商业用途，原作公司拥有所有权利。**

```
Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
