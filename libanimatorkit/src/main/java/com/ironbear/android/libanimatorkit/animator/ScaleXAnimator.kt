package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * X轴缩放动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:50
 *
 * @constructor
 * @param target
 * @param values
 */
class ScaleXAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    SCALE_X_TYPE, ObjectAnimator.ofFloat(target, "scaleX", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}