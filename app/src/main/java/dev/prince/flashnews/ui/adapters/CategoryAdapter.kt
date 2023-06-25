package dev.prince.flashnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.prince.flashnews.R
import dev.prince.flashnews.databinding.CategoryListItemBinding
import dev.prince.flashnews.databinding.TopHeadlineListItemBinding
import dev.prince.flashnews.models.Category

class CategoryAdapter(
    private val context: Context,
    private val categoryList: List<Category>
) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            CategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       with(holder){
           val item = categoryList[position]
           binding.categoryTitleTv.text = item.categoryTitle
           binding.categoryIconImg.setImageResource(item.categoryImg)
       }
    }

    override fun getItemCount() = categoryList.size

}

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root)