package com.ironbear.android.libanimatorkit.animator

import android.animation.ValueAnimator

/**
 * BaseValueAnimator
 *
 * @Author: Xym
 * @Date: 2020-02-11 16:53
 *
 * @constructor
 * @param type
 * @param animator
 */
abstract class BaseValueAnimator(type: Int, animator: ValueAnimator) : BaseAnimator(type, animator) {
    var repeatCount = 0
        set(value) {
            (animator as? ValueAnimator)?.repeatCount = value
            field = value
        }
    var repeatMode = ValueAnimator.RESTART
        set(value) {
            (animator as? ValueAnimator)?.repeatMode = value
            field = value
        }

    var doOnUpdate: (animator: ValueAnimator) -> Unit = {}
        set(value) {
            (animator as? ValueAnimator)?.addUpdateListener{
                field.invoke(it)
            }
            field = value
        }
}