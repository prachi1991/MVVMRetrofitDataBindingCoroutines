package com.mvvm.mvvm.home.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.applexinfotech.hrmapp.base.BaseFragment
import com.example.mvvm.ui.repository.MyViewModelFactory
import com.mvvm.mvvm.api.RetrofitInstance
import com.mvvm.mvvm.databinding.LayoutHomeBinding
import com.mvvm.mvvm.home.repository.MainRepository
import com.mvvm.mvvm.home.viewmodel.MainViewModel

class HomeFragment : BaseFragment() {
    private lateinit var binding: LayoutHomeBinding
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        binding = LayoutHomeBinding.inflate(inflater, container, false)
        initViews()
        setAdapter()

        return binding.root
    }

    private fun initViews() {
        val retrofitService = RetrofitInstance
        val mainRepository = MainRepository(retrofitService)
        binding.viewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]
    }

    private fun setAdapter() {
        binding.rvHomeCards.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHomeCards.adapter = adapter
        binding.viewModel!!.movieList.observe(viewLifecycleOwner, {
            adapter.setMovieItemList(it)
        })
        binding.rvHomeCards.adapter = adapter

        binding.viewModel!!.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        binding.viewModel!!.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
                binding.rvHomeCards.adapter = adapter

            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        binding.viewModel!!.getAllMovies()
        binding.rvHomeCards.adapter = adapter
    }

}