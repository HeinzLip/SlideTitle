package com.why.slidetitle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.why.slidetitle.text.TextAnnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tabCount = 5;
    val datas = mutableListOf<String>().apply {
        for (i in 0 until tabCount){
            add((5..10).randomStr())
        }
    }

    val viewpagerAdapter = ViewPager2TextAniAdapter(datas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTab()
        initViewPager2()


    }

    private fun initViewPager2(){
        viewpager2.adapter = viewpagerAdapter
        var currentIndex = 0;
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                Log.e("why", "position$position===positionOffset$positionOffset===positionOffsetPixels$positionOffsetPixels")
                if(position == 0){
                    //右划
                    changeTabRight(position, positionOffset, false);
                    changeTabRight(position + 1, positionOffset, true);

                }else if(currentIndex == position){
                    //右划
                    changeTabRight(position, positionOffset, false);
                    changeTabRight(position + 1, positionOffset, true);
                }else{
                    //左划
                    changeTabRight(position, positionOffset, false);
                    changeTabRight(position + 1, positionOffset, true);
                }

                viewpager_table.setScrollPosition(position, positionOffset, true)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if(state == ViewPager2.SCROLL_STATE_IDLE){
                    currentIndex = viewpager2.currentItem
                }
            }
        })

    }

    private fun initTab(){
        for(index in 0 until tabCount){
            viewpager_table.addTab(viewpager_table.newTab())
            viewpager_table.getTabAt(index)?.let {
                it.setCustomView(R.layout.tab_item)
                tabView(it, index)
            }
        }

        viewpager_table.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager2.setCurrentItem(tab?.position?:0, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun tabView(tab: TabLayout.Tab, position: Int){

        val textAni = tab.customView?.findViewById<TextAnnimation>(R.id.viewpager_tab_text)
        textAni?.text = datas[position]
    }

    private fun changeTabRight(position: Int, percent: Float, side: Boolean){
        viewpager_table.getTabAt(position)?.let {
            it.customView?.findViewById<TextAnnimation>(R.id.viewpager_tab_text)?.let {
                it.updatePercentAndSide(percent, side)
            }
        }
    }
}