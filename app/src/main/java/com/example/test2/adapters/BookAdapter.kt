package com.example.test2.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test2.BookDetails
import com.example.test2.ModelClasses.Book
import com.example.test2.R

class BookAdapter(private val books: List<Book>, private var ctx: Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val thumbnailView: ImageView = itemView.findViewById(R.id.thumbnailView)
         val titleView: TextView = itemView.findViewById(R.id.title)
         val authorView: TextView = itemView.findViewById(R.id.authors)


    fun bind(book: Book) {
        titleView.text = book.volumeInfo.title
       authorView.text = book.volumeInfo.authors?.toString()

        Glide.with(itemView.context)
            .load(book.volumeInfo.imageLinks?.thumbnail)
            .placeholder(R.drawable.book_placeholder)
            .error(R.drawable.book_placeholder)
            .into(thumbnailView)

    }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.bind(book)
        holder.itemView.setOnClickListener{
            var i = Intent(ctx, BookDetails::class.java)
            i.putExtra("id",book.id)
            i.putExtra("title", book.volumeInfo.title)
            i.putStringArrayListExtra("author", book.volumeInfo.authors)
            i.putExtra("description", book.volumeInfo.description)
            i.putExtra("thumbnail", book.volumeInfo.imageLinks.thumbnail)
            i.putExtra("previewLink", book.volumeInfo.previewLink)
            i.putExtra("buyLink", book.volumeInfo.buyLink)
//            i.putExtra("ratings",book.volumeInfo.averageRating)
            i.putStringArrayListExtra("categories",book.volumeInfo.categories)
            ctx.startActivity(i)

        }

    }

    override fun getItemCount(): Int {
        return books.size
    }
}

// Define a ViewHolder to hold the book item view
