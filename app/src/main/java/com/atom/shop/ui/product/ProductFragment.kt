package com.atom.shop.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.atom.shop.App
import com.atom.shop.databinding.FragmentProductBinding
import javax.inject.Inject

class ProductFragment : Fragment() {


    private var _binding: FragmentProductBinding? = null

    private val binding get() = _binding!!
    private val args by lazy { ProductFragmentArgs.fromBundle(requireArguments()) }

    @Inject
    lateinit var productViewModel: ProductViewModel

    private lateinit var productAdapter: ProductAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val app = requireActivity().application as? App
        app?.appComponent?.inject(this)

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val productId = args.productId

        productAdapter = ProductAdapter(requireContext(), rootView) // Инициализация адаптера

        productViewModel.loadProductById(productId)
        observeViewModel()

        return binding.root
    }


    private fun observeViewModel() {
        productViewModel.productLiveData.observe(viewLifecycleOwner, Observer { product ->
            // Обновляем данные в адаптере
            if (product != null) {
                productAdapter.bind(product)

            }
        })

        productViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errorMessage ->
            // Обрабатываем ошибку при загрузке продукта
            showError(errorMessage)
        })
    }

    private fun showError(errorMessage: String) {
        // Отображаем сообщение об ошибке
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}