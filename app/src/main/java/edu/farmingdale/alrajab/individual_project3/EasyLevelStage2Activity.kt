package edu.farmingdale.alrajab.individual_project3

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import edu.farmingdale.alrajab.individual_project3.databinding.ActivityEasyLevelStage2Binding
import edu.farmingdale.alrajab.individual_project3.databinding.ActivityEasylevelBinding
import java.io.IOException

class EasyLevelStage2Activity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    lateinit var binding: ActivityEasyLevelStage2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasyLevelStage2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setMediaPlayer()

// for placing the arrow keys
        binding.holder01.setOnDragListener(arrowDragListener)
        binding.holder02.setOnDragListener(arrowDragListener)
        binding.holder03.setOnDragListener(arrowDragListener)
        binding.holder04.setOnDragListener(arrowDragListener)
        binding.holder05.setOnDragListener(arrowDragListener)
        //for arrow buttons
        binding.upMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.downMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.forwardMoveBtn.setOnLongClickListener(onLongClickListener)
        binding.backMoveBtn.setOnLongClickListener(onLongClickListener)
        //set the animation drawable
        binding.imageViewsnowman.setBackgroundResource(R.drawable.snowman_walking)
// this needs a click to load the function
        val rocketAnimation = binding.imageViewsnowman.background as AnimationDrawable
// after 1 click on loading the fucntion you need to click it again to play the anamation

        if (rocketAnimation.isRunning) {
            // binding.imageViewsnowman.text="Start"
            rocketAnimation.stop()
        } else {
            //binding.imageViewsnowman.text="Stop"
            rocketAnimation.start()

        }
        binding.btnPlay.setOnClickListener {

            if (binding.holder01.tag == 3)
                animSnowman()
            else {
                //failure
                stop()
                fail()
            }

        }
    }

    private fun setMediaPlayer() {
        mediaPlayer = MediaPlayer()
        try {
            val asset: AssetFileDescriptor = assets.openFd("music/spooky.mp3")

            mediaPlayer?.setDataSource(
                asset.getFileDescriptor(), asset.getStartOffset(), asset.getLength()
            )
            mediaPlayer?.prepare()
            mediaPlayer?.isLooping = true
            play()
        } catch (e: Exception) {
            Log.e("ee", "f " + e.message)
        }
    }
    fun play() {
        if (!mediaPlayer!!.isPlaying) {
            try {
                mediaPlayer!!.start()

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun stop() {
        mediaPlayer!!.stop()
    }
    override fun onDestroy() {
        super.onDestroy()
        stop()
    }
    private fun fail() {
        val builder = AlertDialog.Builder(this).create()

        val view = layoutInflater.inflate(R.layout.dialog_result, null)
        builder.setView(view)
        val retrying = view.findViewById<Button>(R.id.retryButton)

        retrying?.setOnClickListener {
            play()
            binding.apply {
                holder01  .setImageResource(R.drawable.myrectangle)
                holder02  .setImageResource(R.drawable.myrectangle)
                holder03  .setImageResource(R.drawable.myrectangle)
                holder04  .setImageResource(R.drawable.myrectangle)
            }
            builder.dismiss()

        }
        builder.show()
    }

    private fun success() {
        val builder = AlertDialog.Builder(this).create()

        val view = layoutInflater.inflate(R.layout.dialog_result, null)
        builder.setView(view)
        view.findViewById<TextView>(R.id.tvYoufailed).text="You Won"
        view.findViewById<TextView>(R.id.tvGamehelp).visibility=View.GONE
        val retrying = view.findViewById<Button>(R.id.retryButton)
        retrying.text="Next"

        retrying?.setOnClickListener {
            builder.dismiss()
            startActivity(Intent(this,EasyLevelStage3Activity::class.java))
            finish()
        }

        builder.show()
    }

    private fun animSnowman() {
        ObjectAnimator.ofFloat(binding.imageViewsnowman, "translationX", 1100f).apply {
            duration = 2000
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (binding.holder02.tag == 2) {
                        ObjectAnimator.ofFloat(binding.imageViewsnowman, "translationY", 1000f)
                            .apply {
                                duration = 2000
                                start()
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                  //success
                                        stop()
                                        success()
                                    }
                                })
                            }
                    }else
                    {
                        stop()
                        fail()
                        //failure
                    }
                }
            })
        }
    }

    private val onLongClickListener = View.OnLongClickListener { view: View ->
        (view as? Button)?.let {

            val item = ClipData.Item(it.tag as? CharSequence)

            val dragData = ClipData(
                it.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item
            )
            val myShadow = ArrowDragShadowBuilder(it)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(dragData, myShadow, null, 0)
            } else {
                it.startDrag(dragData, myShadow, null, 0)
            }

            true
        }
        false
    }

    private val arrowDragListener = View.OnDragListener { view, dragEvent ->
        (view as? ImageView)?.let {
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {

                    view.setImageResource(R.drawable.highlightrectangle)
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.setImageResource(R.drawable.myrectangle)
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
                    when (lbl) {
                        //                   "UP"->{      // you can check the value of the button vs if its correct with the solution here
                        //                     view.setImageResource( R.drawable.ic_baseline_arrow_upward_24)
                        //               }
                        "UP" -> {
                            view.setTag(1)
                            view.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
                        }
                        "DOWN" -> {
                            view.setTag(2)
                            view.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
                        }
                        "FORWARD" -> {
                            view.setTag(3)
                            view.setImageResource(R.drawable.ic_baseline_arrow_forward_24)
                        }
                        "BACK" -> {
                            view.setTag(4)
                            view.setImageResource(R.drawable.ic_baseline_arrow_back_24)
                        }
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