package com.example.pfinalsw.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfinalsw.R
import com.example.pfinalsw.domain.model.People
import com.example.pfinalsw.presentation.viewmodel.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment(), MyAdapter.Listener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: MyAdapter
    private val viewModel by viewModel<VMMain>()
    private lateinit var viewModelD : VMDesc


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelD = ViewModelProvider(requireActivity()).get(VMDesc::class.java)
        loadData()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun loadData() {
        viewModel.getData()
        with(viewModel) {

            pList.observe(viewLifecycleOwner, Observer {
                val peopleList : ArrayList<People> = viewModel.pList.value!!
                initRecyclerView(peopleList)
            })
        }


    }

    private fun initRecyclerView(peopleList : ArrayList<People>) {
        viewModelD.people.value = peopleList
        myAdapter = MyAdapter(peopleList, this)
        val pList = view?.findViewById<RecyclerView>(R.id.char_list)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        pList?.layoutManager = layoutManager
        pList?.adapter = myAdapter
    }

    override fun onItemClick(ap: Int) {
        viewModelD.position.value = ap
    }
}

