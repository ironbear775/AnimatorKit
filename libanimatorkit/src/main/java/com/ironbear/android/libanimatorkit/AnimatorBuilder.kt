package com.ironbear.android.libanimatorkit

import android.view.View
import com.ironbear.android.libanimatorkit.builder.MultiViewBuilder
import com.ironbear.android.libanimatorkit.builder.SingleViewBuilder

/**
 * AnimatorKit动画创建工具
 *
 * @Author: Xym
 * @Date: 2020-02-11 14:52
 */
internal class AnimatorBuilder {
    private var singleViewUiKit: SingleViewBuilder? = null
    private var multiViewUiKit: MultiViewBuilder? = null

    /**
     * 对一个View创建动画
     *
     * @param target
     * @return
     */
    fun withTargetView(target: View): SingleViewBuilder {
        if (singleViewUiKit == null) {
            singleViewUiKit = SingleViewBuilder()
        }
        return singleViewUiKit!!.setTarget(target)
    }

    /**
     * 对多个View创建动画
     *
     * @return
     */
    fun withMultiView(): MultiViewBuilder {
        if (multiViewUiKit == null) {
            multiViewUiKit = MultiViewBuilder()
        }
        return multiViewUiKit!!
    }
}