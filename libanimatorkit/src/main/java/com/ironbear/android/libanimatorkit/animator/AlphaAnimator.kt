package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * 透明度动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:52
 *
 * @constructor
 * @param target
 * @param values
 */
class AlphaAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    ALPHA_TYPE, ObjectAnimator.ofFloat(target, "alpha", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}