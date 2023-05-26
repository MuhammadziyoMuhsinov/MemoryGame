package com.froxelx8.memorygame

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.froxelx8.memorygame.databinding.FragmentGameBinding
import com.froxelx8.memorygame.databinding.ItemDialogBinding
import com.froxelx8.memorygame.mydata.MyData
import java.util.*


class FragmentGame : Fragment() {

    private lateinit var binding:FragmentGameBinding
    val listImageOchiqYopiq = arrayOf(false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false
    var tugash = 0
    var toFinish = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)

        MyData.score = 0


        binding.lottie.addAnimatorListener(object : AnimatorListener{
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                binding.apply {
                    image1.setImageResource(R.drawable.yulduzcha)
                    image2.setImageResource(R.drawable.yulduzcha)
                    image3.setImageResource(R.drawable.yulduzcha)
                    image4.setImageResource(R.drawable.yulduzcha)
                    image5.setImageResource(R.drawable.yulduzcha)
                    image6.setImageResource(R.drawable.yulduzcha)


                    image1.visibility = View.VISIBLE
                    image2.visibility = View.VISIBLE
                    image3.visibility = View.VISIBLE
                    image4.visibility = View.VISIBLE
                    image5.visibility = View.VISIBLE
                    image6.visibility = View.VISIBLE




                    tugash = 0
                    listImageOchiqYopiq[0] = false
                    listImageOchiqYopiq[1] = false
                    listImageOchiqYopiq[2] = false
                    listImageOchiqYopiq[3] = false
                    listImageOchiqYopiq[4] = false
                    listImageOchiqYopiq[5] = false
                    ochiqRasm = 0
                    animationDoing = false

                    Toast.makeText(binding.root.context, "You've lost!", Toast.LENGTH_SHORT).show()
                    binding.lottie.playAnimation()
                    loadfoto()


                }
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        loadfoto()


        return binding.root
    }




    fun imageClick(imageView: ImageView, rasm: Int, index: Int) {
        if (!animationDoing) {
            if (!listImageOchiqYopiq[index]) {
                animationOchilishi(imageView, rasm, index)
            } else {
                animationYopilishi(imageView, rasm, index)
            }
        }


    }

    fun animationOchilishi(imageView: ImageView, rasm: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        listImageOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++
                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                                MyData.score+=50
                                binding.score.text = "score: ${MyData.score}"
                                binding.task.text = "task: $toFinish"

                                tugash++
                                if (tugash == 3) {

                                        binding.lottie.playAnimation()
                                        loadfoto()
                                        binding.apply {
                                            image1.setImageResource(R.drawable.yulduzcha)
                                            image2.setImageResource(R.drawable.yulduzcha)
                                            image3.setImageResource(R.drawable.yulduzcha)
                                            image4.setImageResource(R.drawable.yulduzcha)
                                            image5.setImageResource(R.drawable.yulduzcha)
                                            image6.setImageResource(R.drawable.yulduzcha)


                                            image1.visibility = View.VISIBLE
                                            image2.visibility = View.VISIBLE
                                            image3.visibility = View.VISIBLE
                                            image4.visibility = View.VISIBLE
                                            image5.visibility = View.VISIBLE
                                            image6.visibility = View.VISIBLE
                                        }

                                        tugash = 0
                                        listImageOchiqYopiq[0] = false
                                        listImageOchiqYopiq[1] = false
                                        listImageOchiqYopiq[2] = false
                                        listImageOchiqYopiq[3] = false
                                        listImageOchiqYopiq[4] = false
                                        listImageOchiqYopiq[5] = false
                                        ochiqRasm = 0
                                        animationDoing = false



                                }
                            } else {
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[0]),
                                    -1,
                                    imageIndex[0]
                                )
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[1]),
                                    -1,
                                    imageIndex[1]
                                )
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }


                })

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

    }


    fun animationYopilishi(imageView: ImageView, rasm: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.yulduzcha)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }

                })

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
        listImageOchiqYopiq[index!!] = false
        ochiqRasm--

    }

    fun imageViewAniqla(index: Int?): ImageView {
        binding.apply {

            var imageView: ImageView? = null

            when (index) {
                0 -> imageView = image1
                1 -> imageView = image2
                2 -> imageView = image3
                3 -> imageView = image4
                4 -> imageView = image5
                5 -> imageView = image6
            }

            return imageView!!

        }
    }


    fun loadfoto() {
        if (toFinish!=10){
            when (Random().nextInt(10)) {
                0 -> foto1()
                1 -> foto2()
                2 -> foto3()
                3 -> foto4()
                4 -> foto5()
                5 -> foto6()
                6 -> foto7()
                7 -> foto8()
                8 -> foto9()
                9 -> foto10()
            }
            toFinish+=1
            binding.task.text = "task: $toFinish"
        }else{
          finish()
        }


    }
    fun finish(){

            binding.lottie.pauseAnimation()
            val alertDialog = AlertDialog.Builder(binding.root.context,R.style.NewDialog).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)

            itemDialogBinding.txtCorrect.text = "score: ${MyData.score}"
            itemDialogBinding.txtOk.setOnClickListener {

                alertDialog.cancel()
            }
            alertDialog.setView(itemDialogBinding.root)
            binding.xira.visibility = View.VISIBLE
            alertDialog.show()
            alertDialog.setOnDismissListener {
                findNavController().popBackStack()
            }

    }


    fun foto1() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img1, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img2, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img3, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img1, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img2, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img3, 5)
            }

        }
    }

    fun foto2() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img4, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img5, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img4, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img5, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img6, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img6, 5)
            }

        }
    }

    fun foto3() {

        binding.apply {
            image1.setOnClickListener {
                imageClick(image1, R.drawable.img8, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img9, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img7, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img8, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img9, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img7, 5)
            }

        }
    }

    fun foto4() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img11, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img11, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img12, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img12, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img10, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img10, 5)
            }

        }
    }

    fun foto5() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img13, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img14, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img15, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img15, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img14, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img13, 5)
            }

        }
    }

    fun foto6() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img16, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img18, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img16, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img17, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img17, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img18, 5)
            }

        }
    }

    fun foto7() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img19, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img20, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img21, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img19, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img20, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img21, 5)
            }

        }

    }
    fun foto8() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img22, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img22, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img23, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img23, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img24, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img24, 5)
            }

        }

    }
    fun foto9() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img25, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img26, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img27, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img25, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img27, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img26, 5)
            }

        }

    }
    fun foto10() {
        binding.apply {

            image1.setOnClickListener {
                imageClick(image1, R.drawable.img29, 0)
            }
            image2.setOnClickListener {
                imageClick(image2, R.drawable.img30, 1)
            }
            image3.setOnClickListener {
                imageClick(image3, R.drawable.img28, 2)
            }
            image4.setOnClickListener {
                imageClick(image4, R.drawable.img30, 3)
            }
            image5.setOnClickListener {
                imageClick(image5, R.drawable.img29, 4)
            }
            image6.setOnClickListener {
                imageClick(image6, R.drawable.img28, 5)
            }

        }

    }



}