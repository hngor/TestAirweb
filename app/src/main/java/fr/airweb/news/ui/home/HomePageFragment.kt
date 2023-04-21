package fr.airweb.news.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.airweb.news.R
import fr.airweb.news.databinding.FragmentHomeBinding
import fr.airweb.news.ui.home.adapter.HomePageAdapter
import fr.airweb.news.ui.home.model.HomeUiModel
import fr.airweb.news.util.NewsTypeEnum
import fr.airweb.news.util.SortNewsEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : Fragment() {

    private lateinit var homePageAdapter: HomePageAdapter
    private val viewModel: HomePageViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.data.observe(this) { state -> updateUI(state) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()
        initToolbar()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
    }

    private fun initClickListeners() {
        binding.btnSearch.setOnClickListener {
            viewModel.searchNews(binding.editSearch.text.toString())
        }

        binding.btnClearSearch.setOnClickListener {
            binding.editSearch.setText("")
            viewModel.getNewsFromType()
        }
    }

    private fun updateUI(state: HomeUiModel) {
        when (state) {
            HomeUiModel.Loading -> {
                binding.recyclerViewNews.visibility = View.GONE
                binding.txtNoNews.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }

            is HomeUiModel.NewsList -> {
                binding.recyclerViewNews.visibility = View.VISIBLE
                binding.txtNoNews.visibility = View.GONE
                binding.progressBar.visibility = View.GONE

                homePageAdapter.articleList = state.newsList
                homePageAdapter.notifyDataSetChanged()
            }

            HomeUiModel.NewsListEmpty -> {
                binding.recyclerViewNews.visibility = View.GONE
                binding.txtNoNews.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initToolbar() {
        binding.toolbarHomePage.inflateMenu(R.menu.menu_home)
        binding.toolbarHomePage.setOnMenuItemClickListener { listener ->
            when (listener.itemId) {
                R.id.action_filter -> {
                    showFilterDialog()
                }

                R.id.action_sort -> {
                    showSortDialog()
                }

                R.id.action_contact -> {
                    navigateToContactPageFragment()
                }
            }
            true
        }
    }

    private fun showSortDialog() {
        val filters = arrayOf(
            SortNewsEnum.DATE.value,
            SortNewsEnum.TITLE.value
        )

        var selectedFilter = filters.indexOf(viewModel.sortArticles)

        AlertDialog.Builder(activity)
            .setTitle(getString(R.string.home_page_sort_dialog_title))
            .setSingleChoiceItems(filters, selectedFilter) { dialog, which ->
                selectedFilter = which
                viewModel.sortArticles = filters[selectedFilter]
            }
            .setPositiveButton(getString(R.string.home_fragment_sort_dialog_ok_button_text)) { dialog, which ->
                dialog.dismiss()
                viewModel.getSortedNews()
            }
            .setNegativeButton(getString(R.string.home_fragment_sort_dialog_cancel_button_text)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showFilterDialog() {
        val filters = arrayOf(
            NewsTypeEnum.NEWS.value,
            NewsTypeEnum.ACTUALITE.value,
            NewsTypeEnum.HOT.value
        )

        var selectedFilter = filters.indexOf(viewModel.displayNewsType)

        AlertDialog.Builder(activity)
            .setTitle(getString(R.string.home_page_filter_dialog_title))
            .setSingleChoiceItems(filters, selectedFilter) { dialog, which ->
                selectedFilter = which
                viewModel.displayNewsType = filters[selectedFilter]
            }
            .setPositiveButton(getString(R.string.home_fragment_filter_dialog_ok_button_text)) { dialog, which ->
                dialog.dismiss()
                viewModel.getNewsFromType()
            }
            .setNegativeButton(getString(R.string.home_fragment_filter_dialog_cancel_button_text)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun initRecyclerView() {
        val viewManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        homePageAdapter = HomePageAdapter(
            onNewsClick = { articleId -> navigateToDetailPageFragment(articleId = articleId) }
        )

        binding.recyclerViewNews.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homePageAdapter
        }
    }

    private fun navigateToDetailPageFragment(articleId: Int) {
        val action = HomePageFragmentDirections.navigateToDetailPageFragment(articleId = articleId)
        findNavController().navigate(action)
    }

    private fun navigateToContactPageFragment() {
        val action = HomePageFragmentDirections.navigateToContactPageFragment()
        findNavController().navigate(action)
    }
}