package com.example.note

import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import java.util.*


class ColorChooserDialog(context: Context?) :
    Dialog(context!!) {
    private var one: ImageButton? = null
    private var two: ImageButton? = null
    private var three: ImageButton? = null
    private var four: ImageButton? = null
    private var five: ImageButton? = null
    private val six: ImageButton? = null
    private var seven: ImageButton? = null
    //    private ImageButton eight;
    private val nine: ImageButton? = null
    private var ten: ImageButton? = null
    //    private ImageButton eleven;
//    private ImageButton twelve;
//    private ImageButton thirteen;
//    private ImageButton fourteen;
    private var fifteen: ImageButton? = null
    private val sixteen: ImageButton? = null
    private var seventeen: ImageButton? = null
    private var eighteen: ImageButton? = null
    //    private ImageButton nineteen;
//    private ImageButton twenty;
//    private Button twentyOne;
    private var colors: MutableList<Int>? = null
    private var buttons: MutableList<ImageButton?>? = null
    private var myColorListener: ColorListener? = null
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.color_picker_dialog)
        one = findViewById<View>(R.id.b1) as ImageButton
        two = findViewById<View>(R.id.b2) as ImageButton
        three = findViewById<View>(R.id.b3) as ImageButton
        four = findViewById<View>(R.id.b4) as ImageButton
        five = findViewById<View>(R.id.b5) as ImageButton
        //        six =      (ImageButton)findViewById(R.id.b6);
        seven = findViewById<View>(R.id.b7) as ImageButton
        //        eight =    (ImageButton)findViewById(R.id.b8);
//        nine =     (ImageButton)findViewById(R.id.b9);
        ten = findViewById<View>(R.id.b10) as ImageButton
        //        eleven =   (ImageButton)findViewById(R.id.b11);
//        twelve =   (ImageButton)findViewById(R.id.b12);
//        thirteen = (ImageButton)findViewById(R.id.b13);
//        fourteen = (ImageButton)findViewById(R.id.b14);
        fifteen = findViewById<View>(R.id.b15) as ImageButton
        //        sixteen =  (ImageButton)findViewById(R.id.b16);
        seventeen = findViewById<View>(R.id.b17) as ImageButton
        eighteen = findViewById<View>(R.id.b18) as ImageButton
        //        nineteen = (ImageButton)findViewById(R.id.b19);
//        twenty =   (ImageButton)findViewById(R.id.b20);
//        twentyOne =(Button)findViewById(R.id.b21);
        colors = ArrayList()
        (colors as ArrayList<Int>).add(red)
        (colors as ArrayList<Int>).add(pink)
        (colors as ArrayList<Int>).add(Purple)
        (colors as ArrayList<Int>).add(DeepPurple)
        (colors as ArrayList<Int>).add(Indigo)
        //        colors.add(Blue);
        (colors as ArrayList<Int>).add(LightBlue)
        //        colors.add(Cyan);
//        colors.add(Teal);
        (colors as ArrayList<Int>).add(Green)
        //        colors.add(LightGreen);
//        colors.add(Lime);
//        colors.add(Yellow);
//        colors.add(Amber);
        (colors as ArrayList<Int>).add(Orange)
        //        colors.add(DeepOrange);
        (colors as ArrayList<Int>).add(Brown)
        (colors as ArrayList<Int>).add(Grey)
        //        colors.add(BlueGray);
//        colors.add(Black);
//        colors.add(White);
        buttons = ArrayList()
        (buttons as ArrayList<ImageButton?>).add(one)
        (buttons as ArrayList<ImageButton?>).add(two)
        (buttons as ArrayList<ImageButton?>).add(three)
        (buttons as ArrayList<ImageButton?>).add(four)
        (buttons as ArrayList<ImageButton?>).add(five)
        //        buttons.add(six);
        (buttons as ArrayList<ImageButton?>).add(seven)
        //        buttons.add(eight);
//        buttons.add(nine);
        (buttons as ArrayList<ImageButton?>).add(ten)
        //        buttons.add(eleven);
//        buttons.add(twelve);
//        buttons.add(thirteen);
//        buttons.add(fourteen);
        (buttons as ArrayList<ImageButton?>).add(fifteen)
        //        buttons.add(sixteen);
        (buttons as ArrayList<ImageButton?>).add(seventeen)
        (buttons as ArrayList<ImageButton?>).add(eighteen)
        //        buttons.add(nineteen);
