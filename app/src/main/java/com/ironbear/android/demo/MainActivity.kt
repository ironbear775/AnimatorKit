package com.ironbear.android.demo

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ironbear.android.libanimatorkit.AnimatorKit
import com.ironbear.android.libanimatorkit.animator.*
import com.ironbear.android.libanimatorkit.interpolator.Interpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var animator: Animator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkbox1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox1)
            }
        }

        checkbox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox2)
            }
        }

        checkbox3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox3)
            }
        }

        checkbox4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox4)
            }
        }

        checkbox5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox5)
            }
        }

        checkbox6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setSelect(checkbox6)
            }
        }

        startBtn.setOnClickListener {
            startAnimator()
        }

        resetBtn.setOnClickListener {
            resetAttr()
        }
    }

    private fun setSelect(view: View) {
        checkbox1.isChecked = view == checkbox1
        checkbox2.isChecked = view == checkbox2
        checkbox3.isChecked = view == checkbox3
        checkbox4.isChecked = view == checkbox4
        checkbox5.isChecked = view == checkbox5
        checkbox6.isChecked = view == checkbox6
    }

    private fun startAnimator() {
        animator = when {
            checkbox1.isChecked -> startView1RotateAnimator()
            checkbox2.isChecked -> startView1TogetherAnimator()
            checkbox3.isChecked -> startView1SequenceAnimator()
            checkbox4.isChecked -> startMultiViewTogetherAnimator()
            checkbox5.isChecked -> startMultiViewSequenceAnimator()
            checkbox6.isChecked -> startMixAnimator()
            else -> null
        }

        animator?.start()
    }

    private fun resetAttr() {
        animator?.cancel()
        animationView1.translationY = 0f
        animationView1.translationX = 0f
        animationView1.alpha = 1f
        animationView1.rotation = 0f
        animationView1.rotationX = 0f
        animationView1.rotationY = 0f
        animationView1.scaleX = 1f
        animationView1.scaleY = 1f
        animationView1.text = "view 1"

        animationView2.translationY = 0f
        animationView2.translationX = 0f
        animationView2.alpha = 1f
        animationView2.rotation = 0f
        animationView2.rotationX = 0f
        animationView2.rotationY = 0f
        animationView2.scaleX = 1f
        animationView2.scaleY = 1f
        animationView2.text = "view 2"
    }

    //View 1 Rotate
    private fun startView1RotateAnimator(): Animator? =
        AnimatorKit
            .withTargetView(animationView1) //作用在animationView1上
            .singleAnimator(RotationAnimator(0f, 270f, 90f)) //单个旋转动画
            .interpolator(Interpolator.linearInterpolator) //设置差值器
            .doOnStart { Log.d("view1Rotation", "start") } //动画监听
            .doOnCancel { Log.d("view1Rotation", "cancel") } //动画监听
            .doOnEnd { Log.d("view1Rotation", "end") } //动画监听
            .build()

    //View 1 同时 scale + translationY + alpha
    private fun startView1TogetherAnimator(): Animator? =
        AnimatorKit
            .withTargetView(animationView1) //设置作用在单一View上
            .playTogether( //同时播放多个动画
                ScaleAnimator(1f, 2f, 0.5f).apply {
                    doOnStart = { Log.d("view1Scale", "start") }
                    doOnEnd = { Log.d("view1Scale", "end") }
                },
                TranslationYAnimator(0f, 100f).apply {
                    doOnStart = { Log.d("view1TranslationY", "start") }
                    doOnEnd = { Log.d("view1TranslationY", "end") }
                },
                AlphaAnimator(1f, 0.5f).apply {
                    doOnStart = { Log.d("view1Alpha", "start") }
                    doOnEnd = { Log.d("view1Alpha", "end") }
                }
            )
            .duration(1000)
            .interpolator(Interpolator.linearInterpolator)
            .doOnStart { Log.d("checkBox2animator", "start") }
            .doOnCancel { Log.d("checkBox2animator", "cancel") }
            .doOnEnd { Log.d("checkBox2animator", "end") }
            .build()

    //View 1 顺序 rotate  -> translationX  -> rangeFloat
    private fun startView1SequenceAnimator(): Animator? =
        AnimatorKit
            .withTargetView(animationView1)
            .playSequentially(
                RotationAnimator(0f, 180f, 90f).apply {
                    doOnStart = {
                        Log.d("view1Rotation", "start")
                    }
                    doOnEnd = {
                        Log.d("view1Rotation", "end")
                    }
                    duration = 1000
                },
                TranslationXAnimator(0f, 100f).apply {
                    doOnStart = {
                        Log.d("view1TranslationX", "start")
                    }
                    doOnEnd = {
                        Log.d("view1TranslationX", "end")
                    }
                    duration = 500
                },
                RangeFloatAnimator(100f, 200f) {
                    Log.d("view1RangeFloat", "$it")
                    animationView1.text = "$it"
                }.apply {
                    doOnStart = {
                        Log.d("view1RangeFloat", "start")
                    }
                    doOnEnd = {
                        Log.d("view1RangeFloat", "end")
                    }
                    duration = 300
                }
            )
            .interpolator(Interpolator.accelerateInterpolator)
            .doOnStart {
                Log.d("checkBox3animator", "start")
            }
            .doOnCancel {
                Log.d("checkBox3animator", "cancel")
            }
            .doOnEnd {
                Log.d("checkBox3animator", "end")
            }
            .build()

    //多个View 同时 scale(View1) + alpha(View1) +  rotate(View2) + translationY(View2)
    private fun startMultiViewTogetherAnimator(): Animator? =
        AnimatorKit
            .withMultiView() //设置为多个view模式
            .playTogether(
                // 设置给View1
                ScaleAnimator(animationView1, 1f, 0.3f, 0.8f).apply {
                    doOnStart = { Log.d("view1Scale", "start") }
                    doOnEnd = { Log.d("view1Scale", "end") }
                },
                // 设置给View1
                AlphaAnimator(animationView1, 0f, 1f, 0.4f).apply {
                    doOnStart = { Log.d("view1Alpha", "start") }
                    doOnEnd = { Log.d("view2Rotation", "end") }
                },
                // 设置给View2
                RotationAnimator(animationView2, 0f, 250f, 90f).apply {
                    doOnStart = { Log.d("view2Rotation", "start") }
                    doOnEnd = { Log.d("view2Rotation", "end") }
                },
                // 设置给View2
                TranslationYAnimator(animationView2, 0f, 100f).apply {
                    doOnStart = { Log.d("view2TranslationY", "start") }
                    doOnEnd = { Log.d("view2TranslationX", "end") }
                }
            )
            .duration(2000)
            .interpolator(Interpolator.accelerateDecelerateInterpolator)
            .doOnStart { Log.d("checkBox4animator", "start") }
            .doOnCancel { Log.d("checkBox4animator", "cancel") }
            .doOnEnd { Log.d("checkBox4animator", "end") }
            .build()

    //多个View 顺序 rotate(View1)  -> scale(View1)  -> translationY(View2)  -> alpha(View2)
    private fun startMultiViewSequenceAnimator(): Animator? =
        AnimatorKit
            .withMultiView()
            .playSequentially(
                RotationAnimator(animationView1, 0f, 120f, 90f).apply {
                    doOnStart = {
                        Log.d("view1Rotation", "start")
                    }
                    doOnEnd = {
                        Log.d("view1Rotation", "end")
                    }
                },
                ScaleAnimator(animationView1, 1f, 0.3f, 0.8f).apply {
                    doOnStart = {
                        Log.d("view1Scale", "start")
                    }
                    doOnEnd = {
                        Log.d("view1Scale", "end")
                    }
                },
                TranslationYAnimator(animationView2, 0f, 100f).apply {
                    doOnStart = {
                        Log.d("view2TranslationY", "start")
                    }
                    doOnEnd = {
                        Log.d("view2TranslationX", "end")
                    }
                },
                AlphaAnimator(animationView2, 1f, 0.2f).apply {
                    doOnStart = {
                        Log.d("view1Alpha", "start")
                    }
                    doOnEnd = {
                        Log.d("view1Alpha", "end")
                    }
                }
            )
            .duration(1000)
            .interpolator(Interpolator.springInterpolator)
            .doOnStart {
                Log.d("checkBox5animator", "start")
            }
            .doOnCancel {
                Log.d("checkBox5animator", "cancel")
            }
            .doOnEnd {
                Log.d("checkBox5animator", "end")
            }
            .build()

    //View 1 顺序 rotate -> 同时播放(scale+alpha) -> translationX -> rangeFloat
    private fun startMixAnimator(): Animator? =
        AnimatorKit
            .withTargetView(animationView1)
            .playSequentially(
                RotationAnimator(0f, 180f, 30f).apply {
                    doOnStart = {
                        Log.d("view1Rotation", "start")
                    }
                    doOnEnd = {
                        Log.d("view1Rotation", "end")
                    }
                },
                //顺序播放中播放到这里时，会同时播放scale和alpha两个动画
                TogetherAnimator(
                    ScaleAnimator(1f, 1.5f),
                    AlphaAnimator(1f, 0.5f)
                ),
                TranslationXAnimator(0f, 100f).apply {
                    pivotX = 0f
                    pivotY = 20f
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                    startDelay = 1000L
                    interpolator = Interpolator.accelerateDecelerateInterpolator

                    doOnStart = { Log.d("view1TranslationX", "start") }
                    doOnPause = { Log.d("view1TranslationX", "pause") }
                    doOnResume = { Log.d("view1TranslationX", "resume") }
                    doOnRepeat = { Log.d("view1TranslationX", "repeat") }
                    doOnCancel = { Log.d("view1TranslationX", "cancel") }
                    doOnEnd = { Log.d("view1TranslationX", "end") }
                    doOnUpdate = { Log.d("view1TranslationX", "update") }
                },
                RangeFloatAnimator(100f, 200f) {
                    Log.d("view1RangeFloat", "$it")
                    animationView1.text = "$it"
                }.apply {
                    doOnStart = {
                        Log.d("view1RangeFloat", "start")
                    }
                    doOnEnd = {
                        Log.d("view1RangeFloat", "end")
                    }
                }
            )
            .duration(1000)
            .interpolator(Interpolator.linearInterpolator)
            .doOnStart {
                Log.d("checkBox3animator", "start")
            }
            .doOnCancel {
                Log.d("checkBox3animator", "cancel")
            }
            .doOnEnd {
                Log.d("checkBox3animator", "end")
            }
            .build()
}
