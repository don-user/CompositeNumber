package ru.yundon.compositenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.yundon.compositenumber.Constants.EXCEPTION_MESSAGE_BINDING
import ru.yundon.compositenumber.databinding.FragmentGameBinding
import ru.yundon.compositenumber.domain.entity.GameResult
import ru.yundon.compositenumber.presentation.viewmodel.GameViewModelFactory
import ru.yundon.compositenumber.presentation.viewmodel.ViewModelGameFragment

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModels by lazy {
        ViewModelProvider(
            this,
            GameViewModelFactory(args.level, requireActivity().application)
        )[ViewModelGameFragment::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException(EXCEPTION_MESSAGE_BINDING)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModels
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() = with(binding) {

        viewModels.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun launchGameFinishedFragment(result: GameResult) {

        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(result)
        )
    }
}