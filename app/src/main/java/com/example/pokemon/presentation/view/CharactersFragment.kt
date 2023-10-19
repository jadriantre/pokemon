package com.example.pokemon.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.FragmentCharactersBinding
import com.example.pokemon.presentation.adapter.CharacterAdapter
import com.example.pokemon.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable

@AndroidEntryPoint
class CharactersFragment: Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentCharactersBinding? = null
    private lateinit var disposable: Disposable
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()

        binding.rcCharacters.layoutManager = LinearLayoutManager( this.requireContext() )

        mainActivityViewModel.characters.observe( viewLifecycleOwner, Observer { it ->
            val adapter = CharacterAdapter(it)
            binding.rcCharacters.adapter = adapter
            disposable = adapter.clickEvent.subscribe {
                onCharacterSelected(it.url)
            }
        } )

        mainActivityViewModel.isLoading.observe( viewLifecycleOwner,  Observer {
            binding.pb.isVisible = it
            binding.efabRefresh.isEnabled = !it
        } )

        mainActivityViewModel.tryAgain.observe( viewLifecycleOwner, Observer {
            binding.efabRefresh.isVisible = it
        } )
        binding.efabRefresh.setOnClickListener {
            mainActivityViewModel.getCharacters()
        }

        return view
    }

    private fun onCharacterSelected(url: String)
    {

    }

    override fun onDestroy() {
        super.onDestroy()
        if(disposable!= null)
            disposable.dispose()
    }

}

