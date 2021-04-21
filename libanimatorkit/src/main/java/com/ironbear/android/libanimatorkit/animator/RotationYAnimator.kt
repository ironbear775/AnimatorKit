package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * Y轴旋转动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:50
 *
 * @constructor
 * @param target
 * @param values
 */
class RotationYAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    ROTATION_Y_TYPE, ObjectAnimator.ofFloat(target, "rotationY", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}