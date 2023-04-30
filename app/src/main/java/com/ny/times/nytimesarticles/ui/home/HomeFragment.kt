package com.ny.times.nytimesarticles.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ny.times.nytimesarticles.R
import com.ny.times.nytimesarticles.activities.BaseFragment
import com.ny.times.nytimesarticles.activities.MostPopularDetailActivity
import com.ny.times.nytimesarticles.adapters.MostPopularListAdapter
import com.ny.times.nytimesarticles.adapters.RecyclerviewItemDecoration
import com.ny.times.nytimesarticles.databinding.FragmentHomeBinding
import com.ny.times.nytimesarticles.helper.MostPopularDataRequest
import com.ny.times.nytimesarticles.models.MostPopular
import com.ny.times.nytimesarticles.models.Response
import io.reactivex.disposables.Disposable

class HomeFragment : BaseFragment(),SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentHomeBinding? = null

  //  private lateinit var sharedViewModel: SharedViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val TAG = "HomeFragmentTag"

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var mostPopularListAdapter: MostPopularListAdapter? = null
    private var errorLayout: View? = null
    private var disposable: Disposable? = null
    private var mostPopularDataRequest: MostPopularDataRequest? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        recyclerView = binding.xRecyclerView
        mostPopularListAdapter = MostPopularListAdapter(requireContext(), object:MostPopularListAdapter.OnItemClickListener{
            override fun onItemClick(newsItem: MostPopular) {
                MostPopularDetailActivity.start(requireContext(),newsItem)
            }
        })
        recyclerView?.adapter = mostPopularListAdapter
        recyclerView?.addItemDecoration(RecyclerviewItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_micro)))
        recyclerView?.layoutManager = LinearLayoutManager(_context)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        swipeRefreshLayout = binding.xSwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener(this)

        errorLayout = binding.xErrorLayout.root

        binding.xErrorLayout.xButtonRetry.setOnClickListener(View.OnClickListener {
            onRefresh()
        })


        initializeReq()

        onRefresh()

        return root
    }

    private fun initializeReq(){
        if (mostPopularDataRequest == null) {
            mostPopularDataRequest = MostPopularDataRequest()
        }
    }

    private fun handleError(th: Throwable) {

        Log.d(TAG, "handleError: "+th.message)
        setVisibleError(true)
        binding.xSwipeRefreshLayout.setRefreshing(false)
        setVisibleRecyclerview(false)
    }

    fun setVisibleError(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.xErrorLayout.root.visibility = visibility
    }

    fun setVisibleRecyclerview(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.xRecyclerView.visibility = visibility
    }

    private fun updateItems(response: Response?) {
        if (mostPopularListAdapter != null) mostPopularListAdapter?.replaceItems(response!!.getResults())
        setVisibleRecyclerview(true)
        binding.xSwipeRefreshLayout.setRefreshing(false)
        setVisibleError(false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mostPopularListAdapter = null
        swipeRefreshLayout = null
        recyclerView = null
    }

    override fun onRefresh() {
        showProgress(true)
        initializeReq()
        disposable = mostPopularDataRequest?.getMostPopularArticles()?.subscribe(
            { response -> updateItems(response) },
            { throwable -> handleError(throwable) }
        )
    }
    private fun showProgress(shouldShow: Boolean) {
        swipeRefreshLayout?.isRefreshing = shouldShow
        setVisibleRecyclerview(!shouldShow)
        setVisibleError(!shouldShow)
    }
    override fun onStop() {
        super.onStop()
        showProgress(false)
        disposeSafe(disposable)
        disposable = null
    }
    fun disposeSafe(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onResume() {
        super.onResume()
        if (mostPopularListAdapter != null && mostPopularListAdapter?.itemCount ?: 0 > 0) {
            setVisibleError(false)
        }
    }
}