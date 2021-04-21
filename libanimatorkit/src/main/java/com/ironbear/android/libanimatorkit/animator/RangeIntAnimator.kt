package com.ironbear.android.libanimatorkit.animator

import android.animation.ValueAnimator

/**
 * Int范围自定义动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:50
 *
 * @constructor
 * @param values
 * @param doOnUpdate
 */
class RangeIntAnimator(vararg values: Int, doOnUpdate: ((value: Int) -> Unit)) : BaseValueAnimator(
    RANGE_INT_TYPE, ValueAnimator.ofInt(*values)
) {
    init {
        this.doOnUpdate = {
            doOnUpdate.invoke((it.animatedValue as? Int) ?: 0)
        }
    }
}