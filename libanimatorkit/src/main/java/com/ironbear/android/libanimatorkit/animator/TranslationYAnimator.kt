package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.view.View

/**
 * Y轴位移动画
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:52
 *
 * @constructor
 * @param target
 * @param values
 */
class TranslationYAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    TRANSLATION_Y_TYPE, ObjectAnimator.ofFloat(target, "translationY", *values)
) {
    constructor(vararg values: Float) : this(null, *values)
}