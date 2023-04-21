package fr.airweb.news.ui.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.htmlEncode
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.airweb.news.R
import fr.airweb.news.databinding.FragmentContactBinding

class ContactPageFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        setClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initToolbar() {
        binding.contactToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.contactToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setClickListeners() {
        binding.txtContactAddress.setOnClickListener {
            val mapsIntentUri =
                Uri.parse("geo:0,0?q=${getString(R.string.contact_page_address).htmlEncode()}")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = mapsIntentUri
            }
            startActivity(intent)
        }

        binding.txtContactMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.contact_page_mail)))
            }
            startActivity(intent)
        }

        binding.txtContactPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                val phoneNumber = getString(R.string.contact_page_phone_number)
                data = Uri.parse("tel:${phoneNumber}")
            }
            startActivity(intent)
        }
    }
}