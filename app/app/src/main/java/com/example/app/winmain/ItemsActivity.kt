package com.example.app.winmain

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Item
import com.example.app.ItemsAdapter
import com.example.app.R

class ItemsActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector
    private var lastTapTime: Long = 0
    private var tapCount: Int = 0
    private val TAP_THRESHOLD: Long = 300

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val rootLayout: View = findViewById(R.id.main)

        rootLayout.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTapTime < TAP_THRESHOLD) {
                tapCount++

                if (tapCount >= 3) {
                    navigateToNextActivity()
                }
            } else {
                tapCount = 1
            }

            lastTapTime = currentTime
        }

        val buttonGoToAccount: ImageButton = findViewById(R.id.account_button)
        val buttonGoToMap: ImageButton = findViewById(R.id.map_button)
        val buttonGoToPlaces: ImageButton = findViewById(R.id.places_button)
        val buttonGoToPictures: ImageButton = findViewById(R.id.picture_button)

        buttonGoToAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        buttonGoToMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        buttonGoToPlaces.setOnClickListener {
            val intent = Intent(this, PlacesActivity::class.java)
            startActivity(intent)
        }

        buttonGoToAccount.setOnLongClickListener{
            showFunnyMessage()
            true
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()

        items.add(Item(1, "vaadhoo", "Остров Ваадху", "Мальдивы", "Казалось бы, что интересного может быть в биолюминесцентном фитопланктоне? Однако посмотреть на него стоит. Уникальный вид планктона, который светится в темноте, можно увидеть только на острове Ваадху. Волны, набегая на берег, уносят его с собой, и вода начинает переливаться сотнями, тысячами сверкающих огней."))
        items.add(Item(2, "rayskie_vrata", "Райские врата", "Китай, Тяньмэнь", "Другое название — «Небесные врата». Это самая высокая пещера, она образовалась в 263 году, когда внезапно большой кусок горы просто отвалился, создав огромную полость. К её подножию ведёт 999 ступеней: по легенде, пройдя по ним, можно достигнуть просветления и приблизиться к Богу. Такое под силу не каждому, а в Китае, видимо, не любят разочаровывать туристов, поэтому сделали самый длинный в мире фуникулёр и самый высокий открытый лифт. Над пещерой почти всегда можно наблюдать облако тумана, что придаёт ей ещё больше таинственности."))
        items.add(Item(3, "retba_lake", "Озеро Ретба", "Сенегал, 20 км к северо-востоку от полуострова Зелёный мыс", "Озеро Ретба располагается в северо-восточной части Дакара и известно необычным цветом воды. Своим окрасом озеро обязано водорослям, из-за которых вода приобретает насыщенный цвет — от нежно-розового до алого. Также в воде высокое содержание соли, поэтому можно всё время оставаться на плаву, как в Мёртвом море."))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)


        /*gestureDetector = GestureDetector(this, object : SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                if (e1 != null && e2 != null) {
                    if (e1.x - e2.x > SWIPE_THRESHOLD) {
                        //свайп влеао
                        navigateToNextScreen()
                        return true
                    } else if (e2.x - e1.x > SWIPE_THRESHOLD) {
                        navigateToPreviousScreen()
                        return true
                    }
                }
                return false
            }
        })*/

        //findViewById<View>(R.id.itemsList).setOnTouchListener { v, event ->
        //    gestureDetector.onTouchEvent(event)
        //    true
        //}


    }

    private fun navigateToNextScreen() {
        //переход вправо
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
    }

    private fun navigateToPreviousScreen() {
        val intent = Intent(this, PlacesActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
    }

    private fun showFunnyMessage() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_funny_message, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.ok_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, AccountActivity::class.java)
        startActivity(intent)
        tapCount = 0
        lastTapTime = 0
    }

}