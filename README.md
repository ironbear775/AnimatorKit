# AnimatorKit

使用AnimatorKit，快速创建动画，通过Builder模式构造动画，轻松构建复杂动画，同时使得动画意图更加直观。相比原生Animator创建方式拥有更佳的代码可读性。

支持对单个View，多个View设置动画，支持设置顺序播放，同时播放及混合播放动画。

![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo1.gif)
![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo2.gif)
![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo3.gif)
![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo4.gif)
![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo5.gif)
![image](https://github.com/ironbear775/AnimatorKit/blob/main/pic/demo6.gif)

## 集成

[![](https://jitpack.io/v/ironbear775/AnimatorKit.svg)](https://jitpack.io/#ironbear775/AnimatorKit)

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Step 2. Add the dependency
```groovy
dependencies {
	implementation 'com.github.ironbear775:AnimatorKit:1.0.0'
}
```

## 快速入门
### 给一个View添加动画

使用 `AnimatorKit.withTargetView(target: View)`创建动画（作用在一个View上）

### 给多个View添加动画

使用 `AnimatorKit.withMultiView()`创建动画（作用在多个View上），给多个View创建动画必须在创建动画时传入要作用的View。

### 声明动画
1. 单个动画，使用`singleAnimator(animator: BaseValueAnimator)` 。
```kotlin
//对animationView1设置一个旋转动画

AnimatorKit
    .withTargetView(animationView1) //作用在animationView1上
    .singleAnimator(RotationAnimator(0f, 270f, 90f)) //旋转动画
    .interpolator(Interpolator.springInterpolator) //设置差值器
    .doOnStart { Log.d("view1Rotation", "start") } //动画监听
    .doOnCancel { Log.d("view1Rotation", "cancel") } //动画监听
    .doOnEnd { Log.d("view1Rotation", "end") } //动画监听
    .build()
```
2. 多个动画，同时播放，使用`playTogether(vararg animators: BaseAnimator)` ,参数为需要同时播放的动画。
```kotlin
//对animationView1设置多个同时播放的动画： scale + translationY + alpha

AnimatorKit
    .withTargetView(animationView1) //设置作用在单一View上
    .playTogether( //同时播放多个动画
        ScaleAnimator(1f, 2f, 0.5f),
        TranslationYAnimator(0f, 100f),
        AlphaAnimator(1f, 0.2f)
    )
    .build()
    
//多个View 设置同时播放动画 scale(View1) + alpha(View1) + rotate(View2) + translationY(View2)
AnimatorKit
    .withMultiView() //设置为多个view模式
    .playTogether(
        // 设置给View1
        ScaleAnimator(animationView1, 1f, 0.3f, 0.8f),
        // 设置给View1
        AlphaAnimator(animationView1, 0f, 1f, 0.4f),
        // 设置给View2
        RotationAnimator(animationView2, 0f, 250f, 90f),
        // 设置给View2
        TranslationYAnimator(animationView2, 0f, 100f)
    )
    .build()
```
3. 多个动画，顺序播放，使用`playSequentially(vararg animators: BaseAnimator)` ,参数为需要顺序播放的动画。
```kotlin
//给animationView1设置顺序动画，播放顺序为rotate -> translationX -> rangeFloat

AnimatorKit
    .withTargetView(animationView1)
    .playSequentially( // 顺序播放
        RotationAnimator(0f, 270f, 180f),
        TranslationXAnimator(0f, 100f),
        RangeFloatAnimator(100f, 200f) {
            Log.d("view1RangeFloat", "$it")
        }
    )
    .build()
```
4. 多个动画混合播放，使用`playSequentially(vararg animators: BaseAnimator)`搭配`TogetherAnimator`完成，其内部可容纳多个动画同时播放。
```kotlin
//给animationView1设置混合动画，播放顺序为rotate -> 同时播放(scale+alpha) -> translationX -> rangeFloat

AnimatorKit
    .withTargetView(animationView1)
    .playSequentially(
        RotationAnimator(0f, 270f, 180f),
        //顺序播放中播放到这里时，会同时播放scale和alpha两个动画
        TogetherAnimator( 
            ScaleAnimator(1f, 1.5f),
            AlphaAnimator(0f, 0.5f)
        ),
        TranslationXAnimator(0f, 100f),
        RangeFloatAnimator(100f, 200f) {
            Log.d("view1RangeFloat", "$it")
        }
    )
    .build()
```

### 动画种类及属性

除TogetherAnimator为特殊容器，本身不作为动画，其余动画都是Android中的属性动画，并且支持属性动画中所支持的属性。

##### 单一属性动画支持的属性
```kotlin
TranslationXAnimator(0f, 100f).apply {
    pivotX = 0f
    pivotY = 20f
    repeatCount = 1
    repeatMode = ValueAnimator.RESTART
    startDelay = 1000L
    interpolator = Interpolator.accelerateDecelerateInterpolator
    
    doOnStart = { Log.d("view1TranslationX", "start") }
    doOnPause = { Log.d("view1TranslationX", "pause") }
    doOnResume = { Log.d("view1TranslationX", "resume") }
    doOnRepeat = { Log.d("view1TranslationX", "repeat") }
    doOnCancel = { Log.d("view1TranslationX", "cancel") }
    doOnEnd = { Log.d("view1TranslationX", "end") }
    doOnUpdate = { Log.d("view1TranslationX", "update") }
}
```
#### `SingleAnimator`，`PlayTogether`与`PlaySequentially`的说明

`SingleAnimator`，`PlayTogether`与`PlaySequentially`为`AnimatorSet`，同样支持对集合的属性进行设置。

需要注意的是，`Animator`和`AnimatorSet`中如果同时设置了某些属性，`Animator`中部分属性会被AnimatorSet覆盖，这一点和Android原生`AnimatorSet`中逻辑完全一致。如有属性覆盖关系不清楚的请阅读`SingleViewBuilder`与`BaseAnimatorSetBuilder`相关方法中的注释部分,或阅读Android相关源码。
