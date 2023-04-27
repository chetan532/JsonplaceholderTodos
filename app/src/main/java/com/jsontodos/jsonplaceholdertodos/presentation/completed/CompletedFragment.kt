package com.jsontodos.jsonplaceholdertodos.presentation.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.jsontodos.jsonplaceholdertodos.databinding.FragmentCompletedBinding
import com.jsontodos.jsonplaceholdertodos.databinding.FragmentHomeBinding
import com.jsontodos.jsonplaceholdertodos.models.TodosData
import com.jsontodos.jsonplaceholdertodos.presentation.home.HomeTodoAdapter
import com.jsontodos.jsonplaceholdertodos.presentation.main.MainViewModel
import com.jsontodos.jsonplaceholdertodos.retrofit.Result
import com.jsontodos.jsonplaceholdertodos.utils.gone
import com.jsontodos.jsonplaceholdertodos.utils.visible

class CompletedFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentCompletedBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: HomeTodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompletedBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getAllTodosFromDb().observe(viewLifecycleOwner, Observer {

            if (it != null && it.isNotEmpty()) {

                binding.shimmerGridLayout.newsShimmerLayout.stopShimmer()
                binding.shimmerGridLayout.newsShimmerLayout.gone()
                binding.rvTodos.visible()

                adapter =
                    HomeTodoAdapter(
                        requireContext(),
                        onItemClick = { todosData ->
                            updateToDatabase(todosData)
                        }).apply {
                        if (it != null && it.size > 0) {

                            mainViewModel.addTodosToDb(it as ArrayList<TodosData>)

                            val array = ArrayList<TodosData>()
                            it.forEach { todo ->
                                if (todo.completed) {
                                    array.add(todo)
                                }
                            }
                            submitList(array)

//                            submitList(it)
                        }
                    }

                binding.rvTodos.adapter = adapter


            } else {
                mainViewModel.todosDataLiveData.observe(
                    viewLifecycleOwner,
                    Observer {

                        when (it) {
                            is Result.Success -> {

                                binding.shimmerGridLayout.newsShimmerLayout.stopShimmer()
                                binding.shimmerGridLayout.newsShimmerLayout.gone()
                                binding.rvTodos.visible()

                                adapter =
                                    HomeTodoAdapter(
                                        requireContext(),
                                        onItemClick = { todosData ->
                                            updateToDatabase(todosData)
                                        }).apply {
                                        if (it.data != null && it.data.size > 0) {

                                            mainViewModel.addTodosToDb(it.data)
                                            val array = ArrayList<TodosData>()
                                            it.data.forEach { todo ->
                                                if (todo.completed) {
                                                    array.add(todo)
                                                }
                                            }
                                            submitList(array)
//                                            submitList(it.data)
                                        }
                                    }

                                binding.rvTodos.adapter = adapter
                            }
                            is Result.Error -> {
                                binding.shimmerGridLayout.newsShimmerLayout.stopShimmer()
                                binding.shimmerGridLayout.newsShimmerLayout.gone()
                            }
                            is Result.Loading -> {
                                binding.shimmerGridLayout.newsShimmerLayout.startShimmer()
                                binding.shimmerGridLayout.newsShimmerLayout.visible()
                                binding.rvTodos.gone()
                            }
                        }
                    }
                )
            }

        })

    }

    fun updateToDatabase(todosData: TodosData) {
        todosData.completed = false
        mainViewModel.updateTodosData(todosData.completed,todosData.id)
        mainViewModel.getAllTodosFromDb().observe(viewLifecycleOwner, Observer {
            if (it != null && it.size > 0) {

                val array = ArrayList<TodosData>()
                it.forEach {
                    if (it.completed) {
                        array.add(it)
                    }
                }
                adapter.submitList(array)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}