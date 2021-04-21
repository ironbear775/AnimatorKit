package com.ironbear.android.libanimatorkit.animator

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

/**
 * 位移动画
 *
 * @Author: Xym
 * @Date: 2020-02-10 14:25
 *
 * @constructor
 * @param target
 * @param values
 */
class TranslationAnimator(target: View?, vararg values: Float) : BaseValueAnimator(
    TRANSLATION_TYPE, ObjectAnimator.ofPropertyValuesHolder(
        target,
        PropertyValuesHolder.ofFloat("translationX", *values),
        PropertyValuesHolder.ofFloat("translationY", *values)
    )
) {
    constructor(vararg values: Float) : this(null, *values)
}