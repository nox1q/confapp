package kz.kolesateam.confapp.upcoming_events.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.di.applicationModule
import kz.kolesateam.confapp.events.data.model.ApiBranchData
import kz.kolesateam.confapp.events.data.model.ApiEventData
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_TITLE = "title"
const val EXTRA_BRANCH_ID = "branch_id"

class UpcomingEventsActivity : AppCompatActivity() {

    private val upcomingEventsViewModel: UpcomingEventsViewModel by viewModel()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var enteredName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upcoming_events_activity)
        bindViews()
        upcomingEventsViewModel.viewState.observe(this, ::handleViewState)
    }

    private fun handleViewState(state: State) = when (state) {
        is State.ShowLoading -> {
            progressBar.visibility = View.VISIBLE
        }
        is State.HideLoading -> {
            progressBar.visibility = View.GONE
        }
        is State.Success -> onLoadEventsSuccess(state.events)
        is State.Error -> onLoadEventsFailure(state.t)
        is State.ShowUserName -> showUserName(state.userName)
    }

    private fun bindViews() {
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.activity_upcoming_events_list)
        enteredName = findViewById(R.id.name_from_main_activity)
    }

    private fun showUserName(userName: String) {
        enteredName.text = resources.getString(R.string.name_fmt, userName)
    }

    private fun onLoadEventsSuccess(events: List<ApiBranchData>) {
        progressBar.visibility = View.GONE
        recyclerView.adapter = EventCardListAdapter(
            events,
            ::onMoveClicked,
            ::onCardClicked,
            ::onMarkAsFavoriteClicked
        )
    }

    private fun onLoadEventsFailure(throwable: Throwable) {
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun onCardClicked(description: String) {
        Toast.makeText(this, description, Toast.LENGTH_SHORT).show()
    }

    private fun onMoveClicked(id: Int, title: String) {
        startActivity(UpcomingEventsRouter.createBranchEventIntent(this, id, title))
    }

    private fun onMarkAsFavoriteClicked(eventData: ApiEventData) {
        upcomingEventsViewModel.onMarkAsFavoriteClicked(eventData)
    }
}