package com.gedar0082.graphviewtesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.gedar0082.graphviewtesting.databinding.NodeBinding
import de.blox.graphview.*
import org.w3c.dom.Text

class GAdapter(
    list : List<MyData>,
    private val clickListener: (Any)->Unit,
    ): GraphAdapter<GraphView.ViewHolder>(graphInit(list)) {


    override fun getCount(): Int {
        return graph.nodes.size
    }

    override fun getItem(position: Int): Any {
        return graph.nodes[position]
    }

    override fun isEmpty(): Boolean {
        return graph.nodes.isEmpty()
    }

    override fun onBindViewHolder(viewHolder: GraphView.ViewHolder, data: Any, position: Int) {
        (viewHolder as SimpleViewHolder).bind(data, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphView.ViewHolder {
        val li = LayoutInflater.from(parent.context)
        val binding: NodeBinding =
            DataBindingUtil.inflate(
                li,
                R.layout.node,
                parent,
                false
            )
        return SimpleViewHolder(binding)
    }

    class SimpleViewHolder(private val binding: NodeBinding) :
        GraphView.ViewHolder(binding.root) {
        fun bind(data: Any, clickListener: (Any) -> Unit){
            binding.cardText.text = if (data is Node) (data.data as MyData).text else "dump"
            binding.cardDesc.text = if (data is Node) (data.data as MyData).desc else "dump"
            binding.cardNode.setOnClickListener {
                clickListener(data)
            }
        }

    }

}

fun graphInit(list: List<MyData>): Graph{
    val parent = Node(MyData(100, 0, "suck", "my balls"))
    val graph = Graph()
    for (a in list){
        val node = Node(a)
        graph.addEdge(Edge(parent, node))
    }
    return graph
}

