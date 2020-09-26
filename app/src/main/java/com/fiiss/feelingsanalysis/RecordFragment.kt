package com.fiiss.feelingsanalysis

import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fiiss.feelingsanalysis.adapters.EncodeAudio
import com.fiiss.feelingsanalysis.adapters.ServiceRestApp
import com.fiiss.feelingsanalysis.models.BaseResponse
import com.fiiss.feelingsanalysis.models.Parametros
import com.fiiss.feelingsanalysis.models.Parametros2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RecordFragment : Fragment(), View.OnClickListener {

    lateinit var floatingActionButton: FloatingActionButton
    var grabacion: MediaRecorder? = null
    lateinit var archivoSalida: String
    var nombreArchivo: String? = null
    var serviceRestApp: ServiceRestApp = AppAndroidUnit.retrofitCliente()!!.create(ServiceRestApp::class.java)
    var serviceRestApp2: ServiceRestApp = AppAndroidUnit.retrofitCliente2()!!.create(ServiceRestApp::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener(this)

        val buttonSubir: Button = view.findViewById(R.id.buttonSubir)
        buttonSubir.setOnClickListener(this)

        val buttonCalificacion: Button = view.findViewById(R.id.buttonCalificacion)
        buttonCalificacion.setOnClickListener(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.floatingActionButton -> {
                if (grabacion == null) {
                    val encodeAudio = EncodeAudio()
                    nombreArchivo = encodeAudio.unique()

                    archivoSalida =
                        Environment.getExternalStorageDirectory().absolutePath + "/" + nombreArchivo + ".mp3"

                    grabacion = MediaRecorder()
                    grabacion!!.setAudioSource(MediaRecorder.AudioSource.MIC)
                    grabacion!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    grabacion!!.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
                    grabacion!!.setOutputFile(archivoSalida)

                    try {
                        grabacion!!.prepare()
                        grabacion!!.start()
                    } catch (e: IOException) {
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }

                    floatingActionButton.setImageResource(R.drawable.baseline_stop_red)
                    Toast.makeText(requireContext(), "Grabando...", Toast.LENGTH_SHORT).show()
                } else {
                    grabacion!!.stop()
                    grabacion!!.release()
                    grabacion = null
                    floatingActionButton.setImageResource(R.drawable.baseline_mic)
                    Toast.makeText(requireContext(), "Grabación finalizada", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            R.id.buttonSubir -> {
                val encodeAudio = EncodeAudio()
                val resutlado = encodeAudio.encodeAudio(
                    Environment.getExternalStorageDirectory()
                        .toString() + "/" + nombreArchivo + ".mp3", requireActivity()
                )

                if (resutlado == null) {
                    Toast.makeText(
                        requireContext(),
                        "Necesita Grabar un archivo",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    //Envio al servidor
                    jsonParse(resutlado)
                }

            }

            R.id.buttonCalificacion -> {

                val bundle = Bundle()
                bundle.putInt("id", arguments?.getInt("id")!!)

                findNavController().navigate(R.id.action_recordFragment_to_calificacionFragment, bundle)
            }
        }
    }

    private fun jsonParse(resutlado: String) {

        serviceRestApp.subirArchivo(Parametros(resutlado, nombreArchivo!!))
            ?.enqueue(object : Callback<BaseResponse?> {
                override fun onResponse(
                    call: Call<BaseResponse?>,
                    response: Response<BaseResponse?>
                ) {
                    if (response.isSuccessful) {
                        val resp: BaseResponse = response.body()!!



                        val nomb = nombreArchivo
                        val urlArchivo = resp.datos
                        val idprocu = arguments?.getInt("id")

                        serverCall(nomb, urlArchivo, idprocu)

                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun serverCall(nomb: String?, urlArchivo: String?, idprocu: Int?) {

        serviceRestApp2.procesoGuardado(Parametros2(nomb!!+".mp3", urlArchivo!!, idprocu.toString()))
            ?.enqueue(object : Callback<BaseResponse?> {
                override fun onResponse(
                    call: Call<BaseResponse?>,
                    response: Response<BaseResponse?>
                ) {
                    if (response.isSuccessful) {
                        val resp: BaseResponse = response.body()!!
                        Toast.makeText(requireContext(), "Guardado...", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

}