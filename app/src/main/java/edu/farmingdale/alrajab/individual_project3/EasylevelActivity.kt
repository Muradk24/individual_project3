package edu.farmingdale.alrajab.individual_project3

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import edu.farmingdale.alrajab.individual_project3.databinding.ActivityEasylevelBinding

class EasylevelActivity : AppCompatActivity() {
    lateinit var  binding:ActivityEasylevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasylevelBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.holder01.setOnDragListener(arrowDragListener)
        binding.holder02.setOnDragListener(arrowDragListener)
        binding.holder03.setOnDragListener(arrowDragListener)
        binding.holder04.setOnDragListener(arrowDragListener)
        binding.holder05.setOnDragListener(arrowDragListener)

        binding.imageViewsnowman.setBackgroundResource(R.drawable.snowman_walking)
// this needs a click to load the function


        binding.imageViewsnowman.setOnClickListener {
            val rocketAnimation = binding.imageViewsnowman.background as AnimationDrawable
// after 1 click on loading the fucntion you need to click it again to play the anamation

            if (rocketAnimation.isRunning) {
               // binding.imageViewsnowman.text="Start"
                rocketAnimation.stop()
            } else {
                //binding.imageViewsnowman.text="Stop"
                rocketAnimation.start()

            }
        }



    }


    private val arrowDragListener = View.OnDragListener { view, dragEvent ->
        (view as? ImageView)?.let {
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {

                    view.setImageResource( R.drawable.highlightrectangle)
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_EXITED-> {
                    view.setImageResource( R.drawable.myrectangle)
                    return@OnDragListener true
                }
                // No need to handle this for our use case.
                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@OnDragListener true
                }

                DragEvent.ACTION_DROP -> {
                    // Read color data from the clip data and apply it to the card view background.
                    val item: ClipData.Item = dragEvent.clipData.getItemAt(0)
                    val lbl = item.text.toString()
                    // debug                  Log.d("BCCCCCCCCCCC", "NOTHING > >  " + lbl)
                    //                 when(lbl.toString()){
                    when(lbl){
                        //                   "UP"->{      // you can check the value of the button vs if its correct with the solution here
                        //                     view.setImageResource( R.drawable.ic_baseline_arrow_upward_24)
                        //               }
                        "UP"-> view.setImageResource( R.drawable.ic_baseline_arrow_upward_24)
                        "DOWN"-> view.setImageResource( R.drawable.ic_baseline_arrow_downward_24)
                        "FORWARD"-> view.setImageResource( R.drawable.ic_baseline_arrow_forward_24)
                        "BACK"-> view.setImageResource( R.drawable.ic_baseline_arrow_back_24)
                    }
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENDED -> {

                    return@OnDragListener true
                }
                else -> return@OnDragListener false
            }
        }
        false
    }


    private class ArrowDragShadowBuilder(view: View) : View.DragShadowBuilder(view) {
        private val shadow = view.background
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = view.width
            val height: Int = view.height
            // how big tdo you want your shadow
            shadow?.setBounds(0, 0, width, height)
            size.set(width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            shadow?.draw(canvas)
        }
    }
}




