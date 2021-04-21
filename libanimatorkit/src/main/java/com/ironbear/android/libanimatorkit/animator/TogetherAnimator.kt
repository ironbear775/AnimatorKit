package com.ironbear.android.libanimatorkit.animator

import android.animation.Animator
import android.animation.AnimatorSet

/**
 * 同时播放动画，内部可容纳多个动画同时播放
 *
 * @Author: Xym
 * @Date: 2020-02-11 16:51
 *
 * @constructor
 * @param animators
 */
class TogetherAnimator(vararg animators: BaseAnimator) : BaseAnimator(
    TOGETHER_TYPE, AnimatorSet().apply {
        val realAnimators = arrayListOf<Animator>()
        animators.forEach {
            realAnimators.add(it.getTargetAnimator())
        }
        this.playTogether(realAnimators)
    }) {

    val saveAnimators = animators
}