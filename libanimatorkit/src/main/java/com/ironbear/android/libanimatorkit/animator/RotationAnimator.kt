package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * 旋转动画
 *
 * @Author: Xym
 * @Date: 2020-02-10 14:25
 *
 * @constructor
 * @param target
 * @param values
 */
class RotationAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    ROTATION_TYPE,  ObjectAnimator.ofFloat(target, "rotation", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}