package com.gkuziel.mskprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkuziel.mskprep.databinding.ActivityMainBinding
import com.gkuziel.mskprep.presentation.EventAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val eventAdapter by lazy {
        EventAdapter {


            viewModel.modifyUser(
                it
//                it.copy(
//                    description = "M ${it.description}"
//                )
            )
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        viewModel.loadUsers(resources)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.events.collect { eventsUi ->
                    Log.d("dsfsd", "fdssd")
                    eventAdapter.setItems(eventsUi)
//                    eventAdapter.setItems(EventtMapper().execute(eventsUi))
                }
            }
        }


    }

    private fun initViews() {
        with(binding) {
            rvEvents.adapter = eventAdapter
            rvEvents.layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

}