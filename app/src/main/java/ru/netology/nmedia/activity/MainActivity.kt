package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.R
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeIcon.setImageResource( if (post.likedByMe) R.drawable.liked_icon else R.drawable.like_icon)
                likeNumber.text = formatAmount(post.likes)
                sharingsNumber.text = formatAmount(post.sharings)
            }
        }
        //обработчик кнопки лайк
        binding.likeIcon.setOnClickListener {
            viewModel.like()
        }
        //обработчик кнопки "поделиться"
        binding.sharingIcon.setOnClickListener {
            viewModel.share()
        }
    }
}