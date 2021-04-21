package com.ironbear.android.libanimatorkit.builder

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.View
import com.ironbear.android.libanimatorkit.animator.BaseAnimator

/**
 * TogetherBuilder
 *
 * @Author: Xym
 * @Date: 2020-02-12 10:24
 *
 * @constructor
 * @param target
 * @param animators
 * @param buildFromSingle
 */
class TogetherBuilder(target: View?, animators: List<BaseAnimator>, buildFromSingle: Boolean) : BaseAnimatorSetBuilder(target, animators, buildFromSingle) {
    override fun build(): Animator? {
        return buildPlayTogetherAnimator()
    }

    private fun buildPlayTogetherAnimator(): Animator? {
        if (animators.isEmpty()) return null

        prepareForBuild(animators, buildFromSingle)
        val animatorSet = AnimatorSet()
        animatorSet.let { anim ->
            val realAnimators = arrayListOf<Animator>()
            animators.forEach {
                realAnimators.add(it.getTargetAnimator())
            }
            anim.playTogether(realAnimators)
            buildBaseAttr(anim)
            return anim
        }
    }
}