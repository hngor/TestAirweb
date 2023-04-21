package fr.airweb.news.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import fr.airweb.news.R
import fr.airweb.news.databinding.FragmentDetailPageBinding
import fr.airweb.news.ui.detail.model.DetailUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPageFragment : Fragment() {

    private val detailPageViewModel: DetailPageViewModel by viewModel()
    private lateinit var binding: FragmentDetailPageBinding
    private val navArguments: DetailPageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailPageViewModel.data.observe(this) { state -> updateUI(state) }
        detailPageViewModel.getArticle(navArguments.articleId)
    }

    private fun updateUI(state: DetailUiModel) {
        when (state) {
            is DetailUiModel.DetailArticle -> {
                binding.detailProgressBar.visibility = View.GONE
                binding.detailScrollView.visibility = View.VISIBLE
                binding.txtDetailErrorMessage.visibility = View.GONE

                binding.txtDetailTitle.text = state.title
                Glide.with(this).load(state.picture).into(binding.imgDetail)
                binding.txtDetailContextText.text = state.content
            }

            DetailUiModel.Error -> {
                binding.detailProgressBar.visibility = View.GONE
                binding.detailScrollView.visibility = View.GONE
                binding.txtDetailErrorMessage.visibility = View.VISIBLE
            }

            DetailUiModel.Loading -> {
                binding.detailProgressBar.visibility = View.VISIBLE
                binding.detailScrollView.visibility = View.GONE
                binding.txtDetailErrorMessage.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailPageToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.detailPageToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}