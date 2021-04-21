package com.ironbear.android.libanimatorkit.animator

import android.animation.ValueAnimator

/**
 * Float范围自定义动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:50
 *
 * @constructor
 * @param values
 * @param doOnUpdate
 */
class RangeFloatAnimator(vararg values: Float, doOnUpdate: (value: Float) -> Unit) : BaseValueAnimator(
    RANGE_FLOAT_TYPE, ValueAnimator.ofFloat(*values)
) {
    init {
        this.doOnUpdate = {
            doOnUpdate.invoke((it.animatedValue as? Float) ?: 0f)
        }
    }
}