package com.fiiss.feelingsanalysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.feelingsanalysis.adapters.RecyclerCalificacion
import com.fiiss.feelingsanalysis.adapters.RecyclerItemClickListener
import com.fiiss.feelingsanalysis.adapters.RecyclerProduct
import com.fiiss.feelingsanalysis.adapters.ServiceRestApp
import com.fiiss.feelingsanalysis.models.BaseResponse
import com.fiiss.feelingsanalysis.models.Historial
import com.fiiss.feelingsanalysis.models.Parametros2
import com.fiiss.feelingsanalysis.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalificacionFragment : Fragment() {

    lateinit var recyclerCalificacion: RecyclerView
    var serviceRestApp2: ServiceRestApp = AppAndroidUnit.retrofitCliente2()!!.create(ServiceRestApp::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calificacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCalificacion = view.findViewById(R.id.recyclerCalificacion)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val producto = Product(1, "", "", 2222)

        producto.producto = arguments?.getInt("id")!!

        serviceRestApp2.listar(producto)
            ?.enqueue(object : Callback<List<Historial>?> {
                override fun onResponse(
                    call: Call<List<Historial>?>,
                    response: Response<List<Historial>?>
                ) {
                    if (response.isSuccessful) {
                        val resp: List<Historial> = response.body()!!
                        val recyclerCalificacion21 = RecyclerCalificacion(resp)
                        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 1)
                        recyclerCalificacion.layoutManager = mLayoutManager
                        recyclerCalificacion.itemAnimator = DefaultItemAnimator()
                        recyclerCalificacion.adapter = recyclerCalificacion21
                        recyclerCalificacion.addOnItemTouchListener(RecyclerItemClickListener(requireActivity()) { _, _ ->

                        })
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<Historial>?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }
            })


    }

}