package com.pthw.uidesignprototype

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.SnapHelper
import com.pthw.sharebase.core.BaseActivity
import com.pthw.sharebase.extensions.hide
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.show
import com.pthw.uidesignprototype.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(inflater())
    }

    private val carouselAdapter by lazy {
        CarouselAdapter()
    }

    private val byRoomAdapter by lazy {
        RoomRatesAdapter(byRoom = true)
    }

    private val byRatesAdapter by lazy {
        RoomRatesAdapter(byRoom = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setupUI()
    }

    private fun ActivityMainBinding.setupUI() {
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvCarousel)
        rvCarousel.adapter = carouselAdapter
        tvCarouselSeeAll.text = "See All 1/${carouselAdapter.itemCount}"


        rvCarousel.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val position =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() + 1
                tvCarouselSeeAll.text = "See All $position/${carouselAdapter.itemCount}"
            }
        })

        //
        rvRoomRates.adapter = byRoomAdapter

        with(includedTab) {
            tcvByRoom.setOnClickListener {
                tcvByRoomBlue.show()
                tcvByRoom.hide()
                tcvByRatesBlue.hide()
                tcvByRates.show()
                rvRoomRates.adapter = byRoomAdapter
            }

            tcvByRates.setOnClickListener {
                tcvByRoomBlue.hide()
                tcvByRoom.show()
                tcvByRatesBlue.show()
                tcvByRates.hide()
                rvRoomRates.adapter = byRatesAdapter
            }
        }

    }


}