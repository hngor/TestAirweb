package fr.airweb.news.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import fr.airweb.news.R
import fr.airweb.news.databinding.FragmentDetailPageBinding
import fr.airweb.news.ui.detail.DetailPageViewModel
import fr.airweb.news.ui.detail.model.DetailUiModel
import org.koin.androidx.viewmodel.ext.android.getStateViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailPageFragment : Fragment() {

    private val viewModel: DetailPageViewModel by viewModel()
    private lateinit var binding: FragmentDetailPageBinding
    private val navArguments: DetailPageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.data.observe(this) { state -> updateUI(state) }
        viewModel.getArticle(navArguments.articleId)
    }

    private fun updateUI(state: DetailUiModel) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }
}