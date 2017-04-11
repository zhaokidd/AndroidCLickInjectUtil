# AndroidCLickInjectUtil
This is a inject util using annotaion which helps you apply 'setOnclickLisetner' method automatically 


### 关于这个Util
主要使用了反射来获得某个holder对象中的所有子组件,也是通过反射调用这些子组件的 ___setOnCLickListener___ 方法

### 使用方法
```java
	@ClickInject(R.id.button)
	private Button button;
	@ClickInject(R.id.textview)
	private TextView textView;
```

首先使用 __@CLickInject()__ 注解声明所有子组件.

```java
		ClickUtil.inject(this, this);
```

然后在代码调用到这些子组件之前，使用 ___inject()___ 方法进行初始化.
