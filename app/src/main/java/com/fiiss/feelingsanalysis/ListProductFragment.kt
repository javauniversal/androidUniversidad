package com.fiiss.feelingsanalysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.adapters.RecyclerItemClickListener
import com.fiiss.feelingsanalysis.adapters.RecyclerProduct
import com.fiiss.feelingsanalysis.models.Product


class ListProductFragment : Fragment() {

    lateinit var recyclerProduct: RecyclerView

    companion object {
        fun newInstance() = ListProductFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_product_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerProduct = view.findViewById(R.id.recyclerProduct)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var listProduct = ArrayList<Product>()

        listProduct.add(Product(1, "PC escritorio", "", R.drawable.ic_launcher_background))
        listProduct.add(Product(2, "Portátil", "", R.drawable.ic_launcher_background))
        listProduct.add(Product(3, "Celular", "", R.drawable.ic_launcher_background))

        val recyclerRestaurante = RecyclerProduct(requireActivity(), listProduct)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 1)
        recyclerProduct.layoutManager = mLayoutManager
        recyclerProduct.itemAnimator = DefaultItemAnimator()
        recyclerProduct.adapter = recyclerRestaurante
        recyclerProduct.addOnItemTouchListener(RecyclerItemClickListener(requireActivity()) { _, position ->

            val bundle = Bundle()
            listProduct[position as Int].idproductos?.let { bundle.putInt("id", it) }

            findNavController().navigate(R.id.action_listProductFragment_to_recordFragment, bundle)
        })

    }

}
