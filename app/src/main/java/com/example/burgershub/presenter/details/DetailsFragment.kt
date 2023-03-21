package com.example.burgershub.presenter.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burgershub.databinding.FragmentDetailsBinding
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.model.Ingredient
import com.example.burgershub.util.StateView
import com.example.burgershub.util.formattedValue
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()
    private val ingredientsAdapter by lazy { IngredientsAdapter() }

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBurgerById()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getBurgerById() {
        viewModel.getBurgerById(args.burgerId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }
                is StateView.Success -> {
                    stateView.data?.let {
                        ingredientsAdapter.ingredients = it.ingredients as List<Ingredient>
                        configData(it)
                    }
                }
                is StateView.Error -> {
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun configData(burger: Burger) {
        Picasso
            .get()
            .load(burger.images?.get(1)?.lg)
            .into(binding.imageBurger)

        binding.textBurger.text = burger.name
        binding.textDescription.text = burger.desc
        binding.textBurgerPrice.text = burger.price?.formattedValue()

        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        rvIngredients.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}