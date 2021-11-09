package kz.kolesateam.confapp.all_events.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.all_events.domain.AllEventsRepository
import kz.kolesateam.confapp.events.data.model.ApiEventData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_TITLE = "title"
const val EXTRA_BRANCH_ID = "branch_id"

class AllEventsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var eventTitle: TextView
    private lateinit var backToUpcomingEvents: ImageView
    private lateinit var title: String
    private var branchId: Int = -1
    private val allEventsViewModel: AllEventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branch_events)
        bindView()
        allEventsViewModel.viewState.observe(this, ::handleViewState)
        allEventsViewModel.onViewSubscribed(branchId)
        navigateToUpcomingEvents()
    }

    private fun handleViewState(state: AllEventsState) = when (state) {
        is AllEventsState.ShowLoading -> {
            progressBar.visibility = View.VISIBLE
        }
        is AllEventsState.HideLoading -> {
            progressBar.visibility = View.GONE
        }
        is AllEventsState.Success -> onLoadEventsSuccess(state.events)
        is AllEventsState.Error -> onLoadEventsFailure(state.t)
    }

    private fun bindView() {
        recyclerView = findViewById(R.id.event_card_vertical_list)
        eventTitle = findViewById(R.id.branch_title_text_view)
        progressBar = findViewById(R.id.progress_bar)
        branchId = intent.getIntExtra(EXTRA_BRANCH_ID, -1)
        title = intent.getStringExtra(EXTRA_TITLE) ?: ""
        backToUpcomingEvents = findViewById(R.id.ic_arrow_back_app_image_view)
        eventTitle.text = title
    }

    private fun onLoadEventsSuccess(events: List<ApiEventData>) {
        progressBar.visibility = View.GONE

        recyclerView.adapter = EventCardVerticalAdapter(
            events,
            ::onCardClicked
        )
    }

    private fun onLoadEventsFailure(throwable: Throwable) {
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun onCardClicked(description: String) {
        Toast.makeText(this, description, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToUpcomingEvents() {
        backToUpcomingEvents.setOnClickListener {
            finish()
        }
    }
}