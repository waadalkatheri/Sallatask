package com.example.task.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.task.R.id
import com.example.task.R.layout
import com.example.task.ui.models.Image
import java.util.*


class ViewPagerAdapter(private val context: Context, private val images: List<Image>) :
    PagerAdapter() {


    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(layout.item_image, container, false)

        val imageView: ImageView = itemView.findViewById<View>(id.imageViewMain) as ImageView

        Glide.with(context).load(images[position].url).into(imageView)

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}