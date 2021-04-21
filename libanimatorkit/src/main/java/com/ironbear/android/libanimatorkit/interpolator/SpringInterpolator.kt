package com.ironbear.android.libanimatorkit.interpolator

import android.view.animation.Interpolator
import kotlin.math.pow
import kotlin.math.sin

/**
 * 弹性差值器
 *
 * @Author: Xym
 * @Date: 2020-02-10 10:56
 *
 * @property factor
 */
class SpringInterpolator internal constructor(private val factor: Float) : Interpolator {

    override fun getInterpolation(input: Float): Float {
        return (2.0.pow((-10 * input).toDouble()) * sin((input - factor / 4) * (2 * Math.PI) / factor) + 1).toFloat()
    }

    companion object {
        val interpolator = SpringInterpolator(0.9f)
    }
}