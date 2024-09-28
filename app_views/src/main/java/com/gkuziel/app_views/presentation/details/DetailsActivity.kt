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
import com.gkuziel.core.presentation.details.DetailsStateUI
import com.gkuziel.core.presentation.details.DetailsViewModel
import com.gkuziel.core.presentation.getDecayInfoLabel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()


    private val resultAdapter by lazy {
        ResultAdapter {
            if (isClickable()) {
                viewModel.updateResultValue(
                    it.id,
                    (0..100).random()
                )
            }
        }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setEventId(extractIDFromIntent())
        initViews()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventDetailsState.collect { detailsUIState ->
                    detailsUIState?.let {
                        resultAdapter.setItems(it.results)
                        updatedCountdownLabel()
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
                if (isClickable()) {
                    viewModel.addResultToEvent(
                        "description",
                        (0..100).random()
                    )
                }
            }
        }
    }

    private fun updatedCountdownLabel() {
        binding.tvDecaysIn.text = viewModel.eventDetailsState.value?.getDecayInfoLabel()
    }

    private fun isClickable() = viewModel.eventDetailsState.value?.editable ?: false

    private fun extractIDFromIntent() = intent.extras?.getString(ID_EXTRA) ?: ""

    companion object {
        private const val ID_EXTRA = "ID"

        fun start(
            context: Context,
            id: String
        ) {
            context.startActivity(
                Intent(
                    context,
                    DetailsActivity::class.java
                ).also {
                    it.putExtras(
                        Bundle().also {
                            it.putString(ID_EXTRA, id)
                        }
                    )
                }
            )
        }
    }
}