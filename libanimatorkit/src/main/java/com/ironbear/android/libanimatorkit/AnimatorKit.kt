package com.ironbear.android.libanimatorkit

import android.view.View
import com.ironbear.android.libanimatorkit.builder.MultiViewBuilder
import com.ironbear.android.libanimatorkit.builder.SingleViewBuilder

/**
 * AnimatorKit动画创建工具
 * @Author: Xym
 * @Date: 2021/4/20 10:57
 */
object AnimatorKit {

    /**
     * 对一个View创建动画
     *
     * @param target
     * @return
     */
    fun withTargetView(target: View): SingleViewBuilder = AnimatorBuilder().withTargetView(target)

    /**
     * 对多个View创建动画
     *
     * @return
     */
    fun withMultiView(): MultiViewBuilder = AnimatorBuilder().withMultiView()
}