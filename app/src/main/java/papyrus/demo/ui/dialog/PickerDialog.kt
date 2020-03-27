package papyrus.demo.ui.dialog

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import papyrus.adapter.DataItem
import papyrus.adapter.DataSource
import papyrus.adapter.DataSourceAdapter
import papyrus.adapter.PapyrusViewHolder
import papyrus.alerts.ViewBinder
import papyrus.demo.R
import papyrus.demo.ui.adapter.holder.LabelViewHolder
import papyrus.demo.ui.adapter.item.LabelItem

class PickerDialog : ViewBinder() {
    val title: TextView by lazy { itemView.findViewById<TextView>(R.id.title) }
    val recycler: RecyclerView by lazy { itemView.findViewById<RecyclerView>(R.id.recycler) }

    override val layoutID: Int
        get() = R.layout.dialog_picker

    override fun bind(config: Bundle?, send: (Int) -> Unit) {
        config?.getString("title")?.let { title.text = it }
        config?.getStringArray("options")?.let { options ->
            recycler.layoutManager = LinearLayoutManager(itemView.context)
            recycler.adapter = DataSourceAdapter(object : DataSource<DataItem<String>>() {
                override fun createViewHolder(parent: ViewGroup, viewType: Int): PapyrusViewHolder<out DataItem<*>> {
                    return LabelViewHolder(parent)
                }

                override fun createDefaultDataItem(index: Int, data: Any): DataItem<*> {
                    return LabelItem(index, data as String) {
                        send(index)
                    }
                }

                override fun makeNextRequest(onNewPage: (ArrayList<out Any>) -> Unit) {
                    onNewPage(arrayListOf<String>(*options))
                    dataEnded = true
                }

            }.apply {
                loadNext()
            })
        }
    }

}