package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

/**
 * 缩放动画
 *
 * @Author: Xym
 * @Date: 2020-02-10 14:25
 *
 * @constructor
 * @param target
 * @param values
 */
class ScaleAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    SCALE_TYPE, ObjectAnimator.ofPropertyValuesHolder(
        target,
        PropertyValuesHolder.ofFloat("scaleX", *values),
        PropertyValuesHolder.ofFloat("scaleY", *values)
    )
) {
    constructor(vararg values: Float) : this(null, *values)
}