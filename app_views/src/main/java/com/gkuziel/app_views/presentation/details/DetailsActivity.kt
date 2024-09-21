package com.gkuziel.app_views.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gkuziel.app_views.databinding.ActivityDetailBinding
import com.gkuziel.core.presentation.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private val resultAdapter by lazy {
        ResultAdapter {
            viewModel.setResultValue(
                it.id,
                (0..100).random()
            )
        }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setId(intent.extras?.getString("id") ?: "")
        initViews()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.event.collect { detailsUIState ->
                    detailsUIState.event?.let {
                        resultAdapter.setItems(it.results)
                    }
                }
            }
        }
    }

    private fun initViews() {
        with(binding) {
            rvResults.adapter = resultAdapter
            rvResults.layoutManager = LinearLayoutManager(
                this@DetailsActivity,
                RecyclerView.VERTICAL,
                false
            )

            btnAddResult.setOnClickListener {
                viewModel.addResult(
                    UUID.randomUUID().toString(),
                    "description",
                    (0..100).random()
                )
            }
        }
    }

    companion object {
        fun start(context: Context, id: String) {
            val intent = Intent(
                context,
                DetailsActivity::class.java
            )
            intent.putExtras(
                Bundle().also {
                    it.putString("id", id)
                }
            )

            context.startActivity(intent)
        }
    }

}