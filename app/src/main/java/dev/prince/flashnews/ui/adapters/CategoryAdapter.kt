package dev.prince.flashnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dev.prince.flashnews.databinding.CategoryListItemBinding
import dev.prince.flashnews.models.Category
import dev.prince.flashnews.ui.home.HomeFragmentDirections

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
           binding.categoryCardView.setOnClickListener{
               Navigation.findNavController(it).navigate(
                   HomeFragmentDirections.actionHomeFragmentToAllArticlesFragment(item.categoryTitle)
               )
           }
       }
    }

    override fun getItemCount() = categoryList.size

}

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root)