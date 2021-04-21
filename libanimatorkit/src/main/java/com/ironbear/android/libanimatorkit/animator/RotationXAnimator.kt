package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * X轴旋转动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:50
 *
 * @constructor
 * @param target
 * @param values
 */
class RotationXAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    ROTATION_X_TYPE, ObjectAnimator.ofFloat(target, "rotationX", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}