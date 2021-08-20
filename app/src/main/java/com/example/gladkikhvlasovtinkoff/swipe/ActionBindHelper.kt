package gcom.example.gladkikhvlasovtinkoff.swipe

import com.example.gladkikhvlasovtinkoff.swipe.SwipeToActionLayout
import java.util.*

class ActionBindHelper {

    private val actions: MutableMap<String, SwipeToActionLayout> = Collections.synchronizedMap(mutableMapOf())

    fun closeOtherThan(currentId: String) {
        for ((id, actionLayout) in actions.entries) {
            if (id == currentId) {
                continue
            }

            actionLayout.close()
        }
    }

}