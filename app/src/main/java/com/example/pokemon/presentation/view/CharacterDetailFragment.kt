package com.example.pokemon.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.pokemon.databinding.FragmentCharacterDetailBinding
import com.example.pokemon.presentation.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val args:CharacterDetailFragmentArgs by navArgs()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        mainActivityViewModel.getCharacter(args.name, args.url)

        mainActivityViewModel.character.observe( viewLifecycleOwner, Observer {
            if (it != null && it.id != -1) {
                binding.tvName.text = it.name
                binding.tvHeight.text = it.height+"\nHeight"
                binding.tvWeight.text = it.weight+"\nWeight"
                binding.tvHeight.isVisible = true
                binding.tvWeight.isVisible = true
                if( it.sprite != "" ) {
                    Picasso.with(this.requireContext()).load(it.sprite).into(binding.ivImage)
                    binding.ivImage.isVisible = true
                }
            }
        } )

        mainActivityViewModel.isLoadingDetail.observe( viewLifecycleOwner,  Observer {
            binding.pb.isVisible = it
            binding.efabRefresh.isEnabled = !it
        } )

        mainActivityViewModel.tryAgainDetail.observe( viewLifecycleOwner, Observer {
            binding.efabRefresh.isVisible = it
            if(it)
            {
                binding.tvName.text = ""
                binding.tvHeight.text = ""
                binding.tvWeight.text = ""
                binding.ivImage.isVisible = false
                binding.tvHeight.isVisible = false
                binding.tvWeight.isVisible = false
            }
        } )
        binding.efabRefresh.setOnClickListener {
            mainActivityViewModel.getCharacter(args.name, args.url)
        }
        return view
    }

}