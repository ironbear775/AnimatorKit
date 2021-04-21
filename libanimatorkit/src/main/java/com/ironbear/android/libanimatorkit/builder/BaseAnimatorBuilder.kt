package com.ironbear.android.libanimatorkit.builder

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import com.ironbear.android.libanimatorkit.animator.*

/**
 * BaseAnimatorBuilder
 *
 * @Author: Xym
 * @Date: 2020-02-11 15:04
 */
abstract class BaseAnimatorBuilder {
    protected var duration: Long? = null
    protected var interpolator: Interpolator? = null
    protected var startDelay: Long? = null

    protected var doOnStart: ((animator: Animator) -> Unit)? = null
    protected var doOnPause: ((animator: Animator) -> Unit)? = null
    protected var doOnResume: ((animator: Animator) -> Unit)? = null
    protected var doOnRepeat: ((animator: Animator) -> Unit)? = null
    protected var doOnCancel: ((animator: Animator) -> Unit)? = null
    protected var doOnEnd: ((animator: Animator) -> Unit)? = null
    protected var onUpdate: ((animator: ValueAnimator) -> Unit)? = null

    protected var view: View? = null

    protected var buildFromSingle = false

    /**
     * 同时播放多个动画
     *
     * @param animators
     * @return
     */
    fun playTogether(vararg animators: BaseAnimator): TogetherBuilder {
        return TogetherBuilder(view, animators.toList(),buildFromSingle)
    }

    /**
     * 顺序播放多个动画
     *
     * @param animators
     * @return
     */
    fun playSequentially(vararg animators: BaseAnimator): SequenceBuilder {
        return SequenceBuilder(view, animators.toList(),buildFromSingle)
    }

    abstract fun build(): Animator?
}