package com.atom.shop.ui.catalog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atom.shop.App
import com.atom.shop.JsonFileReader
import com.atom.shop.R
import com.atom.shop.databinding.FragmentCatalogBinding
import javax.inject.Inject


class CatalogFragment : Fragment(), CatalogAdapter.CatalogItemClickListener {

    private var _binding: FragmentCatalogBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var catalogViewModel: CatalogViewModel

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

        _binding = FragmentCatalogBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.listShop

        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager

        // Инициализация адаптера
        val adapter = CatalogAdapter(emptyList(), this)
        recyclerView.adapter = adapter

        // Наблюдение за LiveData из ViewModel
        catalogViewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            Log.d("CatalogFragment", "Received products: $products")
            adapter.updateData(products)
        }

        // Загрузка продуктов из JSON и сохранение в базу данных
        val jsonFileResourceId = R.raw.products
        val jsonFileReader = JsonFileReader(requireContext())
        val jsonObject = jsonFileReader.readJsonFile(jsonFileResourceId)
        Log.d("CatalogFragment", "JSON object: $jsonObject")

        if (jsonObject != null) {
            Log.d("CatalogFragment", "JSON object: $jsonObject")
            catalogViewModel.loadProductsFromJson(jsonObject)
        } else {
            Log.e("CatalogFragment", "Failed to read JSON object")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFavoriteButtonClick(productId: String) {
        TODO("Not yet implemented")
    }

    override fun onCatalogItemClick(productId: String) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToProductFragment(productId)
        findNavController().navigate(action)
    }
}