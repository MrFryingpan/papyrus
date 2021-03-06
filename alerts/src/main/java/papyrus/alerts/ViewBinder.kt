package papyrus.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class ViewBinder {
    var cancelableObserver: ((Boolean) -> Unit)? = null
    lateinit var itemView: ViewGroup

    open val layoutID: Int = R.layout.dialog_default

    var cancelable = true
        set(value) {
            field = value
            cancelableObserver?.invoke(value)
        }

    fun initializeView(parent: ViewGroup, cancelableObserver: (Boolean) -> Unit): ViewGroup {
        this.cancelableObserver = cancelableObserver
        itemView = (LayoutInflater.from(parent.context).inflate(layoutID, parent, false) as? ViewGroup)
                ?: throw IllegalStateException("layout ${parent.context.resources.getResourceName(layoutID)} must have ViewGroup root")
        parent.addView(itemView)
        return itemView
    }

    open fun bind(config: Bundle?) = Unit
    open fun bind(config: Bundle?, send: (Int) -> Unit) = bind(config)

    open fun resolveExtras(result: Bundle): Bundle = result
}

