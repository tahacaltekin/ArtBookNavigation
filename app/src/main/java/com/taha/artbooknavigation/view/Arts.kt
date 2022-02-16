package com.taha.artbooknavigation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.taha.artbooknavigation.adapter.ArtsAdapter
import com.taha.artbooknavigation.databinding.FragmentArtsBinding
import com.taha.artbooknavigation.model.Art
import com.taha.artbooknavigation.roomdb.ArtDao
import com.taha.artbooknavigation.roomdb.ArtDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class Arts : Fragment() {

    private lateinit var artAdapter: ArtsAdapter
    private var _binding: FragmentArtsBinding? = null
    private val binding get() = _binding!!
    private val disposable = CompositeDisposable()
    private lateinit var artDao: ArtDao
    private lateinit var artDatabase: ArtDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        artDatabase = Room.databaseBuilder(requireContext(), ArtDatabase::class.java, "Arts").build()
        artDao = artDatabase.artDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArtsBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFromSQL()
    }

    fun getFromSQL() {
        disposable.add(artDao.getArtWithNameAndId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(artList: List<Art>) {
        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        artAdapter = ArtsAdapter(artList)
        binding.artList.adapter = artAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
