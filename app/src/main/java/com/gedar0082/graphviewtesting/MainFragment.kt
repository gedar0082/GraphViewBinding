package com.gedar0082.graphviewtesting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.blox.graphview.tree.BuchheimWalkerAlgorithm
import de.blox.graphview.tree.BuchheimWalkerConfiguration
import com.gedar0082.graphviewtesting.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        val repo = Repo()
        val factory = Factory(repo)
        mViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)
        binding.vm = mViewModel
        binding.lifecycleOwner = this
        initGraph()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initGraph(){
        val config : BuchheimWalkerConfiguration = BuchheimWalkerConfiguration.Builder()
            .setSiblingSeparation(100)
            .setLevelSeparation(300)
            .setSubtreeSeparation(300)
            .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
            .build()
        binding.graph.setLayout(BuchheimWalkerAlgorithm(config))
        displayGraph()
    }

    private fun displayGraph(){
        val observer = Observer<List<MyData>> {
            binding.graph.adapter =
                GAdapter(it) { selected: Any ->
                    run{
                        print("fuck")
                    }
            }
        }
        mViewModel.list.observe(viewLifecycleOwner, observer)
    }


}