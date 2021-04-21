package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * X轴位移动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:52
 *
 * @constructor
 * @param target
 * @param values
 */
class TranslationXAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    TRANSLATION_X_TYPE, ObjectAnimator.ofFloat(target, "translationX", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}