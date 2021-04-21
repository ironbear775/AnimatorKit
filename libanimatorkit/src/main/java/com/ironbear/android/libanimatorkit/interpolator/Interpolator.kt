package com.ironbear.android.libanimatorkit.interpolator

import android.view.animation.*

/**
 * Interpolator
 *
 * @Author: Xym
 * @Date: 2020-02-10 10:53
 */
object Interpolator {
    val linearInterpolator = LinearInterpolator()

    val accelerateInterpolator = AccelerateInterpolator()

    val accelerateDecelerateInterpolator = AccelerateDecelerateInterpolator()

    val bounceInterpolator= BounceInterpolator()

    val anticipateInterpolator = AnticipateInterpolator()

    val anticipateOvershootInterpolator = AnticipateOvershootInterpolator()

    val cycleInterpolator = CycleInterpolator(1.0f)

    val decelerateInterpolator = DecelerateInterpolator()

    val overshootInterpolator = OvershootInterpolator()

    val springInterpolator = SpringInterpolator.interpolator
}