//        buttons.add(twenty);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Colorize()
        } else {
            ColorizeOld()
        }
        //        twentyOne.setVisibility(View.INVISIBLE);
        setListeners()
    }

    private val listener =
        View.OnClickListener { v ->
            myColorListener?.OnColorClick(v, v.tag as Int)
            dismiss()
        }

    private fun setListeners() {
        for (i in buttons!!.indices) {
            buttons!![i]!!.tag = colors!![i]
            buttons!![i]!!.setOnClickListener(listener)
        }
        //        twentyOne.setTag(colors.get(20));
//        twentyOne.setOnClickListener(listener);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun Colorize() {
        for (i in buttons!!.indices) {
            val d = ShapeDrawable(OvalShape())
            d.setBounds(58, 58, 58, 58)
            Log.e("Shape drown no", i.toString() + "")
            buttons!![i]!!.visibility = View.INVISIBLE
            d.paint.style = Paint.Style.FILL
            d.paint.color = colors!![i]
            buttons!![i]!!.background = d
        }
        animate()
    }

    private fun ColorizeOld() {
        for (i in buttons!!.indices) {
            val d = ShapeDrawable(OvalShape())
            d.paint.color = colors!![i]
            d.paint.strokeWidth = 1f
            d.setBounds(58, 58, 58, 58)
            buttons!![i]!!.visibility = View.INVISIBLE
            d.paint.style = Paint.Style.FILL
            d.paint.color = colors!![i]
            buttons!![i]!!.setBackgroundDrawable(d)
        }
        animate()
    }

    private fun animate() {
        Log.e("animate", "true")
        val r1 = Runnable {
            Log.e("animator 1", "r")
            animator(one)
        }
        val r2 = Runnable {
            animator(two)
            animator(seven)
        }
        val r3 = Runnable {
            animator(three)
            animator(ten)
            //                animator(eleven);
        }
        val r4 = Runnable {
            animator(four)
            animator(fifteen)
            animator(eighteen)
            //                animator(sixteen);
        }
        val r5 = Runnable {
            animator(five)
            animator(seventeen)
            //                animator(thirteen);
            //                animator(seventeen);
        }
        //        Runnable r6 = new Runnable() {
//            @Override
//            public void run() {
//                animator(ten);
////                animator(fourteen);
////                animator(eighteen);
//            }
//        };
//
//        Runnable r7 = new Runnable() {
//            @Override
//            public void run() {
////                animator(fifteen);
//                animator(nineteen);
//            }
//        };
//
//        Runnable r8 = new Runnable() {
//            @Override
//            public void run() {
//                animator(twenty);
//            }
//        };
        val r9 = Runnable {
            val animation = AnimationUtils.loadAnimation(
                context,
                android.R.anim.fade_in
            )
            animation.interpolator = AccelerateInterpolator()
            //                twentyOne.setAnimation(animation);
            //                twentyOne.setVisibility(View.VISIBLE);
            animation.start()
        }
        val handler = Handler()
        val counter = 85
        handler.postDelayed(r1, counter.toLong())
        handler.postDelayed(r2, counter * 2.toLong())
        handler.postDelayed(r3, counter * 3.toLong())
        handler.postDelayed(r4, counter * 4.toLong())
        handler.postDelayed(r5, counter * 5.toLong())
        //        handler.postDelayed(r6,counter * 6);
//        handler.postDelayed(r7,counter * 7);
//        handler.postDelayed(r8,counter * 8);
//        handler.postDelayed(r9,counter * 6);
    }

    private fun animator(imageButton: ImageButton?) {
        val animation =
            AnimationUtils.loadAnimation(context, R.anim.color_item)
        animation.interpolator = AccelerateInterpolator()
        imageButton!!.animation = animation
        imageButton.visibility = View.VISIBLE
        animation.start()
    }

    //CONSTANTS
    val red = -0xbbcca
    val pink = -0x16e19d
    val Purple = -0x63d850
    val DeepPurple = -0x98c549
    val Indigo = -0xc0ae4b
    val Blue = -0xde690d
    val LightBlue = -0xfc560c
    val Cyan = -0xff432c
    val Teal = -0xff6978
    val Green = -0xb350b0
    val LightGreen = -0x743cb6
    val Lime = -0x3223c7
    val Yellow = -0x14c5
    val Amber = -0x3ef9
    val Orange = -0x6800
    val DeepOrange = -0xa8de
    val Brown = -0x86aab8
    val Grey = -0x616162
    val BlueGray = -0x9f8275
    //    public final int Black =      0xff000000;
    val White = -0x1

    fun setColorListener(listener: ColorListener?) {
        myColorListener = listener
    }
}
